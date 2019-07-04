# person-rest-api
 
 Provides the API's to create, edit, delete and retrieving person entities.
 
# Technology stack
 Solution is developed using Spring Boot framework.
 
 In memory database(h2) is used for the data persistency.
 
# Notes
 All the API endpoints are secured using JWT token based mechanism.
 
 ## security
 
 A token can be obtained by making a post call to the http://localhost:8088/token endpoint.
 Token endpoint is using the basic auth mechanism.
 For the demo purpose, I have taken static user name and password to retrieve the JWT token. 
 
    Username: john123
    Password: password
 
 Once token is obtained, all the other endpoints can be accessed by sending the token as an Authorization header. Please prefix the token with 'Bearer' when sending the request.
 
 # Available endpoints
 POST http://localhost:8088/token 
 
 GET http://localhost:8088/api/persons - gets all the person entities
 
    Note: I have included the id for each entity in the response. Id is required to get/delete/update endpoints related to a specific entity.
 
 GET http://localhost:8088/api/persons/{id} - gets the person with given id
 
 POST http://localhost:8088/api/persons - creates the person
    
    sample payload {
                       "first_name": "Upendra",
                       "last_name": "Kumbham",
                       "age": 13,
                       "favourite_color": "pink",
                       "hobby": [
                           "reading",
                           "music"
                       ]
                   }
   
 PUT http://localhost:8088/api/persons/{id} - updates the entity
 
    sample payload {
                 	"id" : "1",
                 	"first_name":"Upendra",
                 	"last_name" : "Kumbham",
                 	"age": "34",
                 	"favourite_color" : "pink",
                 	"hobby" : ["reading"]
                 }
 
 DELETE http://localhost:8088/api/persons/{id} - deletes the entity with given id
 
 # demo run
 For the demo purpose application is hosted on Heroku and can be accessed 
 by going to 
    
    https://persons-services.herokuapp.com/
 
 Please replace the http://localhost:8088/ with https://persons-services.herokuapp.com/
 
 # how to run
 This project uses Spring-Boot and Gradle. In order to run it
 
 1. Clone the repository from github:
    
        git clone https://github.com/upendrakumbham/person-rest-api.git
  
 2. Import repo in the any preferred IDE- I used intellij IDEA
 
 3. To run the application through terminal
    - 'gradlew bootRun'  
 4. To run tests
     - gradlew clean test --info
        
     The base url of the API is [http://localhost:8088](http://localhost:8088).
    
