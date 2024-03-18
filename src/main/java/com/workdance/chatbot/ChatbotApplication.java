package com.workdance.chatbot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = "classpath*:spring/*.xml")
public class ChatbotApplication {

  private static final Logger LOGGER = LoggerFactory.getLogger(ChatbotApplication.class);

  public static void main(String[] args) {
    try {
      SpringApplication.run(ChatbotApplication.class, args);
      LOGGER.info("Springboot Application Start!!!");
    } catch (Throwable e) {
      LOGGER.error("Springboot Application Start Fail!!!", e);
      throw e;
    }
  }

}
