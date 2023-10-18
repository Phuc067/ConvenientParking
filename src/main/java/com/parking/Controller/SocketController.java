package com.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.parking.model.ChatMessage;
import com.parking.model.SocketConnectData;
import com.parking.model.SocketMessageData;
import com.parking.service.SocketService;



@Controller
public class SocketController {
	@Autowired
    SocketService socketService;
	
	@MessageMapping("/hello")
	@SendTo("/topic/messages")
	public String send(String username) {
	return "Hello, " + username;
	}
	

    @PostMapping("/sendToMerchant/{parkingLotID}")
    public boolean sendToMerchant(@RequestBody SocketMessageData socketMessageData, @PathVariable Long parkingLotID) {
    	socketService.sendToMerchant(parkingLotID, socketMessageData);
        return true;
    }

    @MessageMapping("/connect")
    public boolean receiveMessage(@Payload SocketConnectData socketConnectData) {
        System.out.println("connect: " + socketConnectData.getParkingLotID());
        return true;
    }
    
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage chatMessage(@Payload ChatMessage chatMessage)
    {
    	return chatMessage;
    }
    
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor simpMessageHeaderAccessor)
    {
    	simpMessageHeaderAccessor.getSessionAttributes().put("username", chatMessage.getSender());
    	return chatMessage;
    }
}
