FROM openjdk:8-slim

ENV TZ=Asia/Shanghai
RUN set -eux; \
    ln -snf /usr/share/zoneinfo/$TZ /etc/localtime; \
    echo $TZ > /etc/timezone
ADD target/*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom" ,"-jar" , "-Xms128m","-Xmx128m" ,"/app.jar"]