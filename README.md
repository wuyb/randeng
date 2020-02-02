## 服务介绍

### 用户体系
在目前版本，用户端仅会在公众号中存在，因此借用微信的用户体系。在通过微信登录获得了用户open_id和access_token后，保存在服务端，
并以此作为身份认证的凭证（类似用户名和密码），服务端返回基于open_id和access_token的auth_code。用户端对后台的受限访问均需附带auth_code。

管理端将有独立的用户体系，以用户名密码为凭证完成身份认证，服务端返回基于用户名和密码的auth_code。

## 部署方法

### 系统配置
系统的配置在 ``src/resources`` 目录下的 ``application-[profile].properties`` 文件中。

首先请拷贝 application-dev.properties 为 application-prod.properties (profile 名字可自行设置，建议用 prod)。

然后根据实际情况修改配置项。

### 打包 jar 文件
在根目录下运行 ``./gradlew bootJar`` 即可，生成的可执行文件在 ``/build/libs/`` 下。

### 启动服务
``nohup java -jar -Dspring.profiles.active=prod randeng-0.0.1-SNAPSHOT.jar RDApplication.class &``