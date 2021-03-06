-- Drop the database if it already exists
DROP DATABASE IF EXISTS Travel;
-- Create database
CREATE DATABASE IF NOT EXISTS Travel;
USE Travel;

CREATE TABLE `Tour` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` varchar(255) NOT NULL ,
	`id_regional` INT(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `TourDetail` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`id_tourType` INT NOT NULL,
	`id_regional` INT NOT NULL,
	`id_tour` INT NOT NULL,
    `id_user` INT NOT NULL,
	`title` varchar(255) NOT NULL,
	`description` TEXT NOT NULL,
	`schedule` varchar(255) NOT NULL,
	`day_start` DATETIME NOT NULL,
	`vehicle` varchar(255) NOT NULL,
	`departure_from` varchar(255) NOT NULL,
	`price` int NOT NULL,
	`quantity` INT NOT NULL,
	`notes` TEXT(255) NOT NULL,
	`star` TINYINT NOT NULL,
	PRIMARY KEY (`id`)
);
CREATE TABLE `Comment` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`content` varchar(255) NOT NULL,
    `id_tourDetail` INT NOT NULL,
    `id_user` INT NOT NULL,
	PRIMARY KEY (`id`)
);
CREATE TABLE `TourType` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Regional` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`id_tourType` INT NOT NULL,
	`name_regional` varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Image` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`id_tourDetail` INT NOT NULL,
	`image` varchar(255),
	PRIMARY KEY (`id`)
);

CREATE TABLE `Invoice` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`id_tourOder` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `TourOrder` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`id_user` INT NOT NULL,
	`id_payment` INT NOT NULL,
	`id_tourDetail` INT NOT NULL,
	`name_order` varchar(255) NOT NULL,
	`date_order` DATETIME NOT NULL,
	`cost_order` int NOT NULL,
	`number_of_people` INT NOT NULL,
	`number_of_rooms` INT NOT NULL,
    Key (id),
	PRIMARY KEY (`id_user`, `id_tourDetail`)
);

CREATE TABLE `Payment` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name_payment` varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `User` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`fullname` varchar(255) NOT NULL,
	`email` varchar(255) UNIQUE NOT NULL ,
	`phone` varchar(255) NOT NULL,
	`address` varchar(255) NOT NULL,
	`username` varchar(255) UNIQUE NOT NULL,
	`password` varchar(255) NOT NULL,
	`id_role` INT(255) NOT NULL,
	`active` BOOLEAN NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Role` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name_role` varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `User_Token` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`id_user` INT NOT NULL,
	`user_token` varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
);

ALTER TABLE `Tour` ADD CONSTRAINT `Tour_fk0` FOREIGN KEY (`id_regional`) REFERENCES `Regional`(`id`);

ALTER TABLE `TourDetail` ADD CONSTRAINT `TourDetail_fk0` FOREIGN KEY (`id_tourType`) REFERENCES `TourType`(`id`);

ALTER TABLE `TourDetail` ADD CONSTRAINT `TourDetail_fk1` FOREIGN KEY (`id_regional`) REFERENCES `Regional`(`id`);

ALTER TABLE `TourDetail` ADD CONSTRAINT `TourDetail_fk2` FOREIGN KEY (`id_tour`) REFERENCES `Tour`(`id`);

ALTER TABLE `TourDetail` ADD CONSTRAINT `TourDetail_fk3` FOREIGN KEY (`id_user`) REFERENCES `User`(`id`);

ALTER TABLE `Regional` ADD CONSTRAINT `Regional_fk0` FOREIGN KEY (`id_tourType`) REFERENCES `TourType`(`id`);

ALTER TABLE `Image` ADD CONSTRAINT `Image_fk0` FOREIGN KEY (`id_tourDetail`) REFERENCES `TourDetail`(`id`);

ALTER TABLE `Invoice` ADD CONSTRAINT `Invoice_fk0` FOREIGN KEY (`id_tourOder`) REFERENCES `TourOrder`(`id`);

ALTER TABLE `TourOrder` ADD CONSTRAINT `TourOrder_fk0` FOREIGN KEY (`id_user`) REFERENCES `User`(`id`);

ALTER TABLE `TourOrder` ADD CONSTRAINT `TourOrder_fk1` FOREIGN KEY (`id_payment`) REFERENCES `Payment`(`id`);

