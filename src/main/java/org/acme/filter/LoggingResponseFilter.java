package org.acme.filter;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import lombok.extern.jbosslog.JBossLog;

@Provider
@JBossLog
public class LoggingResponseFilter implements ContainerResponseFilter {

  @Override
  public void filter(ContainerRequestContext containerRequestContext,
      ContainerResponseContext containerResponseContext) throws IOException {
    log.info("Response Filter");
  }
}
