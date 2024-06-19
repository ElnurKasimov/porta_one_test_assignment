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
//        List<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6).toList();
        List<Integer> sorted = sortList(numbers);

        System.out.println("Task 1");
        System.out.println("Max value in the input data : " + sorted.get(0));
        System.out.println("Task 2");
        System.out.println("Min value in the input data : " + sorted.get(sorted.size() - 1));
        System.out.println("Task 3");
        System.out.println("Median value in the input data : " + findMedian(sorted));
        System.out.println("Task 4");
        System.out.println("Average value in the input data : " + findAverage(sorted));

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

    private static List<Integer> sortList(List<Integer> inputList) {
        if (inputList.size() <= 1) {
            return inputList;
        }
        if (inputList.size() == 2) {
            List<Integer> result = new ArrayList<>();
            if (inputList.get(0) <= inputList.get(1)) {
                result.add(inputList.get(0));
                result.add(inputList.get(1));
            } else {
                result.add(inputList.get(1));
                result.add(inputList.get(0));

            }
            return result;
        }
        int pivot = inputList.size() / 2;
        Integer pivotValue = inputList.get(pivot);
        List<Integer> less = new ArrayList<>();
        List<Integer> greater = new ArrayList<>();
        for (int i = 0; i < inputList.size(); i++) {
            if (i != pivot) {
                if (inputList.get(i) <= pivotValue) {
                    less.add(inputList.get(i));
                } else {
                    greater.add(inputList.get(i));
                }
            }
        }
        List<Integer> result = new ArrayList<>(sortList(less));
        result.add(pivotValue);
        result.addAll(sortList(greater));
        return result;
    }


    private static double findMedian(List<Integer> list) {
        if (list.size() %2 != 0) {
            return list.get(list.size()/2);
        } else {
        return (list.get(list.size()/2 - 1) + list.get(list.size()/2)) * 1. / 2;
        }
    }

    private static double findAverage(List<Integer> list) {
        long sum = 0;
        for (Integer number : list) {
            sum += number;
        }
        return sum * 1. / list.size();
    }


}
