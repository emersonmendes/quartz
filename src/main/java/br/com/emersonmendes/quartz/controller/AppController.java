package br.com.emersonmendes.quartz.controller;

import br.com.emersonmendes.quartz.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class AppController {

    @Autowired
    private AppService service;

    @GetMapping("/")
    public String index() {
        return "Welcome to Quartz";
    }

    @GetMapping("/schedule")
    public void schedule(@RequestParam String name) {
        service.schedule(name);
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<String>> jobs() {
        return new ResponseEntity<>(service.getJobs(), HttpStatus.OK);
    }

}
