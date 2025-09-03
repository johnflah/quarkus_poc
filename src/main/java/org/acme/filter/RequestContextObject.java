package org.acme.filter;

import jakarta.enterprise.context.RequestScoped;
import lombok.ToString;

@RequestScoped
@ToString
public class RequestContextObject {

  private String user;
  private String message;

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
