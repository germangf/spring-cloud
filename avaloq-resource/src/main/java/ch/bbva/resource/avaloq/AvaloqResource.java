package ch.ggf.resource.avaloq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AvaloqResource {

  protected AvaloqResource() {
  }

  public static void main(String[] args) {
    System.setProperty("spring.config.name", "avaloq-resource");
    SpringApplication.run(AvaloqResource.class, args);
  }

}
