package example;

import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;
import javax.servlet.Servlet;

public class ApplicationServletModule extends ServletModule {

  @Override
  protected void configureServlets() {
    bind(HelloServlet.class).in(Singleton.class);
    bind(MemcacheServlet.class).in(Singleton.class);

    serve("/hello").with(HelloServlet.class);
    serve("/memcache").with(MemcacheServlet.class);
  }
}