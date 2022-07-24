package com.example.springcalculator.controller;

import com.example.springcalculator.component.Calculator;
import com.example.springcalculator.component.ICalculator;
import com.example.springcalculator.dto.Req;
import com.example.springcalculator.dto.Resp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CalculatorApiController {

    public final Calculator calculator;

    @GetMapping("/sum")
    public int sum(@RequestParam int x, @RequestParam int y){
        return calculator.sum(x,y);
    }

    @PostMapping("/minus")
    public Resp minus(@RequestBody Req req){
        int result = calculator.minus(req.getX(), req.getY());

        Resp resp = new Resp();
        resp.setResult(result);
        resp.setResponse(new Resp.Body());

        return resp;
    }

}
