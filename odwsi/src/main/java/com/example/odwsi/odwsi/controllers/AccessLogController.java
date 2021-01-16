package com.example.odwsi.odwsi.controllers;

import com.example.odwsi.odwsi.service.access_log.AccessLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class AccessLogController {

    private final AccessLogService accessLogService;

    @GetMapping("/access-log")
    public ModelAndView getAccessLog() {
        ModelAndView modelAndView = new ModelAndView("access-log");
        modelAndView.addObject("accessLogEntries", accessLogService.getAccessLogEntries());
        return modelAndView;
    }

}
