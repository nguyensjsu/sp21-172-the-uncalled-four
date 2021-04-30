
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

insert into recipe ( recipe_id, drink_id, recipe ) 
values	(3, 3, 'Caffe Misto Recipe' ) ;

insert into recipe ( recipe_id, drink_id, recipe ) 
values	(4, 4,  'Featured Starbuck Dark Roast Coffee Recipe' ) ;

insert into recipe ( recipe_id, drink_id, recipe ) 
values	(5, 5,  'Pike Place Roast Recipe' ) ;

insert into recipe ( recipe_id, drink_id, recipe ) 
values	(6, 6,  'Decaf Pike Place Roast Recipe' ) ;

insert into recipe ( recipe_id, drink_id, recipe ) 
values	(7, 7,  'Capuccino Recipe' ) ;

insert into recipe ( recipe_id, drink_id, recipe ) 
values	(8, 8, 'Espresso Recipe' ) ;

insert into recipe ( recipe_id, drink_id, recipe ) 
values	(9, 9,  'Espresson Con Panna Recipe' ) ;

insert into recipe ( recipe_id, drink_id, recipe ) 
values	(10, 10,  'Flat White Recipe' ) ;

insert into recipe ( recipe_id, drink_id, recipe ) 
values	(11, 11,  'Honey Almondmilk Flat White Recipe' ) ;

insert into recipe ( recipe_id, drink_id, recipe ) 
values	(12, 12,  'Pistachio Latte Recipe' ) ;

insert into recipe ( recipe_id, drink_id, recipe ) 
values	(13, 13,  'Caffe Latte Recipe' ) ;

insert into recipe ( recipe_id, drink_id, recipe ) 
values	(14, 14,  'Cinnamon Dolce Latte Recipe' ) ;

insert into recipe ( recipe_id, drink_id, recipe ) 
values	(15, 15,  'Starbucks Reserve Latte Recipe' ) ;

insert into recipe ( recipe_id, drink_id, recipe ) 
values	(16, 16,  'Starbucks Reserve Hazelnut Bianco Latte Recipe' ) ;

insert into recipe ( recipe_id, drink_id, recipe ) 
values	(17, 17,  'Starbucks Blonde Vanilla Latte Recipe' ) ;

insert into recipe ( recipe_id, drink_id, recipe ) 
values	(18, 18,  'Caramel Macchiato Recipe' ) ;

insert into recipe ( recipe_id, drink_id, recipe ) 
values	(19, 19,  'Espresso Macchiato Recipe' ) ;

insert into recipe ( recipe_id, drink_id, recipe ) 
values	(20, 20, 'Caffe Mocha Recipe' ) ;

insert into recipe ( recipe_id, drink_id, recipe ) 
values	(21, 21,  'Starbucks Reserve Dark Chocolate Mocha Recipe' ) ;

insert into recipe ( recipe_id, drink_id, recipe ) 
values	(22, 22,  'White Chocolate Mocha Recipe' ) ;

-- insert ingredients for each recipe

-- source of ingrdients https://athome.starbucks.com/recipe/espresso-caffe-americano/
insert into ingredients ( recipe_id, ingredient_id, ingredients ) 
values	(1, 1, 
'Starbucks® Espresso Roast (or 2 Starbucks® Espresso Roast by Nespresso® capsules)
1 ¼ cups hot water') ;

-- https://athome.starbucks.com/recipe/espresso-blonde-vanilla-latte/
insert into ingredients ( recipe_id, ingredient_id, ingredients ) 
values	(2, 2, 
 '1 Starbucks® Blonde® Espresso Roast by Nespresso® capsule
1 cup milk of your choice
2 Tbsp Starbucks® Vanilla Syrup') ;

insert into ingredients ( recipe_id, ingredient_id, ingredients ) 
values	(3, 3, 'Caffe Misto ingredients' ) ;

insert into ingredients ( recipe_id, ingredient_id, ingredients ) 
values	(4, 4,  'Featured Starbuck Dark Roast Coffee ingredients' ) ;

insert into ingredients ( recipe_id, ingredient_id, ingredients ) 
values	(5, 5,  'Pike Place Roast ingredients' ) ;

insert into ingredients ( recipe_id, ingredient_id, ingredients ) 
values	(6, 6,  'Decaf Pike Place Roast ingredients' ) ;

insert into ingredients ( recipe_id, ingredient_id, ingredients ) 
values	(7, 7,  'Capuccino ingredients' ) ;

insert into ingredients ( recipe_id, ingredient_id, ingredients ) 
values	(8, 8, 'Espresso ingredients' ) ;

insert into ingredients ( recipe_id, ingredient_id, ingredients ) 
values	(9, 9,  'Espresson Con Panna ingredients' ) ;

insert into ingredients ( recipe_id, ingredient_id, ingredients ) 
values	(10, 10,  'Flat White ingredients' ) ;

insert into ingredients ( recipe_id, ingredient_id, ingredients ) 
values	(11, 11,  'Honey Almondmilk Flat White ingredients' ) ;

insert into ingredients ( recipe_id, ingredient_id, ingredients ) 
values	(12, 12,  'Pistachio Latte ingredients' ) ;

insert into ingredients ( recipe_id, ingredient_id, ingredients ) 
values	(13, 13,  'Caffe Latte ingredients' ) ;

insert into ingredients ( recipe_id, ingredient_id, ingredients ) 
values	(14, 14,  'Cinnamon Dolce Latte ingredients' ) ;

insert into ingredients ( recipe_id, ingredient_id, ingredients ) 
values	(15, 15,  'Starbucks Reserve Latte ingredients' ) ;

insert into ingredients ( recipe_id, ingredient_id, ingredients ) 
values	(16, 16,  'Starbucks Reserve Hazelnut Bianco Latte ingredients' ) ;

insert into ingredients ( recipe_id, ingredient_id, ingredients ) 
values	(17, 17,  'Starbucks Blonde Vanilla Latte ingredients' ) ;

insert into ingredients ( recipe_id, ingredient_id, ingredients ) 
values	(18, 18,  'Caramel Macchiato ingredients' ) ;

insert into ingredients ( recipe_id, ingredient_id, ingredients ) 
values	(19, 19,  'Espresso Macchiato ingredients' ) ;

insert into ingredients ( recipe_id, ingredient_id, ingredients ) 
values	(20, 20, 'Caffe Mocha ingredients' ) ;

insert into ingredients ( recipe_id, ingredient_id, ingredients ) 
values	(21, 21,  'Starbucks Reserve Dark Chocolate Mocha ingredients' ) ;

insert into ingredients ( recipe_id, ingredient_id, ingredients ) 
values	(22, 22,  'White Chocolate Mocha ingredients' ) ;





