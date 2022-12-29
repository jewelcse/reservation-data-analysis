package com.data.scraper.salondatascraper.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Service
public class ReservationService {

    @Autowired
    private DataSource dataSource;

    public void exportDataToExcel() throws IOException, SQLException {
        // Connect to the database
        Connection connection = dataSource.getConnection();

        // Retrieve the data
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT total_payable_amount,payment_method FROM reservations");

        // Create the Excel file new XSSFWorkbook();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet 1");

        // Write the data to the file
        int rowCount = 0;
        while (resultSet.next()) {
            Row row = sheet.createRow(++rowCount);

            int columnCount = 0;
            row.createCell(columnCount).setCellValue(resultSet.getString(1));
            row.createCell(++columnCount).setCellValue(resultSet.getString(2));
            // Add more cell values as needed
        }

        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_d_HH_mm_ss");

        String dateString = formatter.format(now);
        try (OutputStream fileOut = new FileOutputStream(dateString+"_file.xlsx")) {
            workbook.write(fileOut);
        }
    }
}
