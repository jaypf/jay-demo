FROM docker.io/relateiq/oracle-java8
VOLUME /tmp
ADD jay-admin.jar app.jar
RUN bash -c 'touch /app.jar'
#ENTRYPOINT ["java","-jar","/app.jar"]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 9790
