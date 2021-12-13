package ru.avers.orderId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

@Component
public class OrderService {

    static int update = 0;
    static int nofind = 0;

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
            if (i == 1) {
                update++;
            } else {
                nofind++;
            }
            System.out.println("Updated " + i + " rows for EXTERNAL01 = " + k + " and EXTERNAL01 = " + v);
        });
        System.out.println("Итого : Обновлено значений epgu_id - " + update + ". " +
                "Не найдено значений по ключу ku_id - " + nofind);
    }
}
