insert into share_jpa.account(email, password, name) values ("test1@asd.com", "{noop}test", "test user1");
insert into share_jpa.account(email, password, name) values ("test2@asd.com", "{noop}test", "test user2");
insert into share_jpa.account(email, password, name) values ("test3@asd.com", "{noop}test", "test user3");
insert into share_jpa.account(email, password, name) values ("test4@asd.com", "{noop}test", "test user4");
insert into share_jpa.account(email, password, name) values ("test5@asd.com", "{noop}test", "test user5");

insert into share_jpa.role(name, account_id) VALUES ("BASIC", 1);
insert into share_jpa.role(name, account_id) VALUES ("BASIC", 2);
insert into share_jpa.role(name, account_id) VALUES ("BASIC", 3);
insert into share_jpa.role(name, account_id) VALUES ("BASIC", 4);
insert into share_jpa.role(name, account_id) VALUES ("BASIC", 5);
insert into share_jpa.role(name, account_id) VALUES ("ADMIN", 5);