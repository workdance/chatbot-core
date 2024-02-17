package com.alipay.richplayground;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = "classpath*:spring/*.xml")
public class RichplaygroundApplication {

  private static final Logger LOGGER = LoggerFactory.getLogger(RichplaygroundApplication.class);

  public static void main(String[] args) {
    try {
      SpringApplication.run(RichplaygroundApplication.class, args);
      LOGGER.info("Springboot Application Start!!!");
    } catch (Throwable e) {
      LOGGER.error("Springboot Application Start Fail!!! More logs can be found on 1) logs/sofa-runtime/common-error.log"
              + " 2) logs/spring/spring.log 3) logs/mvc/common-error.log 4) logs/health-check/common-error.log", e);
      throw e;
    }
  }

}
