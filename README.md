# stayCozy
A service that provides daily clothing recomendations based on the weather forecast to help you stay cozy throughout the day via SMS.

## How it works:

This service uses the Spring Framework to read the RESTful API provided by [MetaWeather](https://www.metaweather.com/api/) to retrieve the weather forecast at the user specified location, then it generates a clothing recomendation [using a guide from tommorow.io](https://www.tomorrow.io/weather/blog/weather-clothing-guide/). The service then uses Twillio to send this recomendation via SMS to the user every day at a specified time.

## Tools and Technologies used:
- Spring Framework
- Java
- Twillio
- REST APIs

## Usage instructions:
[TBD]
Possible usage:
- The user sends their current US zip-code to the service 
- The service uses the zip-code to get the corresponding city name [using this API](https://zipapi.us/docs/)
- It then uses Meta Weather's in-built service to get the corresponding "Where on earth ID"
- It then gives the where on earth ID to Meta Weather to get the most recent weather forecast for the current day
- It then uses that forecast, like previously planned, to prepare clothing recommendations
- It then sends those clothing recomendations along with the weather to the user.
- so each use will cost me $0.0015 before hosting fees. 
- For this to work, my code MUST be hosted online as Twilio will send the program HTTP requests whenever it recieves a new message. 
 
