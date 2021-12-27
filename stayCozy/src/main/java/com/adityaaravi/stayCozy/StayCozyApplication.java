package com.adityaaravi.stayCozy;

// local imports
import com.adityaaravi.stayCozy.objects.WeatherObj;
import com.adityaaravi.stayCozy.objects.WoeidSearchResult;
// import com.adityaaravi.stayCozy.objects.archive.ZipData;
// import com.adityaaravi.stayCozy.objects.archive.ZipWrapper;
import com.adityaaravi.stayCozy.utilities.ClothesRecommender;

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
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/// importing twilio
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.serverless.v1.service.environment.Log;
import com.twilio.type.PhoneNumber;

// Date and time
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

@SpringBootApplication
public class StayCozyApplication {

	private static final Logger log = LoggerFactory.getLogger(StayCozyApplication.class);
	private static final String TEMP_FORMAT = "%.2f";

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
	private WeatherObj getMostRecentWeatherPred(RestTemplate restTemplate, int woeid, String date) {
		// get the most recent weather prediction from the meta weather api
		log.info("Step 1: Retriving Weather now...");

		String url = "https://www.metaweather.com/api/location/" + woeid + "/" + date;
		WeatherObj[] predictions = restTemplate.getForObject(url, WeatherObj[].class);
		
		return predictions[0];
	}


	private Message sendMessage(String text, String destNumber){
		log.info("Step 3: Sending message...");

		final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
		final String API_KEY = System.getenv("TWILIO_API_KEY");
		final String API_SECRET = System.getenv("TWILIO_API_SECRET");

		Twilio.init(API_KEY, API_SECRET, ACCOUNT_SID);
		Message message = Message.creator(
			new com.twilio.type.PhoneNumber(destNumber),
			new com.twilio.type.PhoneNumber("+16107959082"), // my twilio trial number
			text)
		.create();

		return message;
	}

	private String getDate(){
		return DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now());
	}
	

	/* 
	private String[] getWoeidAndCityName(String zipcode, RestTemplate restTemplate){
		
	// 	log.info("Step 0: Retriving Woeid and City Name now...");
	// 	// step 0: Zip code to city name
	// 	// final String API_KEY = System.getenv("ZIP_API_US_KEY");

	// 	// String requestUrl = "https://service.zipapi.us/zipcode/" + zipcode + "/?X-API-KEY=" + API_KEY;

	// 	// ZipWrapper wrap = restTemplate.getForObject(requestUrl, ZipWrapper.class);
	// 	// ZipData data = wrap.getData();

	// 	//log.info(data.toString());
	// 	// for some reason, the result is of the form: {latt=null, long=null, city="Davis"} -- i.e only city is correct

	// 	// step 0.5: city name to where on earth id
	// 	String url = "https://www.metaweather.com/api/location/search/?query=" + zipcode;//lattlong=" + data.getLatitude() + "," + data.getLongitude();
		
	// 	log.info(url);
		
	// 	WoeidSearchResult[] results = restTemplate.getForObject(url, WoeidSearchResult[].class);
	// 	WoeidSearchResult result = results[0];

	// 	String[] answer = {result.getWoeid(), zipcode}; //data.getCity()};

	// 	return answer;
	// }
	*/


	private void printArray(Object[] o){
		for(Object a : o){
			log.info(a.toString());
		}
	}

	private int getWoeid(String cityName, RestTemplate restTemplate){
		
		log.info("Step 0: Retriving Woeid now...");
		String cityNameEdited = (cityName.strip()).replace(" ", "%20");
		
		String url = "https://www.metaweather.com/api/location/search/?query=" + cityNameEdited;//lattlong=" + data.getLatitude() + "," + data.getLongitude();
		int minUrlLength = url.length() - cityNameEdited.length();

		log.info(url);
		WoeidSearchResult[] results = {};
		
		while(results.length == 0 && url.length() > minUrlLength){
			results = restTemplate.getForObject(url, WoeidSearchResult[].class);
			printArray(results);
			url = url.substring(0, url.length() - 1);
		}

		for (WoeidSearchResult w : results){
			if(w.getTitle().equals(cityName)) return w.getWoeid(); 
		}

		return -1;		
	}
	

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			
			// Sample input
			String cityName = "Chennai";
			String destPhoneNumber = "+14086917228";
			
			// Step 1: get current weather (Step 0.5: zip code to woeid)
			int woeid = getWoeid(cityName, restTemplate);
			String toSend = "";

			if (woeid != -1){
				WeatherObj curr = getMostRecentWeatherPred(restTemplate, woeid, getDate());
				log.info(curr.getMin_temp() + " " + curr.getMax_temp());
				
				// Step 2: get clothing recomendation
				String clothingRecomendation = ClothesRecommender.clothRecomendation(curr);

				// Step 3: send the recommendation
				toSend = "The weather at " + cityName + " is " + curr.getWeather_state_name() + ".\n" +
						 "The temperature range is: " + String.format(TEMP_FORMAT, ClothesRecommender.cToF(curr.getMin_temp())) + "-" +
						 String.format(TEMP_FORMAT, ClothesRecommender.cToF(curr.getMax_temp())) + "F or " + String.format(TEMP_FORMAT, curr.getMin_temp()) + 
						 "-" + String.format(TEMP_FORMAT, curr.getMax_temp()) + "C.";

				Message message = sendMessage(toSend, destPhoneNumber);
				Message message2 = sendMessage(clothingRecomendation, destPhoneNumber);

				log.info(message.getSid());
				log.info(message2.getSid());

			}else{
				toSend = "\nCity not found. Please try again.\n" + 
						 "Try to spell out the entire name of the city, example: \"San Jose\" or try a nearby bigger city.";
				
				Message message = sendMessage(toSend, destPhoneNumber);
				log.info(message.getSid());
			}

			log.info(toSend);	
			System.exit(0);			
		};
	}

}
