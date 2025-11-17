package com.luckyRepair.seungme.luckySeungCar.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
public class TestController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/test-db")
    public String testConnection() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            return "DB 연결 성공: " + conn.getMetaData().getURL();
        } catch (Exception e) {
            return "DB 연결 실패: " + e.getMessage();
        }
    }
}
