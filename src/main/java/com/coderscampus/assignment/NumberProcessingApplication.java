package com.coderscampus.assignment;

public class NumberProcessingApplication {
    public static void main(String[] args) {

        NumberProcessingService service = new NumberProcessingService();
        service.countAndPrintResults(service.filterNumbers(service.getData()));
    }
}

