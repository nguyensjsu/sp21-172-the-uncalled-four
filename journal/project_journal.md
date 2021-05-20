# Uncalled Four Starbucks Journal

### **CMPE 172 - Starbucks - Weekly Status Reports**
#### CMPE 172 - Starbucks - Week #1 - April 22, 2020 - All Commits accessible [here](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/commits/main)  <br />

***Starting off the project*** [Commit Link](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/commit/7e1f9f69b9fbca3b8125e5915e4b9a8e0349e55e) <br />
For the first-week status report, we started with the Starbucks project by using the Tacos Cloud project in *Spring Boot in Action Fifth Edition by Craig Walls* as my reference. In this code, I pretty much set up the starbucks-api and the starbucks. We wanted to print out the cards created in the starbucks-api that I inserted using Insomnia and display those cards in the starbucks, and so it did. 

***Creating Project Work Board***
Alex primarily coordinated with other members of the group in scheduling future tasks for the project, organizing them concisely into the Project work board available on the team's Github page. It currently has the parts completed by Guiller and the current tasks of both Alexander and Anh.

***Adding styles using CSS*** [Commit Link](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/commit/ee6ea2ce600eb90ea2dd5c2fe8e50a52e234eb3b) <br />
For the second part of my Starbucks development. We wanted to add more style to the pages. So we used CSS to make the pages similar to starbucks.com. We made the page design as simple as possible. Please see the images below.

