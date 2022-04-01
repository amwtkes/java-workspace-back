#O(n)
- 它可以看做是`中心法`与一维DP的组合
- 所以字符串必须要经过处理才能用，加`#`
- 引入了一个变量R，对称半径
- 引入C，对称中心
- dp[i]= 以i为中心的最长回文半径Ri

![img.png](img.png)
![img_1.png](img_1.png)
![img_2.png](img_2.png)

