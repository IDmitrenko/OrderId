package ru.avers.orderId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

@Component
public class OrderService {


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void process(String path) throws IOException, URISyntaxException {
        Map<String, String> epguIdMap = new CvsReader().excract2columns(path);

        String sql = "UPDATE INQRY_SYS_INTERACT SET EXTERNAL01 = ? WHERE EXTERNAL01 = ?";

        epguIdMap.forEach((k, v) -> {
            int i = jdbcTemplate.update(sql, v, k);
            System.out.println("Updated " + i + " rows for EXTERNAL01 = " + k + " and EXTERNAL01 = " + v);
        });
    }
}
