package com.example.demo.controller;

import com.example.demo.model.LotteryResponse;
import com.example.demo.service.XoSoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/xoso")
public class XoSoController {

    @Autowired
    private XoSoService service;

    @GetMapping("/mien-nam")
    public LotteryResponse mienNam() throws Exception {

        LotteryResponse res = new LotteryResponse();
        res.region = "Miền Nam";
        res.date = LocalDate.now().toString();
        res.provinces = service.getMienNam();

        return res;
    }
}

