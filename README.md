# Zoo Management System

## 项目简介
这是一个基于Spring Boot和MySQL的动物园管理系统。通过Docker容器化部署，方便快速启动和测试。

## 环境配置

### MySQL配置
请在`MysqlDockerTest\src\main\resources\application.properties`文件中填写MySQL的连接信息，并确保数据库中的表已经创建。

示例：
```properties
spring.datasource.url=jdbc:mysql://你的ip地址:3306/zoo_management
spring.datasource.username=root
spring.datasource.password=密码
```

### 构建和运行

1. 清理并打包项目：
    ```sh
    ./mvnw clean package
    ```

2. 构建Docker镜像：
    ```sh
    docker build -t zoo-management .
    ```

3. 运行Docker容器：
    ```sh
    docker run -p 8080:8080 zoo-management
    ```

### 测试

`Test.bat`是测试程序。请编辑其中的IP地址，确保与主程序部署的地址一致。主程序部署好后，在Windows系统中双击打开`Test.bat`，并按提示操作。

http://192.168.10.3:8080 -> 你的docker配置的地址