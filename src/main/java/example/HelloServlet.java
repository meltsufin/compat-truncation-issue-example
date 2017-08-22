package example;

import com.google.cloud.pubsub.PubSub;
import com.google.cloud.pubsub.PubSubOptions;
import com.google.cloud.pubsub.Topic;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Enumeration;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

  public static Logger log = Logger.getLogger(HelloServlet.class.getName());

  private String message;

  public void init() throws ServletException {
    message = "Hello World";
  }

  public void service(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {

    log.info("Entered service handling.");

    response.setContentType("text/html");

    PrintWriter out = response.getWriter();

    String scaleStr = request.getParameter("scale");
    String delayStr = request.getParameter("delay");

    int delay = 0;
    if (delayStr != null) {
      delay = Integer.parseInt(delayStr);
    }

    out.println("<h1>" + message + "</h1>");

    // delay
    try {
      Thread.sleep(delay);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    if (scaleStr != null) {
      int scale = Integer.parseInt(scaleStr);
      for (int i = 0; i < scale; i++) {
        out.println("<h2>" + i + " - " + message + "</h2>");
      }
    }

    if (request.getParameter("file") != null) {
      File file = new File(request.getParameter("file"));
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      out.println("<pre>");
      while (bufferedReader.ready()) {
        out.println(bufferedReader.readLine());
      }
      out.println("</pre>");
    }

    log.warning("Testing warning.");

    out.println("<pre>");
    for (String header : Collections.list((Enumeration<String>) request.getHeaderNames())) {
      out.println(header + ": " + request.getHeader(header));
    }
    out.println("</pre>");

    if (request.getParameter("pubsub") != null) {
      log.info("Accessing Pub/Sub");
      PubSub pubsub = PubSubOptions.newBuilder().build().getService();
      Topic t = pubsub.getTopic(request.getParameter("pubsub"));
      log.info("Topic: " + t.getName());
    }

    log.info("Exiting service handling.");
  }

}
