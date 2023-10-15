package com.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.parking.model.SocketConnectData;
import com.parking.model.SocketMessageData;
import com.parking.service.SocketService;



@Controller
public class SocketController {
	@Autowired
    SocketService socketService;

    @PostMapping("/sendToMerchant/{parkingLotID}")
    public boolean sendToMerchant(@RequestBody SocketMessageData socketMessageData, @PathVariable Long parkingLotID) {
    	socketService.sendToMerchant(parkingLotID, socketMessageData);
//        template.convertAndSend("/user/" + String.valueOf(parkingLotID) , socketMessageData);
        return true;
    }

    @MessageMapping("/connect")
    public boolean receiveMessage(@Payload SocketConnectData socketConnectData) {
        System.out.println("connect: " + socketConnectData.getParkingLotID());
        return true;
    }
}
