package example;

import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import java.io.IOException;
import java.math.BigInteger;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemcacheServlet extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,
      ServletException {
    String path = req.getRequestURI();
    if (path.startsWith("/favicon.ico")) {
      return; // ignore the request for favicon.ico
    }

    MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
    syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
    String key = "count-sync";
    byte[] value;
    long count = 1;
    value = (byte[]) syncCache.get(key);
    if (value == null) {
      value = BigInteger.valueOf(count).toByteArray();
      syncCache.put(key, value);
    } else {
      // Increment value
      count = new BigInteger(value).longValue();
      count++;
      value = BigInteger.valueOf(count).toByteArray();
      // Put back in cache
      syncCache.put(key, value);
    }

    // Output content
    resp.setContentType("text/plain");
    resp.getWriter().print("Value is " + count + "\n");
  }

}
