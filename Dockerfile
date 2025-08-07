# 使用官方 OpenJDK 17 作為基底映像
FROM openjdk:17-jdk-slim

# 設定容器內的工作目錄
WORKDIR /app

# 複製 JAR 檔到容器中
COPY target/baoguan-0.0.1-SNAPSHOT.jar app.jar

# 執行 JAR 檔
CMD ["java", "-jar", "app.jar"]
