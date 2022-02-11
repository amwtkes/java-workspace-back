package com.xiaojin.algorithm.datastructure.tree;

@FunctionalInterface
public interface TreeNodeAction<T> {
    void doWork(T t);
}
