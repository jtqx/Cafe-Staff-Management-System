import java.util.*;

class Solution {
    public int[] frequencySort(int[] nums) {
        // Step 1: Count the frequency of each number
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Sort using a custom comparator
        Arrays.sort(nums, (a, b) -> {
            int freqA = freqMap.get(a), freqB = freqMap.get(b);
            if (freqA != freqB) {
                return Integer.compare(freqA, freqB); // Sort by frequency (ascending)
            } 
            return Integer.compare(b, a); // If same frequency, sort by value (descending)
        });

        return nums;
    }
}

import java.util.*;

public class Solution {
    public int[] arrayRankTransform(int[] arr) {
        // Edge case: if the array is empty, return an empty array
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        
        // Create a copy of the original array and sort it
        int[] sortedArr = arr.clone();
        Arrays.sort(sortedArr);
        
        // Map each element in the sorted array to its rank
        Map<Integer, Integer> rankMap = new HashMap<>();
        int rank = 1;
        
        for (int num : sortedArr) {
            if (!rankMap.containsKey(num)) {
                rankMap.put(num, rank++);
            }
        }
        
        // Create the result array by replacing each element in the original array with its rank
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = rankMap.get(arr[i]);
        }
        
        return result;
    }
}

import java.util.Arrays;

public class Solution {
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        // Sort both horizontal and vertical cuts
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        
        // Find the maximum gap in horizontal direction
        int maxHGap = Math.max(horizontalCuts[0], h - horizontalCuts[horizontalCuts.length - 1]);
        for (int i = 1; i < horizontalCuts.length; i++) {
            maxHGap = Math.max(maxHGap, horizontalCuts[i] - horizontalCuts[i - 1]);
        }
        
        // Find the maximum gap in vertical direction
        int maxVGap = Math.max(verticalCuts[0], w - verticalCuts[verticalCuts.length - 1]);
        for (int i = 1; i < verticalCuts.length; i++) {
            maxVGap = Math.max(maxVGap, verticalCuts[i] - verticalCuts[i - 1]);
        }
        
        // The area is the product of the maximum horizontal and vertical gaps
        long maxArea = (long) maxHGap * maxVGap;
        
        // Since the area can be large, return the result modulo 10^9 + 7
        return (int)(maxArea % 1000000007);
    }
}
