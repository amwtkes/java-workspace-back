## Spring5暂时不支持java的module
所以java11会自动使用`Automatic Modules`来为APP适配。
也就是默认导出所有的`packages`

>换句话说就是当你的app定义了`module-info.java`而引用的其他jar包没有导出，就是自动全部导出。

- 另外在module-info.java中加入`open`关键字就表示可以反射导出包中的类。否则不行。
