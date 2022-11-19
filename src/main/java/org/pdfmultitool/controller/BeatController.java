package org.pdfmultitool.controller;

import org.pdfmultitool.PdfMultiToolApplication;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/BeatController")
public class BeatController {
    private int beats = 0;

    @RequestMapping("/checkBeats")
    public String checkHeartbeats() {

        beats = 0;
        return "index";
    }

    @Scheduled(fixedRate = 2000)
    public void schedule() {

        beats++;
        if(beats > 5) {
            PdfMultiToolApplication.shutdownApplication();
        }
    }
}