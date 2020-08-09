package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class IsAnonymousLetterConstructible {
    @EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")

    public static boolean isLetterConstructibleFromMagazine(String letterText,
                                                            String magazineText) {
        /*
        Translation: chars() produces an intstream (integers representing the chars), they get mapped to chars and then
        collected, grouped by identity, which is the char itself, then we have a second collector that takes that stream of chars and counts them
         */
        Map<Character, Long> letterCountMap = letterText.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for(Character magChar : magazineText.toCharArray())
        {
            if(letterCountMap.containsKey(magChar))
            {
                Long aLong = letterCountMap.get(magChar) - 1;
                if (aLong.compareTo(0l) == 0) {
                    letterCountMap.remove(magChar);
                } else {
                    letterCountMap.put(magChar, aLong);
                }
            }
            if(letterCountMap.isEmpty())
            {
                break;
            }
        }
        return letterCountMap.isEmpty();
    }
    /*
    In the worst case this would keep going until either the magazine characters are exhausted or the last character
    in the magazine results in the entire letter being covered
     */

    // If the characters are ASCII then there are only 256 characters available and you could use an array of 256 characters
    public static boolean isLetterConstructibleFromMagazine2(String letterText,
                                                             String magazineText) {
        /*
        DO THE ABOVE WITH AN ARRAY OF 256 CHARS
         */
        return true;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsAnonymousLetterConstructible.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