![alt text](https://github.com/nguyensjsu/cmpe172-guiller-d/blob/main/project/screenshots/welcome.png?raw=true)

![alt text](https://github.com/nguyensjsu/cmpe172-guiller-d/blob/main/project/screenshots/menu.png?raw=true)

***HTML Mappings*** [Commit Link](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/commit/16e840f693989f6cd7e4a2136bcb4719461aae2d) <br />
After adding the design, we wanted to map the buttons on the top left of the menu. So when a button is clicked it will navigate to its respected HTML file. For instance, if the user clicks the menu it will go to the menu page. Now the user can navigate through the pages. 

![alt text](https://github.com/nguyensjsu/cmpe172-guiller-d/blob/main/project/screenshots/welcome.png?raw=true)

![alt text](https://github.com/nguyensjsu/cmpe172-guiller-d/blob/main/project/screenshots/menu.png?raw=true)

***Data Management using MySQL - third phase*** [Table Commit Link](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/commit/1b7ecbba202c01d1936aa8dd811e271207ec67d2) and [Data Commit Link](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/commit/c3848f5a0d89359353c0f68b64854f51bbb3c7aa) <br />
Seeing that data are displayed correctly on the menu page, the next step is to create a table for all the data that the Starbucks project would need. The image below is the Entity Relational Diagram we created. This is not the final ERD, and as we go, we might remove, add, or modify the relationship between the tables. But for now, this is what we have. 

![alt text](https://github.com/nguyensjsu/cmpe172-guiller-d/blob/main/project/screenshots/starbucks-erd.png?raw=true)

![alt text](https://github.com/nguyensjsu/cmpe172-guiller-d/blob/main/project/screenshots/drinks_sql.png?raw=true)
 
**Add UI design of rewards and payment view** [Commit Link](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/commit/2b94cc7879c7ac5d920d4a6d762a5d01e2c5ec97) <br />
Currently, we're working on the UI of the rewards view and payment view of the application. However, we just add the structure of the view, I need to style the pages to make them look nice. 

#### CMPE 172 - Starbucks - Week #2 - April 29, 2020 
***Port Over Cybersource***
We ported over the information from the Cybersource lab to the project, which will be invaluable for the verification of credit cards on our system for the Cashier app. Many of the CyberSource integration can be used from the lab, but some modifications must be made for the project.

[Link](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/commit/ccd22b76d916fb2d7401fadc124738c6df515aaa)

![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/screenshots/taskboard-alex-1.png)

***Updating the ERD*** [Commit Link](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/commit/3908699a72c5e0b3709574395ace43ec11d273dd) <br />
When reviewing the ERD, it seems that there are a couple of tables that can be removed. So the updated version of the ERD is displayed below. 
![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/mysql/starbucks-erd-UPDATED.png?raw=true)

#### CMPE 172 - Starbucks - Week #3 - May 6, 2021 - All Commits accessible [here](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/commits/main)

***Established Connection to Database to Pull Menu items***
We were able to create an initial shell for connecting to the database and extracting information from its tables to populate the menus. Currently, only the connection has been established, but we will continue to update this such that it will populate the HTML properly. Also created a button that will be used for placing orders on the cashier app.

[Link](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/commit/bf88f4bd905fca1b79e65ff6d3f1f8ed4db3e749)

![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/screenshots/taskboard-alex-2.png)

***Updated CRUD Approach Redesign Table Creation Using @Entity*** [Commit Link](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/commit/064ca2ff9822bcfa2a83ca61085c803c814d04af) <br />
We have updated on where CRUD behaves in Starbucks. Before I run SQL scripts on MySQL to create the tables and insert data. This week, We abolished that approach. Now when we run Starbucks, it creates the tables before the user opens the web browser. Models use @Entity to create the tables for Starbucks. 

![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/screenshots/%40entity.png?raw=true)

***Updated CRUD Approach Redesign Table Data Insertion jdbcTemplate*** [Commit Link](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/commit/13286978b5fcc5e491420ff086c5190fbe184a7a) <br />
However, we have encountered a problem with inserting the drinks data into the database. This is error is the main focus, and hopefully, we can fix it by tomorrow. 
![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/screenshots/error-mysql.png?raw=true)

***Initial Commit on Kong*** [Commit Link](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/commit/25638ad6f6525956cccb46c4f49888a0c0bc2236) <br />
We have also initialized the Kong in the project. Although we haven't started developing it since the project got stuck on the error on MySQL. 


#### CMPE 172 - Starbucks - Week #3 - May 13, 2021 - All Commits accessible [here](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/commits/main)

***Payment View*** [Commit Link](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/commit/596239a9301ec400f60d018317564ed82f77c3a9) <br />

![paymentview](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/screenshots/paymentview.png)

***Backoffice***

So far, we've been testing spring-rabbitmq-helloworld as the initial baseline to develop the Backoffice. The approach is to run "make send env=dev" in runtime (starbucks project), but I figured that I was running the jar file that is already created. So whatever question from the customer that is sent won't be sent to RabbitMQ because the jar file is already created beforehand. At this point, I'm running out of approach and trying whatever idea comes to mind. 

First, this commit is when I tried sending the question in runtime using exec. <br />
[Commit Link](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/commit/6ab8f4dbae3f972b59c310dfa23e7a49d2502acf) <br />

And in this commit, we thought that what if we create a static variable first and set the question from the user and then create the jar file. Well, this approach also didn't work because after setting the question to the variable the project close and create the jar file. Meaning when the file was closed the set variable is gone and then it creates the jar file without the previous setup.
<br />
[Commit Link](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/commit/2234937930c29f945f735b980eee977d3438a417) <br />

***Finalize the looks of backoffice on web***
At this point we are running out of ideas on how to tackle this problem. But hopefully we get to think of a solution soon.

These commits are CSS and styles on HTML. <br />
[Commit Link](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/commit/e48a6e8b7243b408bff2d47d2541a7d1d39686e9)<br />
[Commit Link](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/commit/0dd6807262f7471cee433bc95d5752ece7875302)<br />

![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/screenshots/backoffice_journal_screenshot.png?raw=true)<br />

***Created static html values***
[Link](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/commit/3fd9af909a8972010c8b4e88189e44b441f50597)
For now, we decided to use static HTML values for the menu. Currently, we are working on adding the name of the drink to the orders section, but the method isn't fully implemented yet. The project was running into issues since this is our first time working in-depth with HTML and had to read various tutorials on implementing ids and click effects.

#### CMPE 172 - Starbucks - Final Week - May 19, 2021 - All Commits accessible [here](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/commits/main)

### Finalize of What We have Finished

***Cashier's App***
 * In the Cashier’s App, the app handles the user’s order by prices
 * The user can also cancel the order by simply going back to the menu. 
 * Once the customer has decided what drinks to order. The customer can click checkout and it will navigate to the new page for the user to pay along with the receipt. 
 * The customer’s payment is handled using CyberSource. 

![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/screenshots/starbucks2.png?raw=true)<br />

***Backoffice Help Desk App***
 * Our Backoffice runs on Rabbitmq. 
 * It is located at the bottom right of the menu page. 
 * Illustration of how Backoffice works below.

![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/screenshots/starbucks2.png?raw=true)<br />

![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/screenshots/starbucks4.png?raw=true)<br />

***Online Store***
 * The application when open will display the Welcome Page. 

![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/screenshots/welcomepage.png?raw=true)<br />

 * The customer can also go to the **ORIGINAL** Starbucks app to download. 

![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/screenshots/mobile-application.png?raw=true)<br />

 * And also a information about the CDC Guidelines on how we operate during pandemic. 

![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/screenshots/cdc.png?raw=true)<br />

 * The store offers 6-types of coffee. We didn’t really have the intention to check the price of each coffee and display it on the menu, although we have the correct name of the coffee for each type. 

![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/screenshots/starbucks2.png?raw=true)<br />

***REST API***
 * We have successfully deploy KONG API GATEWAY for starbucks-api. Below are some sample of request/response <br />

    ***Ping***
    ![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/kongscreenshots/ping.png?raw=true)<br />
    ***New Card***
    ![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/kongscreenshots/newcard.png?raw=true)<br />
    ***Get Cards***
    ![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/kongscreenshots/getcards.png?raw=true)<br />
    ***Get Card***
    ![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/kongscreenshots/getcard.png?raw=true)<br />
    ***Activate Card***
    ![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/kongscreenshots/activatecard.png?raw=true)<br />
    ***New Order***
    ![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/kongscreenshots/neworder.png?raw=true)<br />
    ***Get Orders***
    ![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/kongscreenshots/getorders.png?raw=true)<br />
    ***Get Order***
    ![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/kongscreenshots/getorder.png?raw=true)<br />
    ***Pay Order***
    ![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/kongscreenshots/payorder.png?raw=true)<br />
    ***Clear Order***
    ![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/kongscreenshots/clearorder1.png?raw=true)<br />
    ***Delete Orders***
    ![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/kongscreenshots/deleteorders.png?raw=true)<br />
    ***Delete Cards***
    ![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/kongscreenshots/deletecards.png?raw=true)<br />

***Integrations***
 * There are two integrations in our Starbucks application. 
 * RabbitMQ for the Backoffice. We choose RabbitMQ for the Backoffice because it is customer question/comments/concerns and we want to make sure we check the questions in person to make sure the customers' questions/comments/concerns are heard by us. (**See Image above**)
 * Cybersource for customer payment. 

 ![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/screenshots/transaction.png?raw=true)<br />
 
***Cloud Deployments***

**Challenges We Faced.** 
 * We have tried deploying the application in GKE however we got stuck on a very persistent error. 
 * First, we thought the problem is one of the yaml files deployed on GKE. 
 
 ![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/screenshots/starbuck5-error.png?raw=true)<br />

 ![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/screenshots/starbucks6-error.png?raw=true)<br />
 
 * But after hours of debugging, we figured that it has something to with the Docker image. And so looking through the docker, we found that there’s an error causing the problem. 

 ![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/screenshots/starbucks7-error.png?raw=true)<br />
 
 * And so we updated the MySQL users and privileges. We updated that the ‘user’ the one who's doing the fetch and insert in the application, can now be accessed by any host. We thought that this might be causing the error and we want to remotely access the database. And so we did this. 
 
 ![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/screenshots/starbucks9-error.png?raw=true)<br />

 * However, we still get the same error. So we have updated the docker-compose. yaml many times, yet we still haven’t figured out this error.
If we know more about how to set up the remote access of MySQL using docker images we could have deployed the Starbucks application on GKE

 ![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/screenshots/starbucks8-error.png?raw=true)<br />


**Challenges We Faced on Reward Card Cashier.** 
 * We tried to create valid coupon card or starbucks reward card that generates a code for the customer to use. However, we did not have enough time to finish it. This is what we have for the current view of reward cards. 

  ![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/screenshots/reward-current.png?raw=true)<br />






