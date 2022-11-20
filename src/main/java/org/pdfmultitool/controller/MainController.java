package org.pdfmultitool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String index(){

        return "index";
    }

    @PostMapping("/error")
    public String error(){

        return "error";
    }

    @PostMapping("/error/goback")
    public String goback(){

        return "index";
    }

//    @PostMapping("/merge")
//    public String merge(){
//
//        return "merge";
//    }

    @PostMapping("/split")
    public String split(){

        return "split";
    }

}