package ru.avers.orderId;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CvsReader {


    public Map<String, String> excract2columns(String path) throws IOException {
        Map<String, String> result = new HashMap<>();
        List<String> lines = Files.readAllLines(Paths.get(path));

        //skip first line
        lines = lines.subList(1, lines.size());

        lines.forEach(l -> {
            String[] split = l.split(";");
            result.put(split[1].replace("\"", ""), split[0].replace("\"", ""));
        });
        return result;
    }
}