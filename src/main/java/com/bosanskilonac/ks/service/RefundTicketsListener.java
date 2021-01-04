package com.bosanskilonac.ks.service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.PovracajNovcaDto;
import utility.BLURL;

@Component
public class RefundTicketsListener {
	
	private ObjectMapper objectMapper;
	private KorisnikService korisnikService;
	
	public RefundTicketsListener(ObjectMapper objectMapper, KorisnikService korisnikService) {
		this.objectMapper = objectMapper;
		this.korisnikService = korisnikService;
	}

	@JmsListener(destination = BLURL.AMQUEUE_REFUND, concurrency = "5-10")
	public void handle(Message message) {
		try {
			String jsonText = ((TextMessage)message).getText();
			PovracajNovcaDto povracajNovcaDto = objectMapper.readValue(jsonText, PovracajNovcaDto.class);
			korisnikService.refund(povracajNovcaDto);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
