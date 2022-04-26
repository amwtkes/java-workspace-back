package com.xiaojin.algorithm.datastructure.tree;

public class SegmentTree {
    // arr[]为原序列的信息从0开始，但在arr里是从1开始的
    // sum[]模拟线段树维护区间和
    // lazy[]为累加和懒惰标记
    // change[]为更新的值
    // update[]为更新慵懒标记
    private int MAXN;
    private int[] arr; //原始数组
    private int[] sum; //线段树数组
    private int[] lazyAdd;
    private int[] lazyUpdate;
    private boolean[] isUpdated; //为什么要多这么个数组？因为更新跟累加不同，更新不能用0或者-1来表示某个范围没有更新，因为更新的值可能就是。所以多一个boolean数组。

    public SegmentTree(int[] origin) {
        MAXN = origin.length + 1;
        arr = new int[MAXN]; // arr[0] 不用 从1开始使用
        for (int i = 1; i < MAXN; i++) {
            arr[i] = origin[i - 1];
        }
        sum = new int[MAXN << 2]; // 某一个范围的累加和信息 . 4N足够存储了。因为是个完全二叉树.
        lazyAdd = new int[MAXN << 2]; // 某一个范围有没有往下传播的累加值
        isUpdated = new boolean[MAXN << 2]; // 某一个范围有没有更新操作的任务
        lazyUpdate = new int[MAXN << 2]; // 某一个范围更新任务，更新成了什么
    }

    private void pushUp(int rt) {
        sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
    }

    // 之前的，所有懒增加，和懒更新，从父范围，发给左右两个子范围
    // 分发策略是什么
    // ln表示左子树元素结点个数，rn表示右子树结点个数

    //因为update为true，说明已经命中相等或者包含，而且
    //lazy会被设置为0，而如果lazy如果不等于0，说明update的操作要早于add。所以先做update在做add的判断。合理。
    /*
     * 这里为什么update的判断是在前？
     * 因为，如果rt为索引的元素是sum这个线段树的一个父节点，进入这个函数说明rt这个元素代表的arr范围需要下推。也就是原来已经需要更新而没有，而被延迟更新的范围
     * 如果再次需要更新，则需要下推1层。为什么是下推1层？
     * 每个节点，只能缓存一个更新信息，如果再来一个，就要下推以便存储新的。
     * 1.add(1,100,4) 对一个范围[1,100]的所有元素+4.
     * 如果arr正好100个元素，则[1,100]是根节点。那么lazyAdd[1]=4.
     * 2.add(3,77,5)在[3,77]范围每个元素再加个5。接到这个调用以后，[1,100]范围的元素有一部分需要更新，有一部分不需要。所以，lazyAdd[1]=4就不对了，也就是失效了
     * 所以需要下推。
     */

    /**
     * 可以根据{@link 线段树.md}文件的图可以看出
     *
     * add,update,query都是相同的递归形式
     * 1. 是要是覆盖不全就下推缓存到下一层
     * 2. 如果是全包就可以计算返回
     * 3. 递归求解
     */
    private void pushDown(int rt, int ln, int rn) {
        /*
         * 更新的是sum数组，update与change数组更新的是上次更改的缓存。
         */
        if (isUpdated[rt]) {
            isUpdated[rt << 1] = true;
            isUpdated[rt << 1 | 1] = true;
            lazyUpdate[rt << 1] = lazyUpdate[rt];
            lazyUpdate[rt << 1 | 1] = lazyUpdate[rt];
            lazyAdd[rt << 1] = 0;
            lazyAdd[rt << 1 | 1] = 0;
            sum[rt << 1] = lazyUpdate[rt] * ln;
            sum[rt << 1 | 1] = lazyUpdate[rt] * rn;
            isUpdated[rt] = false;
        }
        if (lazyAdd[rt] != 0) {
            lazyAdd[rt << 1] += lazyAdd[rt];
            sum[rt << 1] += lazyAdd[rt] * ln;
            lazyAdd[rt << 1 | 1] += lazyAdd[rt];
            sum[rt << 1 | 1] += lazyAdd[rt] * rn;
            lazyAdd[rt] = 0;
        }
    }

    // 在初始化阶段，先把sum数组，填好——sum数组就是线段树
    // 在arr[l~r]范围上，去build，1~N，
    // rt : 这个范围在sum中的下标
    public void build(int l, int r, int rt) {
        if (l == r) {
            sum[rt] = arr[l];
            return;
        }
        int mid = (l + r) >> 1;
        build(l, mid, rt << 1);
        build(mid + 1, r, rt << 1 | 1);
        pushUp(rt);
    }


