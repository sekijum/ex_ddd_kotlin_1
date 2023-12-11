# Dev stage
FROM amazoncorretto:17 AS dev
ENV ROOTPATH /var/www
WORKDIR $ROOTPATH
EXPOSE 8080
CMD ["sh", "./cmd/dev/start.sh"]

# Runtime stage
FROM amazoncorretto:17 AS runtime
ENV ROOTPATH /var/www
WORKDIR $ROOTPATH
COPY --from=build /app/build/libs/*.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/app.jar"]