package org.pdfmultitool;

import org.pdfmultitool.startbrowser.InitBrowser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PdfMultiToolApplication {
	private static ApplicationContext applicationContext = null;

	public static void main(String[] args) {
		SpringApplicationBuilder SpringAppBuilder = new SpringApplicationBuilder(PdfMultiToolApplication.class);
		SpringAppBuilder.headless(false);
		applicationContext = SpringAppBuilder.run(args);
		InitBrowser.startBrowser();
	}

	public static void shutdownApplication(){
		SpringApplication.exit(applicationContext, () -> 0);
	}
}