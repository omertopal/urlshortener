FROM java:8
VOLUME /tmp
ADD target/urlshortener-0.0.1-SNAPSHOT.jar urlshortener.jar
RUN bash -c 'touch /urlshortener.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /urlshortener.jar"]