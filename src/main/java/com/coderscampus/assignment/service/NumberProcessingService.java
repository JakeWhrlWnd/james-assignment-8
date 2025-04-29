package com.coderscampus.assignment.service;

import com.coderscampus.assignment.Assignment8;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NumberProcessingService {

    private final Assignment8 assignment = new Assignment8();

    public void processNumbers() {
        List<Integer> numbers = fetchNumbers();
        List<Integer> filteredNumbers = filterNumbers(numbers);
        Map<Integer, Integer> numberMap = countNumberOccurrences(filteredNumbers);
        printResults(numberMap);
    }

    private List<Integer> fetchNumbers() {
        return assignment.getData();
    }

    private List<Integer> filterNumbers(List<Integer> numbers) {
        return numbers;
    }

    private Map<Integer, Integer> countNumberOccurrences(List<Integer> numbers) {
        Map<Integer, Integer> numberMap = new HashMap<>();

        if (numbers == null || numbers.isEmpty()) {
            return numberMap;
        }

        int min = Collections.min(numbers);
        int max = Collections.max(numbers);

        for (int i = min; i <= max; i++) {
            numberMap.put(i, 0);
        }

        for (Integer number : numbers) {
            numberMap.merge(number, 1, Integer::sum);
        }

        return numberMap;
    }

    private void printResults(Map<Integer, Integer> numberMap) {
        String numberOutput = numberMap.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining(","));

        System.out.println(numberOutput);
    }
}
