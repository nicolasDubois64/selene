package com.capgemini.selene.controller;

import com.capgemini.selene.engine.SeleneDataManager;
import com.capgemini.selene.engine.SeleneEngine;
import com.capgemini.selene.model.SeleneData;
import com.capgemini.selene.model.SeleneEvent;
import com.capgemini.selene.randomizer.DataFluctuationManager;
import com.capgemini.selene.randomizer.RandomEventsManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SeleneController {

    @RequestMapping(value = "/isAlive", method = RequestMethod.GET)
    public String isAlive() {
        return "Bonjour père.";
    }

    @CrossOrigin(origins = "*")
	@RequestMapping(value="/event", method=RequestMethod.GET)
    public SeleneEvent getEvent() {
		RandomEventsManager manager = new RandomEventsManager();
		return manager.getNextEvent();
	}

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public String getData() {
        SeleneEngine.nextDay();
        StringBuilder sb = new StringBuilder();
        for (DataFluctuationManager dfm : SeleneDataManager.fluctuationManagers) {
            sb.append(dfm.getData().toString()).append(dfm.toString()).append("<br />");
        }
        return sb.toString();
    }

    @RequestMapping(value = "/next_day", method = RequestMethod.GET)
    public String getNextDay() {
        SeleneEngine.nextDay();
        StringBuilder sb = new StringBuilder();
        sb.append("Day ");
        sb.append(SeleneEngine.getDay());
        sb.append(". Current day is ");
        sb.append(SeleneEngine.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        return sb.toString();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/data_json", method = RequestMethod.GET)
    public List<SeleneData> getDataJson() {
        SeleneEngine.nextDay();
        return SeleneDataManager.fluctuationManagers.stream().map(DataFluctuationManager::getData).collect(Collectors.toList());
    }

}
