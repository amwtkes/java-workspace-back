package com.xiaojin.algorithm.datastructure.tree;

import lombok.Data;

import java.util.LinkedList;

@Data
public class BinaryTree<T> {
    public static void main(String[] args) {
    }

    private int nodeCount;
    private int level;
    private TreeNode<T> root;
    private TreeNode<T> currentNode;
    private TreeNodeAction<TreeNode<T>> whenReachLastElementOfALevel;

    public void add(TreeNode<T> node) {
        if (this.root == null) {
            this.root = node;
            node.setTreeNodePos(TreeNodePos.Root);
            node.setParent(null);
            this.currentNode = this.root;
            nodeCount++;
            level++;
            return;
        }

        if (this.currentNode == this.root) {
            if (this.root.getLeft() == null) {
                this.root.setLeft(node);
                node.setTreeNodePos(TreeNodePos.Left);
            } else {
                this.root.setRight(node);
                node.setTreeNodePos(TreeNodePos.Right);
            }
            this.currentNode = node;
            node.setParent(this.getRoot());
            nodeCount++;
            level++;
            return;
        }

        if (this.currentNode.getTreeNodePos() == TreeNodePos.Left) {
            this.currentNode.getParent().setRight(node);
            node.setTreeNodePos(TreeNodePos.Right);
            node.setParent(this.currentNode.getParent());
            this.currentNode = node;
            nodeCount++;
            return;
        }
        if (this.currentNode.getTreeNodePos() == TreeNodePos.Right) {
            insertWhenPosRight(this, node);
            nodeCount++;
        }
    }

    private void insertWhenPosRight(BinaryTree<T> tree, TreeNode<T> node) {
        TreeNode<T> currentNode = tree.getCurrentNode();
        TreeNode<T> tmp = currentNode.getParent();
        while (tmp.getTreeNodePos() == TreeNodePos.Right && tmp != tree.getRoot()) {
            tmp = tmp.getParent();
        }
        //找到转折点，并将指针指向转折点的right
        if (tmp.getTreeNodePos() == TreeNodePos.Root) {
            tmp = tmp.getLeft();
            tree.setLevel(tree.getLevel() + 1);
        } else if (tmp.getTreeNodePos() == TreeNodePos.Left) {
            tmp = tmp.getParent().getRight();
        }
        //从转折点的right出发，一路向左，找到插入位置
        while (tmp.getLeft() != null) {
            tmp = tmp.getLeft();
        }
        tmp.setLeft(node);
        node.setParent(tmp);
        node.setTreeNodePos(TreeNodePos.Left);
        tree.setCurrentNode(node);
    }

    public void travel(TreeNodeAction<TreeNode<T>> fromRootAction) {
        LinkedList<TreeNode<T>> queue = new LinkedList<>();
        queue.addFirst(this.getRoot());
        int count = 0;
        int level = 1;
        while (!queue.isEmpty()) {
            TreeNode<T> last = queue.removeLast();
            fromRootAction.doWork(last);
            count++;
            if (count == ((int) Math.pow(2, level)) - 1) {
                this.whenReachLastElementOfALevel.doWork(last);
                level++;
            }

            if (last.getLeft() != null) {
                queue.addFirst(last.getLeft());
            }
            if (last.getRight() != null) {
                queue.addFirst(last.getRight());
            }


        }
    }
}
