FROM gcr.io/google_appengine/jetty9-compat:githubheadasync

ADD . $JETTY_BASE/webapps/root/

WORKDIR $JETTY_BASE
RUN java -jar $JETTY_HOME/start.jar --approve-all-licenses --add-to-startd=gzip

RUN sed -i 's/^\([a-zA-Z\.]*=\).*/#\1(see gae.ini)/' start.d/server.ini \
 && java -jar $JETTY_HOME/start.jar --dry-run \
  | sed 's/^.*java /exec & ${JAVA_OPTS} /' > jetty_cmd.sh \
 && chown -R jetty:jetty $JETTY_BASE \
 && chmod +x $JETTY_BASE/jetty_run.sh