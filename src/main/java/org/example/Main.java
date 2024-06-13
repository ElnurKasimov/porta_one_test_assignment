package org.example;


import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        String fileName = "C:/10m.txt";
        List<Integer> numbers = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            numbers = lines
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .map(Integer::parseInt)
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }


        long quantity = numbers.size();
        System.out.println("quantity = " + quantity);

    }
}
