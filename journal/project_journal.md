# Uncalled Four Starbucks Journal

### **CMPE 172 - Starbucks - Weekly Status Reports**
#### CMPE 172 - Starbucks - Week #1 - April 22, 2020 - All Commits accessible [here](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/commits/main)  <br />

***Starting off the project*** [Commit Link](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/commit/7e1f9f69b9fbca3b8125e5915e4b9a8e0349e55e) <br />
For the first-week status report, I started with the Starbucks project by using the Tacos Cloud project in *Spring Boot in Action Fifth Edition by Craig Walls* as my reference. In this code, I pretty much set up the starbucks-api and the starbucks. I wanted to print out the cards created in the starbucks-api that I inserted using Insomnia and display those cards in the starbucks, and so it did. 

***Creating Project Work Board***
Alex primarily coordinated with other members of the group in scheduling future tasks for the project, organizing them concisely into the Project work board available on the team's Github page. It currently has the parts completed by Guiller and the current tasks of both Alexander and Anh.

***Adding styles using CSS*** [Commit Link](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/commit/ee6ea2ce600eb90ea2dd5c2fe8e50a52e234eb3b) <br />
For the second part of my Starbucks development. I wanted to add more style to the pages. So I used CSS to make the pages similar to starbucks.com. I made the page design as simple as possible. Please see the images below.

