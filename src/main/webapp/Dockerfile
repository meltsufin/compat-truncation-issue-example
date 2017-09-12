#FROM gcr.io/google_appengine/jetty9-compat:githubheadasync

#FROM gcr.io/java-runtime-test/jetty9-compat:trace
FROM  gcr.io/java-runtime-test/jetty9-compat:api-call-retries-1.9.56

ADD . $JETTY_BASE/webapps/root/

RUN rm -f /var/lib/jetty/webapps/root/WEB-INF/lib/appengine-api-1.0-sdk-*
RUN rm -f /var/lib/jetty/webapps/root/WEB-INF/lib/appengine-dependencies-*

RUN rm -f $JETTY_BASE/webapps/root/WEB-INF/*quickstart-web.xml

#RUN apt-get update
#RUN apt-get -y remove openjdk-8-jre openjdk-8-jre-headless ca-certificates-java
#RUN apt-get -y install -t jessie-backports openjdk-8-jdk-headless ca-certificates-java