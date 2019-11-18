package com.capgemini.selene.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.selene.engine.SeleneDataManager;
import com.capgemini.selene.engine.SeleneEngine;
import com.capgemini.selene.model.SeleneEvent;
import com.capgemini.selene.parser.SeleneDataParser;
import com.capgemini.selene.randomizer.DataFluctuationManager;
import com.capgemini.selene.randomizer.RandomEventsManager;

import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class SeleneController {
	
	@RequestMapping(value="/isAlive", method=RequestMethod.GET)
    public String isAlive() {
        return "Bonjour père.";
    }
	
	@RequestMapping(value="/event", method=RequestMethod.GET)
    public SeleneEvent getEvent() {
		RandomEventsManager manager = new RandomEventsManager();
		return manager.getNextEvent();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value="/data", method=RequestMethod.GET)
    public String getData(){
	    SeleneEngine.nextDay();
	    StringBuilder sb = new StringBuilder();
        for(DataFluctuationManager dfm : SeleneDataManager.fluctuationManagers){
            sb.append(dfm.getData().toString()).append(" - ").append(dfm.toString()).append("<br />");
        }
        return sb.toString();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value="/dfm_stats", method=RequestMethod.GET)
    public String getStats(){
	    return DataFluctuationManager.getStats();
    }

    @RequestMapping(value="/next_day", method=RequestMethod.GET)
    public String getNextDay(){
        SeleneEngine.nextDay();
        StringBuilder sb =  new StringBuilder();
        sb.append("Day ");
        sb.append(SeleneEngine.getDayIndex());
        sb.append(". Current day is ");
        sb.append(SeleneEngine.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        return sb.toString();
    }

}
