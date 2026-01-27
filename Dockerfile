FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests

# ⚠️ SỬA DÒNG NÀY – GHI RÕ TÊN FILE JAR
CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]
