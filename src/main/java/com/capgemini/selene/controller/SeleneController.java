package com.capgemini.selene.controller;

import com.capgemini.selene.engine.SeleneDataManager;
import com.capgemini.selene.engine.SeleneEngine;
import com.capgemini.selene.model.*;
import com.capgemini.selene.parser.SeleneDataParser;
import com.capgemini.selene.randomizer.DataFluctuationManager;
import com.capgemini.selene.randomizer.RandomEventsManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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
    @RequestMapping(value = "/event", method = RequestMethod.GET)
    public SeleneEvent getEvent() {
        RandomEventsManager manager = new RandomEventsManager();
        return manager.getNextEvent();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public List<SeleneEvent> getEvents() {
        return SeleneDataParser.getSeleneEvents();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/doc", method = RequestMethod.GET)
    public String getDoc() {
        return SeleneDataParser.getDoc();
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

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/data2", method = RequestMethod.GET, produces = {"application/json"})
    public String getData2() {
        SeleneEngine.nextDay();

        List<SeleneDataPOJO> data = SeleneDataManager.fluctuationManagers.stream().map(dfm -> new SeleneDataPOJO(dfm.getData())).collect(Collectors.toList());

        RandomEventsManager manager = new RandomEventsManager();
        SeleneEventPOJO event = new SeleneEventPOJO(manager.getNextEvent());

        ObjectMapper mapper = new ObjectMapper();
        String s = "";
        try {
            SelenePOJO pojo = new SelenePOJO(SeleneEngine.getDate(), data, event);
            s = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pojo);
            mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return s;
    }

    @CrossOrigin(origins = "*")
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

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/waterleau", method = RequestMethod.GET)
    public void purgeWater() {
        SeleneEngine.purge(Kind.WATER);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/chauffe_marcel", method = RequestMethod.GET)
    public void purgeAir() {
        SeleneEngine.purge(Kind.AIR);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/space_deliveroo", method = RequestMethod.GET)
    public void purgeFood() {
        SeleneEngine.purge(Kind.FOOD);
    }

}
