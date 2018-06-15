package edu.ort.t3.finaltp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FinalApplication {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(FinalApplication.class, args);
    }
    
    @Bean
	public JettyEmbeddedServletContainerFactory jettyEmbeddedServletContainerFactory() {
		JettyEmbeddedServletContainerFactory jettyContainer =
				new JettyEmbeddedServletContainerFactory();
		jettyContainer.setPort(9000);
		return jettyContainer;
	}
}

