# 关于模块
- Spring5还没有模块化，所以使用`automatic module`的形式来使用。关于[automatic module](http://tutorials.jenkov.com/java/modules.html#automatic-modules)
- 编译运行的命令：
```shell
/Library/Java/JavaVirtualMachines/jdk-11.0.12.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=58556:/Applications/IntelliJ IDEA.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/xiaojin/.m2/repository/org/springframework/spring-aop/5.3.13/spring-aop-5.3.13.jar:/Users/xiaojin/.m2/repository/org/springframework/spring-core/5.3.13/spring-core-5.3.13.jar:/Users/xiaojin/.m2/repository/org/springframework/spring-jcl/5.3.13/spring-jcl-5.3.13.jar:/Users/xiaojin/.m2/repository/org/springframework/spring-expression/5.3.13/spring-expression-5.3.13.jar -p /Users/xiaojin/workspace/my_github/java-workspace/spring-study/target/classes:/Users/xiaojin/.m2/repository/org/projectlombok/lombok/1.18.22/lombok-1.18.22.jar:/Users/xiaojin/.m2/repository/org/springframework/spring-beans/5.3.13/spring-beans-5.3.13.jar:/Users/xiaojin/.m2/repository/org/springframework/spring-context/5.3.13/spring-context-5.3.13.jar -m spring.study/com.xiaojin.App
```
`-p`说明是模块化运行的。
