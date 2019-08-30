insert into share_jpa.account(email, password, name, created_at, created_by, modified_by, modified_at) values ("test1@asd.com", "{noop}test", "test user1", now(), "test user1", "test user1", now());
insert into share_jpa.account(email, password, name, created_at, created_by, modified_by, modified_at) values ("test3@asd.com", "{noop}test", "test user3", now(), "test user1", "test user1", now());
insert into share_jpa.account(email, password, name, created_at, created_by, modified_by, modified_at) values ("test4@asd.com", "{noop}test", "test user4", now(), "test user1", "test user1", now());
insert into share_jpa.account(email, password, name, created_at, created_by, modified_by, modified_at) values ("test2@asd.com", "{noop}test", "test user2", now(), "test user1", "test user1", now());
insert into share_jpa.account(email, password, name, created_at, created_by, modified_by, modified_at) values ("test5@asd.com", "{noop}test", "test user5", now(), "test user1", "test user1", now());

insert into share_jpa.role(name, account_id) VALUES ("BASIC", 1);
insert into share_jpa.role(name, account_id) VALUES ("BASIC", 2);
insert into share_jpa.role(name, account_id) VALUES ("BASIC", 3);
insert into share_jpa.role(name, account_id) VALUES ("BASIC", 4);
insert into share_jpa.role(name, account_id) VALUES ("BASIC", 5);
insert into share_jpa.role(name, account_id) VALUES ("ADMIN", 5);