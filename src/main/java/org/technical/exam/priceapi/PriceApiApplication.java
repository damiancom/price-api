package org.technical.exam.priceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:data-source.properties")
public class PriceApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(PriceApiApplication.class, args);
  }

}
