CREATE SCHEMA `restaurant` ;

use restaurant;

create table `account`( 
	id int not null auto_increment,
	
    email varchar(100) not null,
	password varchar(100) not null,
    role varchar(20) not null,
	isActive bool not null,
    
    avatar varchar(500),
    fullname varchar(50) not null,
    phone varchar(10) not null,
    gender varchar(1) not null,
    dob date not null,
    
    created_by int,
    updated_by int,
    created_at datetime not null default now(),
	updated_at datetime on update now(),
    
	primary key (id)
);

create table `session`( 
	id int not null auto_increment,
    
    session_number varchar(100) not null,
    position varchar(100) not null,
    status varchar(50) not null,
    
    created_by int,
    updated_by int,
    created_at datetime not null default now(),
	updated_at datetime on update now(),
	
	primary key (id)
);

create table customer_order( 
	id int not null auto_increment,
    
    session_id int not null,
    
    created_by int,
    updated_by int,
    created_at datetime not null default now(),
	updated_at datetime on update now(),
	
	primary key (id)
);

create table order_detail( 
	id int not null auto_increment,
    
    order_id int not null,
    item_id int not null,
    
    created_by int,
    updated_by int,
    created_at datetime not null default now(),
	updated_at datetime on update now(),
	
	primary key (id)
);

create table order_status( 
	id int not null auto_increment,
    
    order_id int not null,
    status varchar(50) not null,
    content varchar(500),
    
    created_by int,
    created_at datetime not null default now(),
	
	primary key (id)
);

create table item( 
	id int not null auto_increment,
    
    name varchar(50) not null,
    price double not null,
    description varchar(500),
    isAvailable bool not null,
    category_id int,
    
    created_by int,
    updated_by int,
    created_at datetime not null default now(),
	updated_at datetime on update now(),
	
	primary key (id)
);

create table category( 
	id int not null auto_increment,
    
    name varchar(50) not null,
    
    created_by int,
    updated_by int,
    created_at datetime not null default now(),
	updated_at datetime on update now(),
	
	primary key (id)
);

/****ADD FK****/

alter table customer_order
	add constraint FK_order_session
	foreign key (session_id) references `session` (id)
	ON DELETE RESTRICT ON UPDATE CASCADE; 
    
alter table order_detail
	add constraint FK_order_detail_order
	foreign key (order_id) references customer_order (id)
	ON DELETE RESTRICT ON UPDATE CASCADE; 
    
alter table order_detail
	add constraint FK_order_detail_item
	foreign key (item_id) references item (id)
	ON DELETE RESTRICT ON UPDATE CASCADE; 

alter table order_status
	add constraint FK_order_status_oder
	foreign key (order_id) references customer_orders (id)
	ON DELETE RESTRICT ON UPDATE CASCADE;

alter table item
	add constraint FK_item_category
	foreign key (category_id) references category (id)
	ON DELETE SET NULL ON UPDATE CASCADE;      
    
/****ADD UNIQUE KEY****/

ALTER TABLE `account`
ADD CONSTRAINT UK_account_email UNIQUE (email);

ALTER TABLE `session`
ADD CONSTRAINT UK_account_session_number UNIQUE (session_number);

/*insert import */

insert into `account` (id, email, password, role, isActive, avatar, fullname, phone, gender, dob, created_at, updated_at, created_by, updated_by) values
(1, 'test@test.com', '123456', 'MANAGER', true, 'https://st2.depositphotos.com/1009634/7235/v/600/depositphotos_72350117-stock-illustration-no-user-profile-picture-hand.jpg', 'Nguyen Van An', '0909333555', 'M', '2000-01-01', now(), null,1, null),
(2, 'test2@test.com', '123456', 'STAFF', true, 'https://st2.depositphotos.com/1009634/7235/v/600/depositphotos_72350117-stock-illustration-no-user-profile-picture-hand.jpg', 'Nguyen Thi Be', '0909333666', 'F', '2001-01-01', now(), now(),1, 1);

insert into category (id, name, created_at, updated_at, created_by, updated_by) values
(1, 'food', now(), now(),1, 1),
(2, 'beverage', now(), now(),1, 1);

insert into item (id, name, price, description, isAvailable, category_id, created_at, updated_at, created_by, updated_by) values
(1, 'sua da', 15000, 'ca phe sua da nha lam', true, 2, now(), now(),1, 1),
(2, 'banh mi', 18000, 'banh mi kep heo quay', true, 1, now(), now(),1, 1);