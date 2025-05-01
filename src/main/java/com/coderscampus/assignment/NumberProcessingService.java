package com.coderscampus.assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class NumberProcessingService {

    private final Assignment8 assignment = new Assignment8();

    public List<Integer> getData() {
        ExecutorService pool = Executors.newCachedThreadPool();
        List<CompletableFuture<List<Integer>>> tasks = new ArrayList<>();
        List<Integer> allNumbers = new ArrayList<>();

        try {
            for (int i = 0; i < 1000; i++) {
                CompletableFuture<List<Integer>> task = CompletableFuture.supplyAsync(assignment::getNumbers, pool);
                tasks.add(task);
            }

            CompletableFuture.allOf(tasks.toArray(new CompletableFuture[0])).join();
            for (CompletableFuture<List<Integer>> task : tasks) {
                allNumbers.addAll(task.join());
            }
        } finally {
            shutdownExecutor(pool);
        }

        return allNumbers;
    }

    private void shutdownExecutor(ExecutorService pool) {
        pool.shutdown();
        try {
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow();
                if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.err.println("Pool did not terminate");
                }
            }
        } catch (InterruptedException e) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    public List<Integer> filterNumbers(List<Integer> numbers) {
        return numbers;
    }

    public void countAndPrintResults(List<Integer> numbers) {
        Map<Integer, Long> numberMap = numbers.parallelStream()
                .collect(Collectors.groupingByConcurrent(number -> number,
                Collectors.counting()));

        numberMap.forEach((number, count) -> System.out.println(number + "=" + count));
    }
}
