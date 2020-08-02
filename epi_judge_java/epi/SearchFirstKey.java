package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class SearchFirstKey {
  @EpiTest(testDataFile = "search_first_key.tsv")

  public static int searchFirstOfK(List<Integer> A, int k) {
    int l = 0;
    int r = A.size()-1;
    int result = -1;
    while (l <= r) {
      int i = (l + r) / 2;
      int arrayVal = A.get(i);
      if (arrayVal < k) {
        l = i + 1;
      }
      if (arrayVal == k) {
        result = i;
        r = i - 1;
      }
      if (arrayVal > k) {
        r = i - 1;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstKey.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
