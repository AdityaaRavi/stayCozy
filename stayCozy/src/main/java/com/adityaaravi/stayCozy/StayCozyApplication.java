package com.adityaaravi.stayCozy;

// local imports
import com.adityaaravi.stayCozy.objects.WeatherObj;

// library imports

//// logger
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//// basically the main method --> this is the entry point for the application
import org.springframework.boot.CommandLineRunner;

//// other Spring Boot imports
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//// imports to help retrive data from the remote api client
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/// importing twilio
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@SpringBootApplication
public class StayCozyApplication {

	private static final Logger log = LoggerFactory.getLogger(StayCozyApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StayCozyApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			log.info("Testing the API");
			//final String LONDON = "https://www.metaweather.com/api/location/search/?query=london";
			final String WOEID_TEST = "https://www.metaweather.com/api/location/44418/2021/12/27";

			// Test to retrive information from the metaweather API done!

			/*
				Lesson learned:
				When you need to parse a list of json objects--i.e. the file begins and end with a '[' and ']' respectively rather than '{' and '}'
				you don't need a wrapper class
				https://newbedev.com/cannot-deserialize-instance-of-object-out-of-start-array-token-in-spring-webservice
			*/
			WeatherObj[] predictions = restTemplate.getForObject(WOEID_TEST, WeatherObj[].class);
			WeatherObj curr = predictions[0];

			log.info(curr.getMin_temp() + " " + curr.getMax_temp());

			// Step 2: figure out how to use twillo to send text messages
			final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    		//final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
			final String API_KEY = System.getenv("TWILIO_API_KEY");
			final String API_SECRET = System.getenv("TWILIO_API_SECRET");

			String toSend = "The weather is " + curr.getWeather_state_name() + " and the temperature is between " + curr.getMin_temp() + " and " + curr.getMax_temp();

			//Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
			Twilio.init(API_KEY, API_SECRET, ACCOUNT_SID);
        	Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+14086917228"),
                new com.twilio.type.PhoneNumber("+16107959082"),
                toSend)
			.create();

        	log.info(message.getSid());			
		};
	}

}
