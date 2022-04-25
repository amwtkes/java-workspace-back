>来自Amazon
> 
> 给定一个数组arr，含有n个数字，可能有正、有负、有0
> 
> 给定一个正数k
> 
> 返回所有子序列中，累加和最大的前k个子序列累加和
> 
> 假设K不大，怎么算最快？
> 

- 根据前面的求最小的前k个子序列累加和的算法，我们是不能照搬的，因为不能那种分裂方式不能用于构建大顶堆。因为有负数
- 但是可以借用前面的算法，将这个问题转化为小顶堆求解。
  - 整个数组的最大和是可以O(n)算出来的（所有正数相加即可，这是最大的子序列top1）
  - 其余的top k-1一定是在这个数之上减去一个值得到的
  - (⊙o⊙)… 发现规律了吗？
  - 减去一个值就是前k小的子序列和吗？
  - 但是问题是有负数怎么办？有负数也没法用那个分裂算法。因为负数+负数=更小的负数
  - 那就将他求反变成正数，然后就解了。
  - ^_^想想为什么？
  - 提示：减去一个正数元素，表示从这个最大子序列中删除一个元素
  - 减去一个原本是负数的正数元素呢？等于加上这个负数嘛，不是就行了吗？
  - o(*￣︶￣*)o
### 代码：
```java
// 来自Amazon
// 给定一个数组arr，含有n个数字，可能有正、有负、有0
// 给定一个正数k
// 返回所有子序列中，累加和最大的前k个子序列累加和
// 假设K不大，怎么算最快？
public class Code07_TopMaxSubsquenceSum {

	public static int[] topMaxSum1(int[] arr, int k) {
		ArrayList<Integer> allAns = new ArrayList<>();
		process(arr, 0, 0, allAns);
		allAns.sort((a, b) -> a.compareTo(b));
		int[] ans = new int[k];
		for (int i = allAns.size() - 1, j = 0; j < k; i--, j++) {
			ans[j] = allAns.get(i);
		}
		return ans;
	}

	public static void process(int[] arr, int index, int sum, ArrayList<Integer> ans) {
		if (index == arr.length) {
			ans.add(sum);
		} else {
			process(arr, index + 1, sum, ans);
			process(arr, index + 1, sum + arr[index], ans);
		}
	}

	public static int[] topMaxSum2(int[] arr, int k) {
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] >= 0) {
				sum += arr[i];
			} else {
				arr[i] = -arr[i];
			}
		}
		int[] ans = topMinSum(arr, k);
        //最后求解的过程，依次减去topk小的子序列。
		for (int i = 0; i < ans.length; i++) {
			ans[i] = sum - ans[i];
		}
		return ans;
	}

	public static int[] topMinSum(int[] arr, int k) {
		Arrays.sort(arr);
		PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		heap.add(new int[] { 0, arr[0] });
		int[] ans = new int[k];
		for (int i = 1; i < k; i++) {
			int[] cur = heap.poll();
			int last = cur[0];
			int sum = cur[1];
			ans[i] = sum;
			if (last + 1 < arr.length) {
				heap.add(new int[] { last + 1, sum - arr[last] + arr[last + 1] });
				heap.add(new int[] { last + 1, sum + arr[last + 1] });
			}
		}
		return ans;
	}

	// 为了测试
	public static int[] randomArray(int len, int value) {
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			arr[i] = (int) (Math.random() * value) + 1;
		}
		return arr;
	}

	// 为了测试
	public static boolean equals(int[] ans1, int[] ans2) {
		if (ans1.length != ans2.length) {
			return false;
		}
		for (int i = 0; i < ans1.length; i++) {
			if (ans1[i] != ans2[i]) {
				return false;
			}
		}
		return true;
	}

	// 为了测试
	public static void main(String[] args) {
		int n = 10;
		int v = 40;
		int testTime = 5000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int len = (int) (Math.random() * n) + 1;
			int[] arr = randomArray(len, v);
			int k = (int) (Math.random() * ((1 << len) - 1)) + 1;
			int[] ans1 = topMaxSum1(arr, k);
			int[] ans2 = topMaxSum2(arr, k);
			if (!equals(ans1, ans2)) {
				System.out.println("出错了！");
				System.out.print("arr : ");
				for (int num : arr) {
					System.out.print(num + " ");
				}
				System.out.println();
				System.out.println("k : " + k);
				for (int num : ans1) {
					System.out.print(num + " ");
				}
				System.out.println();
				for (int num : ans2) {
					System.out.print(num + " ");
				}
				System.out.println();
				break;
			}
		}
		System.out.println("测试结束");
	}

}
```
