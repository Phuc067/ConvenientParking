package com.parking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.parking.model.ChatMessage;
import com.parking.model.MessageType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketEventListener {
	
	@Autowired
	private SimpMessageSendingOperations messageTemplate;
	
	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event)
	{
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
		String username =(String) headerAccessor.getSessionAttributes().get("username");
		if(username != null)
		{
//			Log.info("User disconnected: {}", username);
			ChatMessage chatMessage = new ChatMessage();
			chatMessage.setType(MessageType.LEAVE);
			chatMessage.setSender(username);
			
			messageTemplate.convertAndSend("/topic/public",chatMessage);
		}
	}
}
