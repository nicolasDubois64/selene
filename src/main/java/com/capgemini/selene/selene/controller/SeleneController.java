package com.capgemini.selene.selene.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeleneController {
	
	@RequestMapping(value="/temperature", method=RequestMethod.GET)
    public String temperature() {
        return "20";
    }

}
