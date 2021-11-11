官方文档：[https://docs.oracle.com/javase/9/index.html](https://www.oschina.net/action/GoToLink?url=https%3A%2F%2Fdocs.oracle.com%2Fjavase%2F9%2Findex.html)

关于 java9的新特性，官方原文：[https://docs.oracle.com/javase/9/whatsnew/toc.htm](https://www.oschina.net/action/GoToLink?url=https%3A%2F%2Fdocs.oracle.com%2Fjavase%2F9%2Fwhatsnew%2Ftoc.htm)

这玩意就是一个列表，具体的技术细节需要根据官方文档挖一挖。

## modular-模块系统

java9的模块化，从一个独立的开源项目而来，名为Jigsaw。

项目官网：[http://openjdk.java.net/projects/jigsaw/](https://www.oschina.net/action/GoToLink?url=http%3A%2F%2Fopenjdk.java.net%2Fprojects%2Fjigsaw%2F)

### 为什么要使用模块化

java开发者都知道，使用java开发应用程序都会遇到一个问题，Jar hell，他就像windows里的dll hell。

比如我们启动一个不算大的应用，但依赖了很多的jar，如下图：

![输入图片说明](https://static.oschina.net/uploads/img/201709/26094041_w0RP.png "在这里输入图片标题")

摘自：Mark Reinhold的演讲 [https://www.youtube.com/watch?v=l1s7R85GF1A](https://www.oschina.net/action/GoToLink?url=https%3A%2F%2Fwww.youtube.com%2Fwatch%3Fv%3Dl1s7R85GF1A)

这是很常见的。虽然你可以使用 "java -Djava.ext.dirs=lib xxx" 让命令行小一些，但不可否认，他的classpath就是那么长。顺便说一句，java9中不允许使用extdirs了。

另一方面，jdk本身有很多的api：

![输入图片说明](https://static.oschina.net/uploads/img/201709/26094131_JZAh.png "在这里输入图片标题")

对于一些小设备，它太庞大了。

### helloworld

还是习惯先来一个helloworld。在此之前，需要先检查一下你的java版本：

```
java -version
java version "9"
Java(TM) SE Runtime Environment (build 9+181)
Java HotSpot(TM) 64-Bit Server VM (build 9+181, mixed mode)
```

如果不是java9，而是 1.8、1.7，那么慢走不送。

#### 创建主类

首先创建一个java类，就叫Demo吧。

文件保存为：src/com/pollyduan/modular/Demo.java

```java
package com.pollyduan.modular;

public class Demo{
    public static void main(String[] args){
        System.out.println("hello modular.");
    }
}
```

#### 编译：

```
$ javac -d classes src/**.java
$ tree .
.
├── classes
│   └── com
│       └── pollyduan
│           └── modular
│               └── Demo.class
└── src
    └── com
        └── pollyduan
            └── modular
                └── Demo.java
```

#### 打包jar并执行

```
$ mkdir lib

$ jar cvf lib/demo.jar -C classes .

$ java --class-path lib/demo.jar com.pollyduan.modular.Demo

hello modular.
```

--class-path 开关可以简写：

```
$ java -cp lib/demo.jar com.pollyduan.modular.Demo
```

当然我们可以为jar指定主类，来简化运行：

```
Main-Class: com.pollyduan.modular.Demo
```

需要在MANIFEST.MF 中增加上面一行，即可直接运行：

```
$ java -jar lib/demo.jar
```

#### 创建模块

src/module-info.java

```java
module hello{}
```

我们写了一个空的模块，命名为hello。

#### 编译模块

```
$ javac -d classes src/**.java
```

反编译看一下：

```java
$ javap classes/module-info.class
Compiled from "module-info.java"
module hello {
  requires java.base;
}
```

为什么我们写了一个空的模块，反编译多了一行？先不用管，后面会说明为什么。

#### 打包模块

```
$ jar cvf lib/hello.jar -C classes .
$ jar tf lib/hello.jar
META-INF/
META-INF/MANIFEST.MF
module-info.class
com/
com/pollyduan/
com/pollyduan/modular/
com/pollyduan/modular/Demo.class
```

#### 运行模块

```
$ java --module-path lib -m hello/com.pollyduan.modular.Demo

hello modular.
```

这里和传统的执行jar不一样了，这里不需要classpath，而是module-path。

同样命令行可以简写成：

```
java -p lib -m hello/com.pollyduan.modular.Demo
```

模块可以增加Main-Class 吗？java9的jar提供了一个create开关，用这种方式打包，可以为module指定主类：

```
$ jar --create --file lib/lib.jar --main-class com.pollyduan.modular.Demo -C classes .
```

再次运行模块，命令行就会更简单了。

```
$ java -p lib -m hello
```

### Jigsaw的设计目标

让开发者构建和维护一个大型的库或应用程序更容易；

提高javaSE平台及JDK实现的安全性和可维护性；

提升应用的性能；

在javase及JDK平台，让应用更小以便于部署于更小的计算单元及紧密的云部署系统。

### 什么是modules

为了解决这些问题，jdk在package上层，封装了一层。

```
module -> package -> class/interface
```

那到底 module 是什么？

```
module 是一些包的容器。

依赖它的应用称之为模块，模块是有名字的，其他模块使用该名字使用它。

module导出特定的包，仅供依赖它的包使用。
```

module是一个包的容器。module仅仅需要导出模块依赖的包。

### 创建一个module

#### 声明一个module

cat module-info.java

```java
module com.foo.bar{
  exports com.foo.bar.alpha;
  exports com.foo.bar.beta;
}
```

和package-info.java 类似，它也用一个独立的java文件保存，名为 module-info.java。

#### 创建需要导出的类

暂时，类的内容不重要，可以先写一个空类，这里只列出目录结构：

```
$ tree .
.
├── com
│   └── foo
│       └── bar
│           ├── alpha
│           │   └── Alpha.java
│           └── beta
│               └── Beta.java
└── module-info.java
```

#### 编译模块

```
$ javac module-info.java com/foo/bar/alpha/*java com/foo/bar/beta/*java
$ tree .
.
├── com
│   └── foo
│       └── bar
│           ├── alpha
│           │   ├── Alpha.class
│           │   └── Alpha.java
│           └── beta
│               ├── Beta.class
│               └── Beta.java
├── module-info.class
└── module-info.java
```

#### 打包模块

```
jar cvf com.foo.bar-1.0.jar .
```

检查jar结构：

```
$ jar tf com.foo.bar-1.0.jar
META-INF/
META-INF/MANIFEST.MF
module-info.class
com/
com/foo/
com/foo/bar/
com/foo/bar/alpha/
com/foo/bar/alpha/Alpha.class
com/foo/bar/beta/
com/foo/bar/beta/Beta.class
```

#### 引用模块

现在我们已经有了模块 com.foo.bar-1.0.jar，那么在定义其他模块，就可以使用requires关键字引用这个模块了。

```java
module com.foo.app{
  requires co.foo.bar;
  requires java.sql;
}

module com.foo.bar{
  requires com.foo.baz;
  exports com.foo.bar.alpha;
  exports com.foo.bar.beta;
}

module com.foo.baz{
  exports com.foo.baz.mumble;
}
```

### 內建的module

jdk原生的包被归并到內建的module里，如java.base模块：

```java
module java.base{
  exports java.io;
  exports java.lang;
  exports java.lang.annotation;
  exports java.lang.invoke;
  exports java.lang.module;
  exports java.lang.ref;
  exports java.lang.reflect;
  exports java.lang.math;
  exports java.lang.net;
  //...
}
```

所有的应用都会默认依赖 java.base,就像以前我们不用显式的 "import java.lang.*;" 一样。

这里验证了前面helloworld中，为什么反编译模块文件之后会多了一个:"requires java.base;"。

下面的 com.foo.app 模块，不需要显式地引入java.base：

![输入图片说明](https://static.oschina.net/uploads/img/201709/26094245_y23O.png "在这里输入图片标题")

如果此时com.foo.bar 增加了 com.foo.baz 模块的引用。

![输入图片说明](https://static.oschina.net/uploads/img/201709/26094306_U7H8.png "在这里输入图片标题")

那么，我们知道 com.foo.bar 也隐式 引入了 java.base。

同样的道理，com.foo.baz 模块也隐式引用了 java.base：

![输入图片说明](https://static.oschina.net/uploads/img/201709/26094333_EJNa.png "在这里输入图片标题")

### 可靠的配置

继续深入下去，我们知道 java.sql 引用了其他大量的api，那么下图就不难理解了。

![输入图片说明](https://static.oschina.net/uploads/img/201709/26100732_AVTY.png "在这里输入图片标题")

目前的模块结构，称为可读的模块，提供了可靠的配置。

如果引用了不存在的module，和jar一样，你同样会触发 xx not found.

编译时：

![输入图片说明](https://static.oschina.net/uploads/img/201709/26100310_duiw.png "在这里输入图片标题")

运行时：

![输入图片说明](https://static.oschina.net/uploads/img/201709/26094445_uVp1.png "在这里输入图片标题")

### 可访问的类型

如果引用的模块没有导出某个类，那么是不可访问的，这称为强封装。

![输入图片说明](https://static.oschina.net/uploads/img/201709/26094530_soOt.png "在这里输入图片标题")

比如 com.foo.bar 模块中有一个内部类BetaImpl：

![输入图片说明](https://static.oschina.net/uploads/img/201709/26100609_ttXV.png "在这里输入图片标题")

那么在 com.foo.bar 模块的主动引用模块 com.foo.app 中如下使用 BeatImpl:

![输入图片说明](https://static.oschina.net/uploads/img/201709/26094632_LItB.png "在这里输入图片标题")

在编译时，会触发异常：

![输入图片说明](https://static.oschina.net/uploads/img/201709/26094657_e48Y.png "在这里输入图片标题")

就是说：BetaImpl不可访问，因为包 com.foo.bar.beta.internal 包没有被导出。

同样，即便使用导出版本编辑成功，而运行时引用了未导出版本模块：

![输入图片说明](https://static.oschina.net/uploads/img/201709/26094717_WXUx.png "在这里输入图片标题")

### 查看內建的模块

```
$ jmod list $JAVA_HOME/jmods/java.base.jmod
classes/module-info.class
classes/apple/security/AppleProvider$1.class
...
classes/java/lang/Object.class
...
bin/java
bin/keytool
...
conf/security/java.policy
...
```

查看更多内建模块：

```
$ java --list-modules

java.activation@9
java.base@9
java.compiler@9
java.corba@9
java.datatransfer@9
java.desktop@9
//...节省篇幅略
```

### helloworld进阶

从helloworld的基础上，增加一个模块的依赖。

先来回顾一下helloworld的目录结构：

```
$ tree module
module
├── classes
│   ├── com
│   │   └── pollyduan
│   │       └── modular
│   │           └── Demo.class
│   └── module-info.class
├── lib
│   ├── demo.jar
│   ├── hello.jar
└── src
    ├── com
    │   └── pollyduan
    │       └── modular
    │           └── Demo.java
    └── module-info.java
```

#### 增加一个模块service，其中service目录和module目录同级。

```
$ tree service
service
├── classes
├── lib
└── src
    └── com
        └── pollyduan
            └── service
```

#### 创建服务类

service/src/com/pollyduan/service/HelloService.java

```java
package com.pollyduan.service;

public class HelloService{
    public void sayHi(String name){
        System.out.println("Hello "+name);
    }
}
```

#### 声明service模块

service/src/module-info.java

```java
module service{
    exports com.pollyduan.service;
}
```

#### 编译service模块

```
$ javac -d service/classes service/src/**java

$ tree service/classes/
service/classes/
├── com
│   └── pollyduan
│       └── service
│           └── HelloService.class
└── module-info.class
```

#### 打包service模块

```
jar --create --file service/lib/service.jar -C service/classes/ .
```

#### 修改helloworld模块

module/src/module-info.java

```
module hello{
    requires service;
}
```

#### 修改helloworld主类使用service中的方法

module/src/com/pollyduan/modular/Demo.java

```java
package com.pollyduan.modular;
import com.pollyduan.service.HelloService;

public class Demo{
    public static void main(String[] args){
        new HelloService().sayHi("java9 modular.");
    }
}
```

#### 重新编译打包helloworld

```
$ javac -p service/lib -d module/classes module/src/**java

$ jar --create --file module/lib/hello.jar -p service/lib --main-class com.pollyduan.modular.Demo -C module/classes .

$ java -p module/lib:service/lib -m hello
Hello java9 modular.
```

打完收工。

### 模块相关的工具

原有的javac/javap等就不说了，这里只列举新增的几个。更多参考：[https://docs.oracle.com/javase/9/tools/tools-and-command-reference.htm#JSWOR-GUID-55DE52DF-5774-4AAB-B334-E026FBAE6F34](https://www.oschina.net/action/GoToLink?url=https%3A%2F%2Fdocs.oracle.com%2Fjavase%2F9%2Ftools%2Ftools-and-command-reference.htm%23JSWOR-GUID-55DE52DF-5774-4AAB-B334-E026FBAE6F34)

#### jlink

模块整理工具，用于将一系列模块聚合、优化，打包到一个自定义的镜像中。这里说的是jre镜像，不是jar。

![输入图片说明](https://static.oschina.net/uploads/img/201709/26101034_YbgM.png "在这里输入图片标题")

如果我们只引用了java.base 模块，那么可以打包时可以选择性地打包：

```
$ jlink -p $JAVA_HOME/jmods --add-modules java.base --output jre
```

这时输出的jre就是一个完整可用的jre，他和原生jdk的大小相差很大：

```
$ du -sh $JAVA_HOME jre
493M	/Library/Java/JavaVirtualMachines/jdk-9.jdk/Contents/Home
 35M	jre
```

这样，我们可以把自己的模块也打包进去。

```
$ mkdir jmods
$ jmod create --class-path service/lib/service.jar jmods/service.jmod
$ jmod create --class-path module/lib/hello.jar jmods/module.jmod
$ jlink -p $JAVA_HOME/jmods:jmods --add-modules java.base,hello --output jre

$ cat jre/release
JAVA_VERSION="9"
MODULES="java.base service hello"

./jre/bin/java --list-modules
hello
java.base@9
service
```

注意，module-path的值采用classpath同样的分隔符，如windows里的分号和linux里的冒号；而add-modules 开关的值是使用逗号分隔的。

这样，我们打包了一个只有30M的jre，而且，把自己的module也打包进去了。然后呢？直接执行模块看看：

```
$ ./jre/bin/java -m hello

Hello java9 modular.
```

jlink还提供了一个launcher开关，可以将我们的模块编译成和java命令一样的可执行文件，放在 jre/bin 中。

```
$ jlink -p $JAVA_HOME/jmods:jmods --add-modules java.base,hello --launcher Hello=hello --output jre

$ ls jre/bin
Hello   java    keytool

$ ./jre/bin/Hello
Hello java9 modular.
```

请留意launcher的格式——"[命令]=[模块]"，为了区分，命令使用了首字母大写。

jlink的开关很多，功能不仅于此，如下可以将已经很小的jre继续压缩：

```
$ jlink -p $JAVA_HOME/jmods:jmods --add-modules java.base,hello --launcher Hello=hello \
--compress 2 --strip-debug \
--output jre_mini

$ du -sh jre*
 35M	jre
 21M	jre_mini
```

#### jdeps

这是一个java类文件的依赖分析器。

```
$ jdeps --module-path service/lib module/lib/hello.jar
hello
 [file:///Users/pollyduan/tmp/java/java9/module/lib/hello.jar]
   requires mandated java.base (@9)
   requires service
hello -> java.base
hello -> service
   com.pollyduan.modular                              -> com.pollyduan.service                              service
   com.pollyduan.modular                              -> java.lang                                          java.base
```

#### jmod

用于创建jmod文件，以及查看已存在的jmod文件。

创建jmod文件：

```
$ jmod create --class-path . com.foo.bar.jmod
$ jmod list com.foo.bar.jmod
classes/module-info.class
classes/.com.foo.bar.jmod.tmp
classes/com/foo/bar/alpha/Alpha.class
classes/com/foo/bar/alpha/Alpha.java
classes/com/foo/bar/beta/Beta.class
classes/com/foo/bar/beta/Beta.java
classes/com.foo.bar-1.0.jar
classes/module-info.java
```

#### jdeprscan

这是一个针对jar的静态的分析工具，查找其依赖的api。

```
$ jdeprscan dom4j-1.6.1.jar
Jar 文件 dom4j-1.6.1.jar:
class org/dom4j/bean/BeanMetaData 使用已过时的方法 java/lang/Integer::<init>(I)V
错误: 找不到类 org/relaxng/datatype/DatatypeException
错误: 找不到类 com/sun/msv/datatype/xsd/XSDatatype
错误: 找不到类 com/sun/msv/datatype/DatabindableDatatype
错误: 找不到类 com/sun/msv/datatype/SerializationContext
错误: 找不到类 com/sun/msv/datatype/xsd/TypeIncubator
错误: 找不到类 com/sun/msv/datatype/xsd/DatatypeFactory
class org/dom4j/io/SAXEventRecorder 使用已过时的方法 java/lang/Integer::<init>(I)V
class org/dom4j/io/SAXHelper 使用已过时的类 org/xml/sax/helpers/XMLReaderFactory
class org/dom4j/io/SAXReader 使用已过时的类 org/xml/sax/helpers/XMLReaderFactory
错误: 找不到类 org/xmlpull/v1/XmlPullParserFactory
错误: 找不到类 org/xmlpull/v1/XmlPullParser
错误: 找不到类 org/gjt/xpp/XmlPullParserFactory
错误: 找不到类 org/gjt/xpp/XmlPullParser
错误: 找不到类 org/jaxen/XPath
错误: 找不到类 org/jaxen/VariableContext
class org/dom4j/tree/NamespaceCache 使用已过时的方法 java/lang/Integer::<init>(I)V
class org/dom4j/tree/NamespaceCache 使用已过时的方法 java/lang/Float::<init>(F)V
错误: 找不到类 org/jaxen/NamespaceContext
错误: 找不到类 org/jaxen/SimpleNamespaceContext
错误: 找不到类 org/jaxen/dom4j/Dom4jXPath
错误: 找不到类 org/jaxen/JaxenException
错误: 找不到类 org/jaxen/pattern/Pattern
错误: 找不到类 org/jaxen/Context
错误: 找不到类 org/jaxen/pattern/PatternParser
错误: 找不到类 org/jaxen/saxpath/SAXPathException
错误: 找不到类 org/jaxen/ContextSupport
错误: 找不到类 org/jaxen/XPathFunctionContext
错误: 找不到类 org/jaxen/SimpleVariableContext
错误: 找不到类 org/jaxen/dom4j/DocumentNavigator
错误: 找不到类 org/gjt/xpp/XmlStartTag
```

### 模块小结

#### 关键词

```
模块定义 module-info.java
模块描述符 module-info.class
modular jar files 模块jar文件
jmod files 模块清单文件
observable modules 
readable modules => reliable configuration 可靠配置
accessible types => strong encapsulation 强封装
```

#### module和jar的区别

```
jar 实际上就是一个类文件的集合，就像一个zip文档；而module是一个规范的java组件，除了jar还有更多的工具支持。

jar中的资源可以任意使用；而module中的资源只有导出的才可以使用。

module仍然以jar为载体。

物理层面上，module在一定意义上可以理解为jar中的一个module-info.class。

目录结构的变化，以前一个jar项目是：
project
├── bin
├── classes
└── src
而module项目则是：
project
├── module1
│   ├── classes
│   ├── lib
│   └── src
└── module2
    ├── classes
    ├── lib
    └── src
```

#### 模块需要注意的问题

module 的依赖，同样存在循环依赖问题，需要注意。如：模块A，requires B; 模块B有 requires A。

IDE是否支持？传统的IDE都是基于classpath管理项目，现在需要支持基于module-path

module打包的jar，你仍然可以当做普通jar来用，没有人阻止你，至少目前是这样的。不过这并不是说module完全没有意义，就像class文件中的成员设置为私有，不允许外部访问，你完全可以通过反射去访问它，一个道理。

#### 模块的应用场景

首先，最突出的用法，就是使用jlink打包自定义的镜像，分发到小计算单元中运行，如docker，嵌入式设备。

其次，将来必定会有越来越多的容器来支持直接运行模块。

然后，他对于应用的热插拔的插件场景中，会有一席之地。

最后，就是代替jar方式运行的模块运行方式。

拭目以待。
