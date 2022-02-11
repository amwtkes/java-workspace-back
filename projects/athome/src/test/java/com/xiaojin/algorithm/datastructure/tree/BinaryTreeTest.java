package com.xiaojin.algorithm.datastructure.tree;

import org.junit.jupiter.api.Test;

class BinaryTreeTest {

    @Test
    void add() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.setWhenReachLastElementOfALevel(integerTreeNode -> {
            System.out.print("--->");
            System.out.println();
        });
        TreeNode<Integer> root = new TreeNode<>(0, TreeNodePos.Root, null, null, null);

        tree.add(root);
        for (int i = 0; i < 450; i++) {
            TreeNode<Integer> node = new TreeNode<>();
            node.setValue(i + 1);
            tree.add(node);
        }
        System.out.println("Total:"+tree.getNodeCount());
        System.out.println("level:"+tree.getLevel());
        System.out.println("=========");
        tree.travel(node -> System.out.print(node.getValue() + " "));

    }

    @Test
    void travel() {
    }
}
