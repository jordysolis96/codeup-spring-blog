package com.spring.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MathController {

    @RequestMapping(path = "/add/{number1}/and/{number2}")
    @ResponseBody
    public String add(@PathVariable int number1, @PathVariable int number2) {
        return (number1 + " + " + number2 + " = " + (number1 + number2));
    }

    @RequestMapping(path = "/subtract/{number1}/and/{number2}")
    @ResponseBody
    public String subtract(@PathVariable int number1, @PathVariable int number2) {
        return (number1 + " - " + number2 + " = " + (number1 - number2));
    }

    @RequestMapping(path = "/multiply/{number1}/and/{number2}")
    @ResponseBody
    public String multiply(@PathVariable int number1, @PathVariable int number2) {
        return (number1 + " * " + number2 + " = " + (number1 * number2));
    }

    @RequestMapping(path = "/divide/{number1}/and/{number2}")
    @ResponseBody
    public String divide(@PathVariable double number1, @PathVariable double number2) {
        return (number1 + " / " + number2 + " = " + (number1 / number2));
    }

}
