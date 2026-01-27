# 1. Dùng Java 21 (đúng với Spring Boot 3)
FROM eclipse-temurin:21-jdk

# 2. Set thư mục làm việc
WORKDIR /app

# 3. Copy toàn bộ source code
COPY . .

# 4. Build project bằng Maven Wrapper
RUN ./mvnw clean package -DskipTests

# 5. Chạy file jar
CMD ["java", "-jar", "target/*.jar"]
