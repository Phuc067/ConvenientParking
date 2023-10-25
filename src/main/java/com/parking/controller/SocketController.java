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
import com.parking.model.CheckInMessageData;
import com.parking.service.SocketService;
import com.parking.dto.checkInOut.CheckInInformation;

@Controller
public class SocketController {
	@Autowired
    private SocketService socketService;
	
	@MessageMapping("/hello")
	@SendTo("/topic/messages")
	public String send(String username) {
	return "Hello, " + username;
	}
	
//	/app/sendToParkingLot/{parkingLotId}
    @MessageMapping("/sendToParkingLot/{parkingLotId}")
    public CheckInMessageData sendToParkingLot(@Payload CheckInMessageData checkInMessageData, @PathVariable Long parkingLotID) {
    	socketService.sendToParkingLot(parkingLotID, checkInMessageData);
        return checkInMessageData;
    }
    
//    /app/submitLiciensePlate/{parkingLotId}
    @MessageMapping("/submitLiciensePlate/{parkingLotId}")
    public CheckInInformation sendToParkingLot(@Payload CheckInInformation checkInInformation)
    {
    	socketService.updateCheckInInformation(checkInInformation);
    	return checkInInformation;
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
