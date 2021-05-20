#  Starbucks - The Uncalled Four
### CMPE 172 instructed by Prof. Paul Nguyen

### Contributors 
 * Alexander Montgomery
 * Anh Hoang
 * Guiller Dalit

### How To Run the application:
***Systems, Software, and Framework used:***
  * Visual Studio Version: 1.56.2
  * Gradle 5.6
  * java: 11.0.10.j9-adpt
  * Cybersource
  * RabbitMQ
  * Kong API on GKE

## Please see [Journal](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/journal/project_journal.md) for more information.

***This file only contains brief explanation on how to run the Starbucks application.***

  * Running the project, first run the starbucks-api folder on terminal by typing 'gradle bootRun'
  * Next, open backofficerabbitmq folder on the terminal and type 'make network-create', then followed up by typing 'make rabbit', this should run Rabbitmq on the      docker in port 8070.
  ![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/screenshots/readme1.png?raw=true)
  
  * Open localhost:8070 to double-check if the RabbitMQ is running. Rabbitmq is used to handle customer questions.  
  ![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/screenshots/readme2.png?raw=true)
  
  * The cashier app and the online store are in starbucks folder. To run the Starbucks application open starbucks folder on the terminal and type 'gradle bootRun'.
  ![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/screenshots/readme3.png?raw=true) 

