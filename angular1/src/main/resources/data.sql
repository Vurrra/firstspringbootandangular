insert into user_entity values(101,'admin','ravi@gmail.com','admin','9876543210','ravi');
insert into user_entity values(102,'admin','kishore@gmail.com','admin','9887543210','kishore');
insert into user_entity values(103,'vendor','vendor1@gmail.com','vendor','9986543210','vendor1');
insert into user_entity values(104,'vendor','vendor2@gmail.com','vendor','9654543210','vendor2');
insert into user_entity values(105,'customer','customer1@gmail.com','customer','9196543210','customer1');
insert into user_entity values(106,'customer','customer2@gmail.com','customer','9006543210','customer2');
insert into user_entity values(107,'customer','customer3@gmail.com','customer','9656543210','customer3');
insert into user_entity values(108,'customer','customer4@gmail.com','customer','9816543210','customer4');
insert into vehicle_entity values(10001,'available',300,'Hyundai i10','ap29bc9898',103);
insert into vehicle_entity values(10002,'available',400,'Hyundai i20','ap10jk9018',104);
insert into vehicle_entity values(10003,'available',350,'Hyundai i10 grand','ap29hk1234',103);
insert into vehicle_entity values(10004,'available',250,'Hyundai santro','ap29lk1456',104);
insert into vehicle_entity values(10005,'available',350,'Ford ecosport','ap29la0987',103);
insert into booking_entity values(100001,8,'2021-12-11 22:20:20',3200,'2021-12-11 14:20:20','Booked',105,10002);
insert into booking_entity values(100002,8,'2021-12-12 22:20:20',2800,'2021-12-12 14:20:20','Booked',106,10003);
insert into booking_entity values(100003,8,'2021-12-13 22:20:20',2400,'2021-12-13 14:20:20','Booked',107,10001);
insert into booking_entity values(100004,8,'2021-12-14 22:20:20',2800,'2021-12-14 14:20:20','Booked',108,10005);
insert into feedback_entity values(495,'Excellent Vehicle, Good Service!',10,105,10001);
insert into feedback_entity values(496,'Good Vehicle, Sweet Service!',9,104,10002);
insert into feedback_entity values(497,'Smooth Vehicle, loved the Service!',10,108,10003);
insert into feedback_entity values(498,'Worst Vehicle Ever!',3,106,10004);
insert into feedback_entity values(499,'Decent Vehicle, Satisfied Service!',7,107,10005);