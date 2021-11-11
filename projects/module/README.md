# 常规编译
> 全部使用命令行操作
> [参考](https://my.oschina.net/polly/blog/1543387)
- 编译：javac -d classes src/com/xiaojin/module/Demo.java
  - 或者：`javac -d classes src/**.java`编译所有
- 打包：jar cvf lib/demo.jar -C classes . （需要提前建立lib文件夹）
- 查看jar内容 jar -tvf lib/demo.jar
- 反编译：`javap classes/module-info.class`
- 运行：
  1. cd到`com`目录，`java com.xiaojin.modular.Demo`
  2. 打包jar lib以后，直接在src目录执行：`Main-Class: com.xiaojin.modular.Demo`
  3. 打包MANIFEST文件：jar cmvf ./src/MANIFEST.MF lib/demo.jar -C classes . （需要提前建立MF文件：Main-Class: com.pollyduan.modular.Demo）
     1. touch src/MANIFEST.MF && echo 'Main-Class: com.xiaojin.modular.Demo'>src/MANIFEST.MF
     2. jar cmvf ./src/MANIFEST.MF lib/demo.jar -C classes .
     3. java -jar lib/demo.jar
  4. 运行模块：`java --module-path lib -m hello/com.xiaojin.modular.Demo`
  5. 模块打包main：`jar --create --file lib/lib.jar --main-class com.xiaojin.modular.Demo -C classes .`打包完就可以这样跑：`java -p lib -m hello`


