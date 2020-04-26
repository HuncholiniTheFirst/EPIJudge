package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SortedListsMerge {
  @EpiTest(testDataFile = "sorted_lists_merge.tsv")
  //@include
  public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1,
                                                      ListNode<Integer> L2) {
   ListNode<Integer> result = new ListNode<>(null,null);
   ListNode<Integer> current = result;

   while(L1 != null)
   {
     Integer l1Val = L1.data;
     Integer l2Val = L2 == null ? null : L2.data;

     if(l2Val == null || l1Val <= l2Val)
     {
       current.next = L1;
       L1 = L1.next;
     }
     else{
       current.next = L2;
       L2 = L2.next;
     }

     current = current.next;
   }

   if(L2 != null){
     current.next = L2;
   }
    return result.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedListsMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
