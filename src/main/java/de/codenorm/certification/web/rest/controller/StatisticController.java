package de.codenorm.certification.web.rest.controller;

import de.codenorm.certification.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rest.model.DashboardDto;

@RestController
@RequestMapping(value = "/api")
public class StatisticController {

    @Autowired
    private StatisticService statisticService;


    @RequestMapping(value = "/statistic", method = RequestMethod.GET)
    public DashboardDto find( ) {
        return statisticService.find();
    }

}