用这个打包
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
