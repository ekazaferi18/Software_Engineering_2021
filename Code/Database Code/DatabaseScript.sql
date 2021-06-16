drop database bms;
create database bms;
use bms;

create table Users(
	userID int NOT NULL AUTO_INCREMENT,
    username varchar(10),
    password varchar(60),
    firstname varchar(30),
    lastname varchar(30),
    email varchar(30),
    address varchar(50),
    access_level varchar(10),
    total_points int,
    wage double,
    primary key(userID),
    deleted boolean default false,
    created_date timestamp default current_timestamp,
    updated_date timestamp default current_timestamp on update current_timestamp
);

create table product_categories(
	categoryID int NOT NULL AUTO_INCREMENT,
    name varchar(20),
    description varchar(50),
    primary key(categoryID),
    deleted boolean default false,
    created_date timestamp default current_timestamp,
    updated_date timestamp default current_timestamp on update current_timestamp
);

create table Products(
	productID int NOT NULL AUTO_INCREMENT,
    name varchar(20),
    description varchar(50),
    quantity int,
    price double,
    categoryID int,
    expiration_date timestamp,
    PRIMARY KEY(productID),
    foreign key (categoryID) references product_categories(categoryID),
    deleted boolean  default false,
    created_date timestamp default current_timestamp,
    updated_date timestamp default current_timestamp on update current_timestamp
);

create table Orders(
	orderID int NOT NULL AUTO_INCREMENT,
    payment_type varchar(10),
    clientID int,
    sellerID int,
    total double,
    points_earned int,
    PRIMARY KEY(orderID),
    foreign key (clientID) references Users(userID),
    foreign key (sellerID) references Users(userID),
    deleted boolean  default false,
    created_date timestamp default current_timestamp,
    updated_date timestamp default current_timestamp on update current_timestamp
);

create table ProcurementOrder(
	pOrderID int not null AUTO_INCREMENT,
    sellerName varchar(20),
    total double,
    primary key(pOrderID),
    deleted boolean  default false,
    created_date timestamp default current_timestamp,
    updated_date timestamp default current_timestamp on update current_timestamp
);

create table ProcurementOrderDetails(
	pOrderDetailID int not null auto_increment,
    pOrderID int,
    productID int,
    quantity int,
    pricePerUnit double,
    total_price double,
    primary key(pOrderDetailID),
    foreign key (pOrderID) references ProcurementOrder(pOrderID),
    foreign key (productID) references Products(productID),
    deleted boolean  default false,
    created_date timestamp default current_timestamp,
    updated_date timestamp default current_timestamp on update current_timestamp
);

create table OrderDetails(
	orderDetailID INT NOT NULL AUTO_INCREMENT,
    productID int,
    orderID int,
    quantity int,
    pricePerUnit double,
    total_price double,
    PRIMARY KEY(orderDetailID),
    foreign key (productID) references Products(productID),
    foreign key (orderID) references Orders(orderID),
    deleted boolean  default false,
    created_date timestamp default current_timestamp,
    updated_date timestamp default current_timestamp on update current_timestamp
);	

create table Deliveries(
	deliveryID INT NOT NULL AUTO_INCREMENT,
    orderID int,
    userID int,
    destination varchar(100),
    status varchar(10),
    PRIMARY KEY(deliveryID),
    foreign key (userID) references Users(userID),
    foreign key (orderID) references Orders(orderID),
    deleted boolean  default false,
    created_date timestamp default current_timestamp,
    updated_date timestamp default current_timestamp on update current_timestamp
);

create table Cart(
	cartID INT NOT NULL AUTO_INCREMENT,
    userID int,
    productID int,
    quantity int,
    total double,
    PRIMARY KEY(cartID),
    foreign key (userID) references Users(userID),
    foreign key (productID) references Products(productID),
    deleted boolean  default false,
    created_date timestamp default current_timestamp,
    updated_date timestamp default current_timestamp on update current_timestamp
);

create table FavouritesLists(
	favouriteListID INT NOT NULL AUTO_INCREMENT,
    userID int,
    productID int,
    PRIMARY KEY(favouriteListID),
    foreign key (userID) references Users(userID),
    foreign key (productID) references Products(productID),
    deleted boolean  default false,
    created_date timestamp default current_timestamp,
    updated_date timestamp default current_timestamp on update current_timestamp
);

create table Statistics(
	statsID int not null auto_increment,
    month timestamp,
    year int,
    wages double,
    pOrdersTotal double,
    revenue double,
    income double,
    primary key(statsID),
    deleted boolean  default false,
    created_date timestamp default current_timestamp,
    updated_date timestamp default current_timestamp on update current_timestamp
);

create table Requests(
	requestID int not null auto_increment,
    managerID int,
    message varchar(100),
    status varchar(15),
    primary key(requestID),
    foreign key (managerID) references Users(userID),
    deleted boolean  default false,
    created_date timestamp default current_timestamp,
    updated_date timestamp default current_timestamp on update current_timestamp
);

delimiter $$
CREATE TRIGGER priceUpdates before insert ON OrderDetails
 FOR EACH ROW 
	BEGIN
    set new.pricePerUnit = (select price from products where productID = new.productID);
    set new.total_price = new.pricePerUnit * new.quantity;
	end$$
delimiter ;


delimiter $$
CREATE TRIGGER total after update ON OrderDetails for each row
    BEGIN
    declare sum double;
    set sum = (select SUM(total_price) from OrderDetails where orderID = new.orderID);
    update Orders set total = sum where orderID = new.orderID;
	end$$
delimiter ;

delimiter $$
CREATE TRIGGER priceUpdatesPO before insert ON ProcurementOrderDetails
 FOR EACH ROW 
	BEGIN
    set new.total_price = new.pricePerUnit * new.quantity;
	end$$
delimiter ;


delimiter $$
CREATE TRIGGER totalPO after update ON ProcurementOrderDetails for each row
    BEGIN
    declare sum double;
    set sum = (select SUM(total_price) from ProcurementOrderDetails where pOrderID = new.pOrderID);
    update ProcurementOrder set total = sum where pOrderID = new.pOrderID;
	end$$
delimiter ;

delimiter $$
CREATE TRIGGER priceUpdatesStat before insert ON Statistics
 FOR EACH ROW 
	BEGIN
    declare sumOrders double;
    declare sumPOrders double;
    declare sumWages double; 
    set sumOrders = (select SUM(total) from Orders where month(new.month) = month(Orders.created_date));
    set sumPOrders = (select SUM(total) from ProcurementOrder where month(new.month) = month(Orders.created_date));
    set sumWages = (select SUM(wage) from Users where wage <> null);
    set new.revenue = sumOrders;
    set new.pOrdersTotal = sumPOrders;
    set new.wages = sumWages;
    set new.income = sumOrders - sumPOrders - sumWages;
	end$$
delimiter ;












