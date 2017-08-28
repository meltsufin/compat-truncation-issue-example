package example;

import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;

public class ApplicationServletModule extends ServletModule {

  @Override
  protected void configureServlets() {
    bind(HelloServlet.class).in(Singleton.class);

    serve("/hello").with(HelloServlet.class);
  }
}