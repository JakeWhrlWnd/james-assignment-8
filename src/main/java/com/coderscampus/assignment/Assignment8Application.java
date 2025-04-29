package com.coderscampus.assignment;

import com.coderscampus.assignment.service.NumberProcessingService;

import java.util.List;

public class Assignment8Application {
    public static void main(String[] args) {

        NumberProcessingService service = new NumberProcessingService();
        service.processNumbers();
    }
}

