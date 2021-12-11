package ru.avers.orderId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class OrderIdApplication implements ApplicationRunner {

    @Autowired
    private OrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(OrderIdApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<String> path = args.getOptionValues("path");
        if (path != null && path.size() == 1) {
            orderService.process(path.get(0));
        }

        System.exit(0);
    }
}
