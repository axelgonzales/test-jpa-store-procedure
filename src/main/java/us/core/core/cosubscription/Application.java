package us.core.core.cosubscription;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    private static final String ARGS = "--spring.output.ansi.enabled=always";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, ARGS);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
}