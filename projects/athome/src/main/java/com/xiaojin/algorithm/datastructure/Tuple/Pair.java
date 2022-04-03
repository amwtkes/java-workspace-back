package com.xiaojin.algorithm.datastructure.Tuple;

public class Pair<T1, T2> {
    public Pair(T1 value1, T2 value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public void setValue1(T1 value1) {
        this.value1 = value1;
    }

    public void setValue2(T2 value2) {
        this.value2 = value2;
    }

    public T1 getValue1() {
        return value1;
    }

    public T2 getValue2() {
        return value2;
    }

    private T1 value1;
    private T2 value2;

    @Override
    public String toString() {
        return this.value1.toString() + " " + this.value2.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Pair) {
            Pair<T1, T2> o1 = (Pair<T1, T2>) o;
            return o1.getValue1() == this.value1 && o1.getValue2() == this.value2;
        }
        return false;
    }
}
