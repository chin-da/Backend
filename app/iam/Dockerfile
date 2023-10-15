FROM eclipse-temurin:17 AS build

WORKDIR /build/
COPY settings.gradle settings.gradle
COPY gradle gradle
COPY gradlew gradlew

COPY ./iam/build.gradle ./iam/build.gradle
COPY ./iam/settings.gradle ./iam/settings.gradle
COPY ./iam/src ./iam/src

COPY ./iam-shared-kernel/build.gradle ./iam-shared-kernel/build.gradle
COPY ./iam-shared-kernel/settings.gradle ./iam-shared-kernel/settings.gradle
COPY ./iam-shared-kernel/src ./iam-shared-kernel/src

COPY ./common/build.gradle ./common/build.gradle
COPY ./common/src ./common/src

RUN ./gradlew :iam:build -x test

FROM eclipse-temurin:17
COPY --from=build /build/iam/build/libs/iam-0.0.1-SNAPSHOT.jar iam.jar
ENTRYPOINT [ "java", "-jar", "iam.jar"]
