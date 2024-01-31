# 使用 Maven 镜像构建项目
FROM adoptopenjdk:8-jre-hotspot

# 设置工作目录
WORKDIR /app

# copy arthas
COPY --from=hengyunabc/arthas:latest /opt/arthas /opt/arthas

# 设置 Arthas 环境变量
ENV PATH=$PATH:/opt/arthas/bin

# copy nacos-provider
COPY target/nacos-consumer-1.0-SNAPSHOT.jar nacos-consumer-1.0-SNAPSHOT.jar

EXPOSE 9090 5005

# 定义启动命令，包括 Arthas 启动
#CMD ["java", "-jar", "nacos-consumer-1.0-SNAPSHOT.jar"]
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005", "-jar", "nacos-consumer-1.0-SNAPSHOT.jar"]
