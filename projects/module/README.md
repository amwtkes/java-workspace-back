# 常规编译
> 全部使用命令行操作
> [参考](https://my.oschina.net/polly/blog/1543387)
- 编译：javac -d classes src/com/xiaojin/module/Demo.java
- 打包：jar cvf lib/demo.jar -C classes . （需要提前建立lib文件夹）
- 查看jar内容 jar -tvf lib/demo.jar
- 打包MANIFEST文件：jar cmvf ./src/MANIFEST.MF lib/demo.jar -C classes . （需要提前建立MF文件：Main-Class: com.pollyduan.modular.Demo）
