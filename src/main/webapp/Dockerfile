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


# pprof config
RUN rm -rf /opt/cprof && mkdir /opt/cprof && cd /opt/cprof && \
wget -qO- https://storage.googleapis.com/cloud-profiler/java/latest/profiler_java_agent.tar.gz | tar xzv
RUN ls /opt/cprof

ENV CPROF_OPTS $CPROF_OPTS,--cprof_delay_sec=60
ENV CPROF_OPTS $CPROF_OPTS,--cprof_interval_sec=300
ENV CPROF_OPTS $CPROF_OPTS,--cprof_duration_sec=30
ENV CPROF_OPTS $CPROF_OPTS,--cprof_max_count=200
ENV CPROF_OPTS $CPROF_OPTS,--cprof_profile_filename=/tmp/profile_
ENV JAVA_OPTS -agentpath:/opt/cprof/profiler_java_agent.so=$CPROF_OPTS