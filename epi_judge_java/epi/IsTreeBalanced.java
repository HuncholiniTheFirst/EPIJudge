package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeBalanced {

    @EpiTest(testDataFile = "is_tree_balanced.tsv")

    public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
        int result = isTreeBalanced(tree, 0);
        return result == -1 ? false : true;
    }

    private static int isTreeBalanced(BinaryTreeNode<Integer> tree, int currentHeight) {
        if (tree == null) {
            return currentHeight;
        }

        int leftTree = isTreeBalanced(tree.left, currentHeight);
        int rightTree = isTreeBalanced(tree.right, currentHeight);

        if (leftTree == -1 || rightTree == -1) {
            return -1;
        }
        if (Math.abs(leftTree - rightTree) > 1) {
            return -1;
        } else return (Math.max(leftTree, rightTree) + 1);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
