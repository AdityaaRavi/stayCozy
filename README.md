# stayCozy
An on-demand SMS service that provides the current weather at your location or a city of your choice along with a recomendation on what you can choose to wear to be cozy. 

## Tools and Technologies used:
- Spring Boot
- Java
- Twilio
- ngrok
- REST API
- Webhook

## How it works (Flow):

1. This service has a webhook handler (managed using Spring Boot) to which Twilio sends a `HTTP POST` request when a user sends a message to the phone number associated with this project. 

2. This service then uses the Spring Framework to read the RESTful API provided by [MetaWeather](https://www.metaweather.com/api/) to retrieve the weather forecast at the location specified by the message. 

3. It generates a clothing recomendation [using a guide from Washinton Post](https://www.washingtonpost.com/weather/2018/10/30/weather-is-what-you-wear-unpacking-clothing-connected-different-climate-conditions-united-states/). 

4. The service then uses Twillio to send this recomendation via SMS back to the user.

## App in action!

### Youtube:

### Pictures:

## Usage instructions (once the program is running):
Command [SMS these]                 |   what does it do?
---                                 |   ------
`how`                               |   Provides a brief description on how to use the service 
`mine`                              |   Provides the weather forecast and clothing recomendation for your current location
name of city [Example: "San Jose"]  |   Provides the weather forecast and clothing recomendation for the given city

## How to get it running:
For phone numbers that I haven't verified with Twilio to be mine, I need a paid subscription to send and recieve messages, if you have some time to burn, this is how you can get it running yourself for free!

[Note: I develop using Linux, using I suggest you to try it out with linux too... some of the accompanied windows instructions may not work]

1. [Follow this short tutorial from Twilio till you get your number set up.](https://www.twilio.com/docs/usage/tutorials/how-to-use-your-free-trial-account)

2. Create an API key for Twilio:
    - Go to the console page
    - Click on the *Accounts* drop down near the top-right corner
    - Click *API keys*
    - Once you complete the 2FA, click on Create API Key
    - before you close the current window, check step 3.

3. Store your API key and Twilio phone number as environmental variables by creating, saving, and then running this script:
    
    - FOR Linux AND MacOS 
    ```bash
        export TWILIO_ACCOUNT_SID="CHANGE THIS TO YOUR ACCOUNT SID" 
        export TWILIO_API_KEY="CHANGE THIS TO YOUR API KEY"
        export TWILIO_API_SECRET="CHANGE THIS TO YOUR API SECRET"
        export TWILIO_PHONE_NO="CHANGE THIS TO YOUR TWILIO PHONE NUMBER"
      ```
    - For Windows, open cmd or powershell as *Admin* and run
    ```
        setx TWILIO_ACCOUNT_SID "CHANGE THIS TO YOUR ACCOUNT SID" /M
        setx TWILIO_API_KEY "CHANGE THIS TO YOUR API KEY" /M
        setx TWILIO_API_SECRET "CHANGE THIS TO YOUR API SECRET" /M
        setx TWILIO_PHONE_NO "CHANGE THIS TO YOUR TWILIO PHONE NUMBER" /M
      ```
4. Temporary port forwarding using `ngork` to allow the locally hosted program to listen for webhooks 
    - [Create an ngrok account and download it using the correct link through this website.](https://dashboard.ngrok.com/get-started/setup)
    - Go to the folder where you downloaded `ngork` and unzip it:
        - using windows explorer on windows
        - or the command `tar -xf ngrok-stable-linux-amd64.tgz` on Linux and MacOS
    - Run the command provided in "part 2" of the linked website that is unique to you.
    - Run the command: `./nrogk http 8080`
    - Copy one of the links provided on the terminal.
    - Go to `Twilio Console > Develop > Phone Numbers > Manage > Active Numbers > click on the number > Change the "A message comes in" link at the Messaging section at the bottom of the page to the link you copied above.`


5. ***Finally.... Running the program***
    - Navigate to the inner folder named "stayCozy" that contains the scripts `mvnw` and `mvnw.cmd`
        - run `./mvnw spring-boot:run` to start the program on linux or mac
            - If you don't have it installed already, install maven using `sudo apt get mvn`
        - For windows, you have to make sure that the PATH variables for the Java SDK is properly set up, after that, you should be able to run `mvnw.cmd` and get the program started. [not sure about this as it didn't make sense to test the program for a windows environment when it would never run there.] 
    - Use commands from the *Commands* table to start using the program!


## Resources
These are a selection of resources that I found particularly useful while building this project, and you might too if you are building something with the technologies used here.

- [Webhooks](https://www.chargebee.com/blog/what-are-webhooks-explained/)
    - "The fundamental difference between API calls and webhooks is that while the former works on request-based output mechanisms, and the latter works on event-based output mechanisms."
- 

