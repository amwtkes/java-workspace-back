package com.xiaojin.algorithm.datastructure.tree;

import lombok.Builder;
import lombok.Data;

import java.util.LinkedList;
import java.util.Stack;

@Data
public class CompleteBinaryTree<T> {
    public static void main(String[] args) {
    }

    private int nodeCount;
    private int level;
    private TreeNode<T> root;
    private TreeNode<T> currentNode;
    private TreeNodeAction<TreeNode<T>> whenReachLastElementOfALevel;

    public void add(TreeNode<T> node) {
        //第一个节点插入情况
        if (this.root == null) {
            this.root = node;
            node.setTreeNodePos(TreeNodePos.Root);
            node.setParent(null);
            this.currentNode = this.root;
            nodeCount++;
            level++;
            return;
        }
        //第二层插入情况
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
        //如果刚插完左子树，无论什么情况都是插右子树
        if (this.currentNode.getTreeNodePos() == TreeNodePos.Left) {
            this.currentNode.getParent().setRight(node);
            node.setTreeNodePos(TreeNodePos.Right);
            node.setParent(this.currentNode.getParent());
            this.currentNode = node;
            nodeCount++;
            return;
        }
        //如果刚刚插完右子树，那么情况就复杂了
        if (this.currentNode.getTreeNodePos() == TreeNodePos.Right) {
            insertWhenPosRight(this, node);
            nodeCount++;
        }
    }

    private void insertWhenPosRight(CompleteBinaryTree<T> tree, TreeNode<T> node) {
        TreeNode<T> currentNode = tree.getCurrentNode();
        TreeNode<T> tmp = currentNode.getParent();
        //循环向上取父亲节点，判断是否是右子树或者已经到了根。这个时候就是转折，到了转折就该递归向左直到找到空位插入。
        while (tmp.getTreeNodePos() == TreeNodePos.Right && tmp != tree.getRoot()) {
            tmp = tmp.getParent();
        }
        //找到转折点，并将指针指向转折点的right
        if (tmp.getTreeNodePos() == TreeNodePos.Root) {
            tmp = tmp.getLeft();
            //只有最右侧叶子结点才能表示是新的层。最右侧的右子树子节点会一直递归到树根。
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

    @Builder
    @Data
    static class TravelContext {
        private int count;
        private int level;
    }

    /*
    利用队列+栈的方式进行交错输出
    队列是顺序，栈是逆序。所以将顺序的元素加入栈就变成逆序，再入一次栈就是顺序，从而实现交错输出的效果。
    1、入栈的时候，左右子节点谁先入栈要考虑。
    2、逆序的时候左-》右
    3、顺序的时候右-》左
    4、每次切换左右入栈顺序的前提是，队列里的元素都退干净了。
    * */
    public void SwirlTravel(TreeNodeAction<TreeNode<T>> fromRootAction) {
        LinkedList<TreeNode<T>> queue = new LinkedList<>();
        Stack<TreeNode<T>> stack = new Stack<>();
        TravelContext context = new TravelContext(0, 1);
        queue.addFirst(this.getRoot());
        boolean isSequential = true;
        TreeNode<T> first;
        while (!queue.isEmpty()) {
            //队列必须退干净然后改变入栈的顺序。
            while (!queue.isEmpty()) {
                first = queue.removeFirst();
                dealWithNode(context, first, fromRootAction);
                if (isSequential) {//当输出逆序的时候，先左入栈再右
                    if (first.getLeft() != null) {
                        stack.push(first.getLeft());
                    }
                    if (first.getRight() != null) {
                        stack.push(first.getRight());
                    }
                } else {
                    if (first.getRight() != null) {
                        stack.push(first.getRight());
                    }
                    if (first.getLeft() != null) {
                        stack.push(first.getLeft());
                    }
                }
            }
            isSequential = !isSequential;
            while (!stack.isEmpty()) {
                queue.addLast(stack.pop());
            }
        }
    }

    private void dealWithNode(TravelContext context, TreeNode<T> first, TreeNodeAction<TreeNode<T>> fromRootAction) {
        //dowork
        fromRootAction.doWork(first);

        context.setCount(context.getCount() + 1);
        //判断是否是层结束
        if (context.getCount() == ((int) Math.pow(2, context.getLevel())) - 1) {
            this.whenReachLastElementOfALevel.doWork(first);
            context.setLevel(context.getLevel() + 1);
        }
    }
}
