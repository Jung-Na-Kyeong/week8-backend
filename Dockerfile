# 깃허브 소스코드를 도커 안으로 가져와서 직접 .jar 파일로 빌드
FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app
COPY . .
# 실행 권한 부여 후 빌드
RUN chmod +x ./gradlew
RUN ./gradlew clean bootJar -x test

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=prod"]