ALTER TABLE `TourOrder` ADD CONSTRAINT `TourOrder_fk2` FOREIGN KEY (`id_tourDetail`) REFERENCES `TourDetail`(`id`);

ALTER TABLE `User` ADD CONSTRAINT `User_fk0` FOREIGN KEY (`id_role`) REFERENCES `Role`(`id`);

ALTER TABLE `User_Token` ADD CONSTRAINT `User_Token_fk0` FOREIGN KEY (`id_user`) REFERENCES `User`(`id`);

ALTER TABLE `Comment` ADD CONSTRAINT `Comment_fk0` FOREIGN KEY (`id_tourDetail`) REFERENCES `TourDetail`(`id`);

ALTER TABLE `Comment` ADD CONSTRAINT `Comment_fk1` FOREIGN KEY (`id_user`) REFERENCES `User`(`id`);



INSERT INTO `Role` (`name_role`)
VALUE				('Admin'),
					('User'),
                   ('Manager');

INSERT INTO `User` 	(`fullname`,			`email`,						`phone`,														`address`,		`username`, 	`password` ,	`id_role`,`active`	)
VALUE				('Nguyen Quan',		'quan123@gmail.com',		'033682712387',		'H?? N???i'	,		'quan123456',			'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	 ,	1,true ),
					('Tr???n Thai Minh',		'abc@gmail.com',		'023344312387',		'S??i G??n'	,		'thaiminh',			'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	 ,	2,false );


INSERT INTO `Tourtype` (`name`)
VALUE					('Du l???ch trong n?????c'),
						('Du l???ch ngo??i n?????c'),
                        ('Du l???ch Bi???n ?????o'),
                        ('Du l???ch r???ng');
                        
INSERT INTO `Regional` (`id_tourType`,`name_regional`)
VALUE					(1,'Du l???ch Mi???n b???c'),
						(1,'Du l???ch T??y b???c'),
                        (1,'Du l???ch Mi???n Nam'),
                        (1,'Du l???ch Mi???n Trung');

INSERT INTO `Tour` (`name`,`id_regional`)
VALUE				('Tour du lich',1),
					('Tour du lich1',3),
                    ('Tour du lich2',2),
                    ('Tour du lich3',4);



INSERT INTO `TourDetail` (`id`,`id_tourType`,`id_regional`,`id_tour`,`id_user`,`title`,`description`,`schedule`,`day_start`,`vehicle`,`departure_from`,`price`,`quantity`,`notes`,`star`)
VALUE					(1		,1				,1			,1		,1		,'Du l???ch Mi???n B???c H?? N???i -?????n H??ng t??? S??i G??n','Tour T??y B???c N???i ti???ng v???i nhi???u ?????a ??i???m tham quan, 
vui ch??i mang ?????m b???n s???c v??n h??a d??n t???c, thi??n nhi??n h??ng v??, th?? m???ng. T???t c??? khung c???nh n??i
 n??i r???ng n??y d?????ng nh?? ?????u ???????c thi??n nhi??n ???s???p ?????t??? m???t c??ch r???t t??i t??nh,
 s???n s??ng l??m xao xuy???n t??m h???n c???a b???t c??? ai ?????t ch??n ?????n d??y. B???n ???? d??? ?????nh s??? du l???ch T??y 
 B???c m???t l???n ????? t??? m??nh c???m nh???n nh???ng n??t ?????p c???a n??i ????y xem c?? ????ng nh?? l???i qu???ng b?? hay kh??ng? H??y nhanh tay ?????t tour du l???ch H?? t???i Du L???ch Vi???t ??i v?? c???m nh???n nh??!',
 '6 ng??y 5 ????m','2020-09-14','M??y bay','H?? N???i',20000000,4,'Sau khi ????ng k??, thanh to??n ??t nh???t 50% ti???n c???c v?? ????ng h???t 100% tr?????c  kh???i h??nh 10 ng??y',5);
INSERT INTO `Image` (`id`, `id_tourDetail`, `image`)
VALUE 				(1,		1,			 'https://res.cloudinary.com/dv0zrozae/image/upload/v1645691520/travel2_shqdqo.jpg');


INSERT INTO `Payment` (`name_payment`)
value				 ('Thanh to??n qua TKNG');







