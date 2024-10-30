###############################################################################
#                                   builder                                   #
###############################################################################
#FROM golang:1.18.0-buster AS build-stage
#
#ENV SERVICE_NAME golang-builder
#ENV APP /src/${SERVICE_NAME}/
#ENV WORKDIR ${GOPATH}${APP}
#
#WORKDIR $WORKDIR
#ADD . $WORKDIR
#
#RUN CGO_ENABLED=0 \
#  go build -a -o /bin/api ./cmd/api

###############################################################################
#                                    image                                    #
###############################################################################
FROM openjdk:17-jdk-slim
ARG JAR_FILE=out/artifacts/techradar_polls_jar/techradar_polls.jar
COPY ${JAR_FILE} app.jar
#COPY --from=build-stage /bin/api /app/api
#CMD ["/app/api"]
#ENTRYPOINT ["java", "-jar", "/app.jar"]
CMD ["java", "-jar", "/app.jar"]