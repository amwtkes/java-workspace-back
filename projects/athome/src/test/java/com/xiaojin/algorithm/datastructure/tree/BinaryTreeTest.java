package com.xiaojin.algorithm.datastructure.tree;

import org.junit.jupiter.api.Test;

class BinaryTreeTest {

    @Test
    void add() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.setWhenReachLastElementOfALevel(integerTreeNode -> System.out.println());
        TreeNode<Integer> root = new TreeNode<>(0, TreeNodePos.Root, null, null, null);

        tree.add(root);
        for (int i = 0; i < 30; i++) {
            TreeNode<Integer> node = new TreeNode<>();
            node.setValue(i + 1);
            tree.add(node);
        }
        System.out.println(tree.getNodeCount());
        tree.travel(node -> System.out.print(node.getValue()));
    }

    @Test
    void travel() {
    }
}
