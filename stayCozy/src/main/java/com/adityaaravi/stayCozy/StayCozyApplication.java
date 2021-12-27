package com.adityaaravi.stayCozy;

// // local imports
// import com.adityaaravi.stayCozy.objects.WeatherObj;
// import com.adityaaravi.stayCozy.objects.WoeidSearchResult;
// // import com.adityaaravi.stayCozy.objects.archive.ZipData;
// // import com.adityaaravi.stayCozy.objects.archive.ZipWrapper;
// import com.adityaaravi.stayCozy.utilities.ClothesRecommender;

// library imports

// //// logger
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

//// basically the main method --> this is the entry point for the application
import org.springframework.boot.CommandLineRunner;

//// other Spring Boot imports
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//// imports to help retrive data from the remote api client
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

// /// importing twilio
// import com.twilio.Twilio;
// import com.twilio.rest.api.v2010.account.Message;
// import com.twilio.rest.serverless.v1.service.environment.Log;
// import com.twilio.type.PhoneNumber;

// // Date and time
// import java.time.format.DateTimeFormatter;  
// import java.time.LocalDateTime;    

@SpringBootApplication
public class StayCozyApplication {



	public static void main(String[] args) {
		SpringApplication.run(StayCozyApplication.class, args);
	}

	// @Bean
	// public RestTemplate restTemplate(RestTemplateBuilder builder) {
	// 	return builder.build();
	// }

	// @Bean
	// public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
	// 	return args -> {
			
	// 	};
	// }

}
