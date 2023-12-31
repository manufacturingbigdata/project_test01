FROM gradle:7.4-jdk11-alpine as builder
WORKDIR /build

# 작업했던 파일들을 복사해서 넣어 줍니다.
COPY . .

# 그래들 파일이 변경되었을 때만 새롭게 의존패키지 다운로드 받게함.
COPY build.gradle settings.gradle /build/
RUN gradle build -x test --parallel --continue > /dev/null 2>&1 || true

# 빌더 이미지에서 애플리케이션 빌드
COPY . /build
RUN gradle build -x test --parallel

## APP
FROM openjdk:11.0
WORKDIR /app
#
## 빌더 이미지에서 jar 파일만 복사
## COPY --from=builder /build/build/libs/*-SNAPSHOT.jar ./app.jar
COPY --from=builder /build/build/libs/*.war ./app.jar
#
EXPOSE 8080
#
#
ENTRYPOINT ["java",  "-jar",  "app.jar"]