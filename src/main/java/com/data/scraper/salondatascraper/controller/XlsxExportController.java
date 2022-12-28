package com.data.scraper.salondatascraper.controller;


import com.data.scraper.salondatascraper.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@AllArgsConstructor
@RequestMapping("/export")
public class XlsxExportController {

    private final ReservationService service;


    @GetMapping
    public String export() throws SQLException, IOException {

        service.exportDataToExcel();

        return "completed";
    }
}
