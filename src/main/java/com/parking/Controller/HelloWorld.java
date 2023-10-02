package com.parking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("")
public class HelloWorld {
	@GetMapping("/hello")
	public String doGetHelloWorld()
	{
		return "Hello world";
	}
}
