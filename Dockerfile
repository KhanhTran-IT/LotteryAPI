# 1. Java 21 cho Spring Boot 3
FROM eclipse-temurin:21-jdk

# 2. Thư mục làm việc
WORKDIR /app

# 3. Copy source code
COPY . .

# 🔥 4. Cấp quyền chạy cho mvnw (QUAN TRỌNG)
RUN chmod +x mvnw

# 5. Build project
RUN ./mvnw clean package -DskipTests

# 6. Chạy app
CMD ["java", "-jar", "target/*.jar"]
