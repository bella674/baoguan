# === 第一階段：使用 Maven 打包 ===
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# 複製專案所有檔案到容器中
COPY . .

# 使用 Maven 打包並略過測試
RUN mvn clean package -DskipTests

# === 第二階段：執行 jar ===
FROM openjdk:17
WORKDIR /app

# 從前面 Maven 階段複製打包好的 jar
COPY --from=build /app/target/baoguan-0.0.1-SNAPSHOT.jar app.jar

# 開放容器的 8080 port
EXPOSE 8080

# 啟動 Spring Boot 應用程式
ENTRYPOINT ["java", "-jar", "app.jar"]
