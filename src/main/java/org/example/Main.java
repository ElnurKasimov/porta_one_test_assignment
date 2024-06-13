package org.example;


import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = readDataFromFile("C:/10m.txt"); //Path to the file with data



        long quantity = numbers.size();
        System.out.println("quantity = " + quantity);

    }


    private static List<Integer> readDataFromFile(String pathToTheFile) {
        List<Integer> numbers = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(pathToTheFile))) {
            numbers = lines
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .map(Integer::parseInt)
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numbers;
    }
}
