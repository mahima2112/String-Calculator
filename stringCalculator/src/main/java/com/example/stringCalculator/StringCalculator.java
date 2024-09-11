package com.example.stringCalculator;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

	public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String delimiter = ",|\n";
        if (numbers.startsWith("//")) {
            int delimiterIndex = numbers.indexOf("\n");
            delimiter = numbers.substring(2, delimiterIndex);
            numbers = numbers.substring(delimiterIndex + 1);
        }

        String[] nums = numbers.split(delimiter);
        int sum = 0;
        List<Integer> negativeNumbers = new ArrayList<>();

        for (String num : nums) {
            int number = Integer.parseInt(num);
            if (number < 0) {
                negativeNumbers.add(number);
            } else {
                sum += number;
            }
        }

        if (!negativeNumbers.isEmpty()) {
            // Join the list of negative numbers as a comma-separated string
            String message = "Negative numbers not allowed: " + negativeNumbers.stream()
                .map(String::valueOf) // Convert each negative number to a string
                .reduce((a, b) -> a + ", " + b) // Join them with a comma and space
                .orElse(""); // Default to empty string if no negatives (shouldn't happen)
            throw new IllegalArgumentException(message);
        }

        return sum;
    }
}