    // L~R  所有的值变成C
    // l~r  rt
    // 进来就是需要更新这个区域的。从if(L<=mid) or if(R > mid)为入口。
    public void update(int L, int R, int C, int l, int r, int rt) {
        if (L <= l && r <= R) {
            isUpdated[rt] = true;
            lazyUpdate[rt] = C;
            sum[rt] = C * (r - l + 1);
            lazyAdd[rt] = 0;
            return;
        }
        // 当前任务躲不掉，无法懒更新，要往下发
        int mid = (l + r) >> 1;
        //为什么要下推缓存？因为注意rt的含义，rt这个索引代表的范围被遍历了，也就是说目标范围[L,R]在这个rt为索引的范围内，表示要更新这个范围[l,r].
        //没有全包就要下推
        pushDown(rt, mid - l + 1, r - mid);
        if (L <= mid) {
            update(L, R, C, l, mid, rt << 1);
        }
        if (R > mid) {
            update(L, R, C, mid + 1, r, rt << 1 | 1);
        }
        pushUp(rt);
    }

    // L~R, C 任务！
    // rt，l~r
    public void add(int L, int R, int C, int l, int r, int rt) {
        // 任务如果把此时的范围全包了！
        if (L <= l && r <= R) {
            sum[rt] += C * (r - l + 1);
            lazyAdd[rt] += C;
            return;
        }
        // 任务没有把你全包！
        // l  r  mid = (l+r)/2
        int mid = (l + r) >> 1;
        pushDown(rt, mid - l + 1, r - mid);
        // L~R
        if (L <= mid) {
            add(L, R, C, l, mid, rt << 1);
        }
        if (R > mid) {
            add(L, R, C, mid + 1, r, rt << 1 | 1);
        }
        pushUp(rt);
    }

    // 1~6 累加和是多少？ 1~8 rt
    public long query(int L, int R, int l, int r, int rt) {
        if (L <= l && r <= R) {
            return sum[rt];
        }
        int mid = (l + r) >> 1;
        pushDown(rt, mid - l + 1, r - mid);
        long ans = 0;
        if (L <= mid) {
            ans += query(L, R, l, mid, rt << 1);
        }
        if (R > mid) {
            ans += query(L, R, mid + 1, r, rt << 1 | 1);
        }
        return ans;
    }


    public static class Right {
        public int[] arr;

        public Right(int[] origin) {
            arr = new int[origin.length + 1];
            for (int i = 0; i < origin.length; i++) {
                arr[i + 1] = origin[i];
            }
        }

        public void update(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] = C;
            }
        }

        public void add(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] += C;
            }
        }

        public long query(int L, int R) {
            long ans = 0;
            for (int i = L; i <= R; i++) {
                ans += arr[i];
            }
            return ans;
        }
    }

    public static int[] generateRandomArray(int len, int max) {
        int size = (int) (Math.random() * len) + 1;
        int[] origin = new int[size];
        for (int i = 0; i < size; i++) {
            origin[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return origin;
    }

    public static boolean test() {
        int len = 100;
        int max = 1000;
        int testTimes = 5000;
        int addOrUpdateTimes = 1000;
        int queryTimes = 500;
        for (int i = 0; i < testTimes; i++) {
            int[] origin = generateRandomArray(len, max);
            SegmentTree seg = new SegmentTree(origin);
            int S = 1;
            int N = origin.length;
            int root = 1;
            seg.build(S, N, root);
            Right rig = new Right(origin);
            for (int j = 0; j < addOrUpdateTimes; j++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                int C = (int) (Math.random() * max) - (int) (Math.random() * max);
                if (Math.random() < 0.5) {
                    seg.add(L, R, C, S, N, root);
                    rig.add(L, R, C);
                } else {
                    seg.update(L, R, C, S, N, root);
                    rig.update(L, R, C);
                }
            }
            for (int k = 0; k < queryTimes; k++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                long ans1 = seg.query(L, R, S, N, root);
                long ans2 = rig.query(L, R);
                if (ans1 != ans2) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] origin = {2, 1, 1, 2, 3, 4, 5};
        SegmentTree seg = new SegmentTree(origin);
        int S = 1; // 整个区间的开始位置，规定从1开始，不从0开始 -> 固定
        int N = origin.length; // 整个区间的结束位置，规定能到N，不是N-1 -> 固定
        int root = 1; // 整棵树的头节点位置，规定是1，不是0 -> 固定
        int L = 2; // 操作区间的开始位置 -> 可变
        int R = 5; // 操作区间的结束位置 -> 可变
        int C = 4; // 要加的数字或者要更新的数字 -> 可变
        // 区间生成，必须在[S,N]整个范围上build
        seg.build(S, N, root);
        // 区间修改，可以改变L、R和C的值，其他值不可改变
        seg.add(L, R, C, S, N, root);
        // 区间更新，可以改变L、R和C的值，其他值不可改变
        seg.update(L, R, C, S, N, root);
        // 区间查询，可以改变L和R的值，其他值不可改变
        long sum = seg.query(L, R, S, N, root);
        System.out.println(sum);

        System.out.println("对数器测试开始...");
        System.out.println("测试结果 : " + (test() ? "通过" : "未通过"));

    }
}
