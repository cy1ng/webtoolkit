package com.cying.webtoolkit.study.letcode.queue;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks. Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.
 * <p>
 * However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.
 * <p>
 * You need to return the least number of intervals the CPU will take to finish all the given tasks.
 * <p>
 * Example:
 * <p>
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 */
public class TaskScheduler621 {


    public static void main(String[] args) {

    }

    private static int leastInterval(char[] tasks, int n) {

        Map<String, Integer> staticsMap = new HashMap<>();
        for (int i = 0; i < tasks.length; i++) {
            String key = String.valueOf(tasks[i]);
            Integer count = staticsMap.get(key);
            if (count == null) {
                count = 1;
            } else {
                count++;
            }
            staticsMap.put(key, count);
        }



        return 0;
    }
}
