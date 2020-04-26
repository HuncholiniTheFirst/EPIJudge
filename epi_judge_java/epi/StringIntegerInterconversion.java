package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
public class StringIntegerInterconversion {
/*
USEFUL: https://stackoverflow.com/questions/5444611/math-abs-returns-wrong-value-for-integer-min-value
 */
  public static String intToString(int x) {
    boolean isNegative = false;
    if(x == 0)
    {
      return "0";
    }
    if (x < 0) {
      isNegative = true;
    }
    String intToReturn = "";
    while (x != 0) {
      int lastDigit = Math.abs(x % 10);
      char lastDigitChar = (char) (lastDigit + '0');
      intToReturn = lastDigitChar + intToReturn;
      x = x/10;
    }

    return isNegative ? "-" + intToReturn : intToReturn;

  }

  public static int stringToInt(String s) {
    int result = 0;
    int multiplier = 1;
    boolean isNegative = false;
    if(s.charAt(0) == '-'){
      isNegative = true;
      s = s.substring(1);
    }
    if(s.charAt(0) == '+'){
      s=s.substring(1);
    }

    for(int i = s.length()-1; i >= 0; i--)
    {
      char number = s.charAt(i);
      int numberInt = Character.getNumericValue(number);
      numberInt = numberInt*multiplier;
      result = result + numberInt;
      multiplier = multiplier*10;
    }
    return isNegative ? -result : result;
  }

  @EpiTest(testDataFile = "string_integer_interconversion.tsv")
  public static void wrapper(int x, String s) throws TestFailure {
    if (Integer.parseInt(intToString(x)) != x) {
      throw new TestFailure("Int to string conversion failed");
    }
    if (stringToInt(s) != x) {
      throw new TestFailure("String to int conversion failed");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
