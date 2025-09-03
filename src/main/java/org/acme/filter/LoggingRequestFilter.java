package org.acme.filter;


import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import java.time.LocalDateTime;
import lombok.extern.jbosslog.JBossLog;

@Provider
@JBossLog
public class LoggingRequestFilter implements ContainerRequestFilter {

private final RequestContextObject requestContextObject;

  public LoggingRequestFilter(RequestContextObject requestContextObject) {
    this.requestContextObject = requestContextObject;
  }


  @Override
  public void filter(ContainerRequestContext containerRequestContext) throws IOException {
    String auth = containerRequestContext.getHeaderString("Authorization");
    containerRequestContext.setProperty("greeting", "hello from the request filter");
    requestContextObject.setUser("moi");
    requestContextObject.setMessage("chello!" + LocalDateTime.now());
    log.infof("auth: %s", auth);
  }
}
