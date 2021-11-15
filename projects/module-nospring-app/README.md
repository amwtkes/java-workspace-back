# 用这个打包
`jar --create --file target/app.jar -p ../module-nospring-lib/target/module-nospring-lib-0.0.1-SNAPSHOT.jar --main-class com.xiaojin.nospring.app.Test -C ./target/classes .`
>maven 暂时不支持java module
- `--file` 新建jar包名称
- `-p`依赖的带module-info的jar包路径
- `--main-class`生成`META-INFO/MANIFEST.MF`文件，指定main-class
- `-C`说明打包这个目录下的所有class文件

# 执行
`java -p ../../module-nospring-lib/target/module-nospring-lib-0.0.1-SNAPSHOT.jar:./app.jar -m module.nospring.app`
- `-p`指定module-path，运行时要用的包含模块的jar包们
- `-m`带main-class的模块名，要运行的模块名。

# 打包
`jmod`文件是最基本的模块文件，可以将模块导出的二进制文件。
打包的对象都是jmod文件。所以先要生成每个模块的jmod文件。
- 打包lib：`jmod create --class-path ./module-nospring-lib-0.0.1-SNAPSHOT.jar ./jmods/module.nospring.lib.jmod` jmods文件夹预先要创建
- 打包app：用jlink打包：`jlink -p $JAVA_HOME/jmods:./jmods:/Users/xiaojin/workspace/my_github/java-workspace/projects/module-nospring-lib/target/jmods --add-modules java.base,module.nospring.app,module.nospring.lib --output jre-me`
    - 新的jre中有完整的可执行的`java`，但是质包含了需要的模块，所以尺寸要小得多。
    - `./java --list-modules`可以列出这个mini jre包含的模块
    - 这次运行就更清爽了，直接不依赖环境了，`./bin/java -m module.nospring.app`
    - 更加方便，直接打包成可执行文件(删除原来生成的jre-me文件夹)：`jlink -p $JAVA_HOME/jmods:./jmods:/Users/xiaojin/workspace/my_github/java-workspace/projects/module-nospring-lib/target/jmods --add-modules java.base,module.nospring.app,module.nospring.lib --launcher App=module.nospring.app --output jre-me`加入了luncher开关
    - `./bin/App`直接运行（加入`--compress 2 --strip-debug`可以压缩产生的可执行文件大小）
