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

// Date and time
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

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

	/*
		Lesson learned:
		When you need to parse a list of json objects--i.e. the file begins and end with a '[' and ']' 
		respectively rather than '{' and '}', you don't need a wrapper class--you just directly retrieve it
		as a list as done below.

		https://newbedev.com/cannot-deserialize-instance-of-object-out-of-start-array-token-in-spring-webservice
	*/
	private WeatherObj getMostRecentWeatherPred(RestTemplate restTemplate, String woeid, String date) {
		// get the most recent weather prediction from the meta weather api
		log.info("Retriving Weather now...");

		String url = "https://www.metaweather.com/api/location/" + woeid + "/" + date;
		WeatherObj[] predictions = restTemplate.getForObject(url, WeatherObj[].class);
		
		return predictions[0];
	}


	private Message sendMessage(String text, String destNumber){
		final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
		final String API_KEY = System.getenv("TWILIO_API_KEY");
		final String API_SECRET = System.getenv("TWILIO_API_SECRET");

		Twilio.init(API_KEY, API_SECRET, ACCOUNT_SID);
		Message message = Message.creator(
			new com.twilio.type.PhoneNumber(destNumber),
			new com.twilio.type.PhoneNumber("+16107959082"),
			text)
		.create();

		return message;
	}

	private String getDate(){
		return DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now());
	}
	
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			// step 0: Zip code to city name
			

			// step 1: city name to where on earth id


			// Step 1: get current weather
			WeatherObj curr = getMostRecentWeatherPred(restTemplate, "44418", getDate());
			log.info(curr.getMin_temp() + " " + curr.getMax_temp());

			// Step 2: get clothing recomendation


			// Step 3: send the recommendation
			String toSend = "The weather is " + curr.getWeather_state_name() + " and the temperature is between " + curr.getMin_temp() + " and " + curr.getMax_temp();
			Message message = sendMessage(toSend, "+14086917228");
        	log.info(message.getSid());			
		};
	}

}
