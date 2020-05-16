package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class SortedArraysMerge {
    public static class ArrayEntry {

        public ArrayEntry(int value, int arrayID) {
            this.value = value;
            this.arrayID = arrayID;
        }

        int value;
        int arrayID;
    }

    @EpiTest(testDataFile = "sorted_arrays_merge.tsv")


    public static List<Integer> mergeSortedArrays(List<List<Integer>> sortedArrays) {
        List<Integer> result = new ArrayList<>();
        List<Iterator<Integer>> iterators = new ArrayList<>();
        for (List<Integer> sortedArray : sortedArrays) {
            iterators.add(sortedArray.iterator());
        }

        PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>(sortedArrays.size(), Comparator.comparingInt(a -> a.value));

        //initialise heap

        for (int i = 0; i < iterators.size(); i++) {
            if (iterators.get(i).hasNext()) {
                minHeap.add(new ArrayEntry(iterators.get(i).next(), i));
            }
        }

        while (!minHeap.isEmpty()) {
            ArrayEntry poll = minHeap.poll();
            result.add(poll.value);
            if (iterators.get(poll.arrayID).hasNext()) {
                Integer next = iterators.get(poll.arrayID).next();
                minHeap.add(new ArrayEntry(next, poll.arrayID));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SortedArraysMerge.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
