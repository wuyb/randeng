# Service 部署文档

## 部署方法

### 系统配置
系统的配置在 ``src/resources`` 目录下的 ``application-[profile].properties`` 文件中。

首先请拷贝 application-dev.properties 为 application-prod.properties (profile 名字可自行设置，建议用 prod)。

然后根据实际情况修改配置项。

### 打包 jar 文件
在根目录下运行 ``./gradlew bootJar`` 即可，生成的可执行文件在 ``/build/libs/`` 下。

### 启动服务
``nohup java -jar -Dspring.profiles.active=prod randeng-0.0.1-SNAPSHOT.jar RDApplication.class &``