![alt text](https://github.com/nguyensjsu/cmpe172-guiller-d/blob/main/project/screenshots/welcome.png?raw=true)

![alt text](https://github.com/nguyensjsu/cmpe172-guiller-d/blob/main/project/screenshots/menu.png?raw=true)

***HTML Mappings*** [Commit Link](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/commit/16e840f693989f6cd7e4a2136bcb4719461aae2d) <br />
After adding the design, I wanted to map the buttons on the top left of the menu. So when a button is clicked it will navigate to its respected HTML file. For instance, if the user clicks the menu it will go to the menu page. Now the user can navigate through the pages. 

![alt text](https://github.com/nguyensjsu/cmpe172-guiller-d/blob/main/project/screenshots/welcome.png?raw=true)

![alt text](https://github.com/nguyensjsu/cmpe172-guiller-d/blob/main/project/screenshots/menu.png?raw=true)

***Displaying drinks using array of Strings - first phase*** [Commit Link](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/commit/b9b303940dd72297d8084d68986823c8b0f1523f) <br />
To display the menu, I tried using an array of String first to display the data on the menu page. 

![alt text](https://github.com/nguyensjsu/cmpe172-guiller-d/blob/main/project/screenshots/drinks_array.png?raw=true)

***Displaying drinks using array of Objects - second phase*** [Commit Link](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/commit/b9b303940dd72297d8084d68986823c8b0f1523f) <br />
After understanding a little bit more of how the resources(HTML files) and src(java code) communicate, I replace the string array with a list of objects. 

![alt text](https://github.com/nguyensjsu/cmpe172-guiller-d/blob/main/project/screenshots/drinks_list.png?raw=true)

*This the current menu we have.*

![alt text](https://github.com/nguyensjsu/cmpe172-guiller-d/blob/main/project/screenshots/menu.png?raw=true)

***Data Management using MySQL - third phase*** [Table Commit Link](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/commit/1b7ecbba202c01d1936aa8dd811e271207ec67d2) and [Data Commit Link](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/commit/c3848f5a0d89359353c0f68b64854f51bbb3c7aa) <br />
Seeing that data are displayed correctly on the menu page, the next step is to create a table for all the data that the Starbucks project would need. The image below is the Entity Relational Diagram I created. This is not the final ERD, and as we go, we might remove, add, or modify the relationship between the tables. But for now, this is what we have. 

![alt text](https://github.com/nguyensjsu/cmpe172-guiller-d/blob/main/project/screenshots/starbucks-erd.png?raw=true)

![alt text](https://github.com/nguyensjsu/cmpe172-guiller-d/blob/main/project/screenshots/drinks_sql.png?raw=true)
 
**Add UI design of rewards and payment view** [Commit Link](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/commit/2b94cc7879c7ac5d920d4a6d762a5d01e2c5ec97) <br />
Currently, I'm working on the UI of reward view and payment view of the application. However, I just add the structure of the view, I need to style the pages to make it look nice. 

#### CMPE 172 - Starbucks - Week #2 - April 29, 2020 
***Port Over Cybersource***
I ported over the information from the Cybersource lab to the project, which will be invaluable for the verification of credit cards on our system for the Cashier app. Many of the cybersource integration can be used from the lab, but some modifications must be made for the project.

[Link](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/commit/ccd22b76d916fb2d7401fadc124738c6df515aaa)

![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/screenshots/taskboard-alex-1.png)

***Updating the ERD*** [Commit Link](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/commit/3908699a72c5e0b3709574395ace43ec11d273dd) <br />
When reviewing the ERD, it seems that there are a couple of tables that can be removed. So the updated version of the ERD is displayed below. 
![alt text](https://github.com/nguyensjsu/sp21-172-the-uncalled-four/blob/main/mysql/starbucks-erd-UPDATED.png?raw=true)

***Drinks Data***

```JSX 

-- insert hot coffees
insert into drink ( drink_name, price,  drink_type ) 
values	('Caffe Americano', 3.45, 'Americano' ) ;

insert into drink ( drink_name, price,  drink_type ) 
values	('Blonde Roast', 5.45, 'BrewedCoffee' ) ;

insert into drink ( drink_name, price,  drink_type ) 
values	('Caffe Misto', 3.45, 'BrewedCoffee' ) ;

insert into drink ( drink_name, price,  drink_type ) 
values	('Featured Starbuck Dark Roast Coffee', 3.45, 'BrewedCoffee' ) ;

insert into drink ( drink_name, price,  drink_type ) 
values	('Pike Place Roast', 3.45, 'BrewedCoffee' ) ;

insert into drink ( drink_name, price,  drink_type ) 
values	('Decaf Pike Place Roast', 3.45, 'BrewedCoffee' ) ;

insert into drink ( drink_name, price,  drink_type ) 
values	('Capuccino', 3.45, 'Capuccino' ) ;

insert into drink ( drink_name, price,  drink_type ) 
values	('Espresso', 3.45, 'EspressoShots' ) ;

insert into drink ( drink_name, price,  drink_type ) 
values	('Espresson Con Panna', 3.45, 'EspressoShots' ) ;

insert into drink ( drink_name, price,  drink_type ) 
values	('Flat White', 3.45, 'FlatWhite' ) ;

insert into drink ( drink_name, price,  drink_type ) 
values	('Honey Almondmilk Flat White', 3.45, 'FlatWhite' ) ;

insert into drink ( drink_name, price,  drink_type ) 
values	('Pistachio Latte', 3.45, 'Latte' ) ;

insert into drink ( drink_name, price,  drink_type ) 
values	('Caffe Latte', 3.45, 'Latte' ) ;

insert into drink ( drink_name, price,  drink_type ) 
values	('Cinnamon Dolce Latte', 3.45, 'Latte' ) ;

insert into drink ( drink_name, price,  drink_type ) 
values	('Starbucks Reserve Latte', 3.45, 'Latte' ) ;

insert into drink ( drink_name, price,  drink_type ) 
values	('Starbucks Reserve Hazelnut Bianco Latte', 3.45, 'Latte' ) ;

insert into drink ( drink_name, price,  drink_type ) 
values	('Starbucks Blonde Vanilla Latte', 3.45, 'Latte' ) ;

insert into drink ( drink_name, price,  drink_type ) 
values	('Caramel Macchiato', 3.45, 'Macchiato' ) ;

insert into drink ( drink_name, price,  drink_type ) 
values	('Espresso Macchiato', 3.45, 'Macchiato' ) ;

insert into drink ( drink_name, price,  drink_type ) 
values	('Caffe Mocha', 3.45, 'Mocha' ) ;

insert into drink ( drink_name, price,  drink_type ) 
values	('Starbucks Reserve Dark Chocolate Mocha', 3.45, 'Mocha' ) ;

insert into drink ( drink_name, price,  drink_type ) 
values	('White Chocolate Mocha', 3.45, 'Mocha' ) ;

-- insert recipe names for each drink

-- source of recipe https://athome.starbucks.com/recipe/espresso-caffe-americano/
insert into recipe ( recipe_id, drink_id, recipe ) 
values	(1, 1, 
'STEP ONE
Bring water to a boil and pour into your mug.
STEP TWO
Prepare 2 shots of espresso.
• We recommend brewing Starbucks® Espresso Roast via an espresso machine. If you don’t have an espresso machine, you can use a stove-top moka pot to make espresso-like coffee. To learn more, read our stove-pot moka pot guide here.
• You can also brew Starbucks® Espresso Roast by Nespresso® capsules for authentic café-style espresso.
STEP THREE
Slowly pour your 2 shots of espresso into the water and enjoy.' ) ;

insert into recipe ( recipe_id, drink_id, recipe ) 
values	(2, 2, 
'STEP ONE
Prepare 1 shot of Starbucks® Blonde® Espresso Roast by Nespresso® and pour into a mug.
• If you don’t have a Nespresso machine, you can still make a Vanilla Latte with a stove-top moka pot and Starbucks® Espresso Roast for a strong espresso-like coffee. To learn more, read our stove-pot moka pot guide here.
STEP TWO
Froth milk using your preferred method. For more information on how to froth your milk, see our frothing guide here.
STEP THREE
Add Starbucks® Vanilla Syrup to your mug of espresso and gently stir in.
STEP FOUR
Gently pour your frothed milk into your mug until it’s almost full.
STEP FIVE
Spoon any remaining foam onto the top of your beverage, and enjoy.' ) ;

........


```




