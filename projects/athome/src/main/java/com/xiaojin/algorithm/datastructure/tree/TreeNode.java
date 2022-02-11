package com.xiaojin.algorithm.datastructure.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode<T> {
    private T value;
    private TreeNodePos treeNodePos;
    private TreeNode<T> parent;
    private TreeNode<T> left;
    private TreeNode<T> right;

    @Override
    public String toString() {
        if(value instanceof Integer){
            return Integer.valueOf(value.toString()).toString();
        }
        return "(String) this.value";
    }
}
