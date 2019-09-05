INSERT INTO share_jpa.account(email, password, name, created_at, created_by, modified_by, modified_at)
VALUES ("test1@asd.com", "{noop}test", "test user1", now(), "test user1", "test user1", now());
INSERT INTO share_jpa.account(email, password, name, created_at, created_by, modified_by, modified_at)
VALUES ("test2@asd.com", "{noop}test", "test user2", now(), "test user2", "test user2", now());
INSERT INTO share_jpa.account(email, password, name, created_at, created_by, modified_by, modified_at)
VALUES ("test3@asd.com", "{noop}test", "test user3", now(), "test user3", "test user3", now());
INSERT INTO share_jpa.account(email, password, name, created_at, created_by, modified_by, modified_at)
VALUES ("test4@asd.com", "{noop}test", "test user4", now(), "test user4", "test user4", now());
INSERT INTO share_jpa.account(email, password, name, created_at, created_by, modified_by, modified_at)
VALUES ("test5@asd.com", "{noop}test", "test user5", now(), "test user5", "test user5", now());

INSERT INTO share_jpa.role(name, account_id)
VALUES ("BASIC", 1);
INSERT INTO share_jpa.role(name, account_id)
VALUES ("ADMIN", 1);
INSERT INTO share_jpa.role(name, account_id)
VALUES ("BASIC", 2);
INSERT INTO share_jpa.role(name, account_id)
VALUES ("BASIC", 3);
INSERT INTO share_jpa.role(name, account_id)
VALUES ("BASIC", 4);
INSERT INTO share_jpa.role(name, account_id)
VALUES ("BASIC", 5);

INSERT INTO share_jpa.book(title, publisher, author, description, owner, created_at, created_by, modified_at,
                           modified_by)
VALUES ("test book1", "test publisher", "test book author", "description for test book1", "test1@asd.com", now(),
        "test1@asd.com", now(), "test1@asd.com");
INSERT INTO share_jpa.book(title, publisher, author, description, owner, created_at, created_by, modified_at,
                           modified_by)
VALUES ("test book2", "test publisher", "test book author", "description for test book2", "test1@asd.com", now(),
        "test1@asd.com", now(), "test1@asd.com");
INSERT INTO share_jpa.book(title, publisher, author, description, owner, created_at, created_by, modified_at,
                           modified_by)
VALUES ("test book3", "test publisher", "test book author", "description for test book3", "test1@asd.com", now(),
        "test1@asd.com", now(), "test1@asd.com");
INSERT INTO share_jpa.book(title, publisher, author, description, owner, created_at, created_by, modified_at,
                           modified_by)
VALUES ("test book4", "test publisher", "test book author", "description for test book4", "test1@asd.com", now(),
        "test1@asd.com", now(), "test1@asd.com");
INSERT INTO share_jpa.book(title, publisher, author, description, owner, created_at, created_by, modified_at,
                           modified_by)
VALUES ("test book5", "test publisher", "test book author", "description for test book5", "test1@asd.com", now(),
        "test1@asd.com", now(), "test1@asd.com");

INSERT INTO share_jpa.book_image(image_url, sort_no, book_id)
VALUES ("a", 0, 1);
INSERT INTO share_jpa.book_image(image_url, sort_no, book_id)
VALUES ("b", 1, 1);
INSERT INTO share_jpa.book_image(image_url, sort_no, book_id)
VALUES ("c", 2, 1);
INSERT INTO share_jpa.book_image(image_url, sort_no, book_id)
VALUES ("a", 0, 2);
INSERT INTO share_jpa.book_image(image_url, sort_no, book_id)
VALUES ("b", 1, 2);
INSERT INTO share_jpa.book_image(image_url, sort_no, book_id)
VALUES ("c", 2, 2);
INSERT INTO share_jpa.book_image(image_url, sort_no, book_id)
VALUES ("a", 0, 3);
INSERT INTO share_jpa.book_image(image_url, sort_no, book_id)
VALUES ("b", 1, 3);
INSERT INTO share_jpa.book_image(image_url, sort_no, book_id)
VALUES ("c", 2, 3);
INSERT INTO share_jpa.book_image(image_url, sort_no, book_id)
VALUES ("a", 0, 4);
INSERT INTO share_jpa.book_image(image_url, sort_no, book_id)
VALUES ("b", 1, 4);
INSERT INTO share_jpa.book_image(image_url, sort_no, book_id)
VALUES ("c", 2, 4);
INSERT INTO share_jpa.book_image(image_url, sort_no, book_id)
VALUES ("a", 0, 5);
INSERT INTO share_jpa.book_image(image_url, sort_no, book_id)
VALUES ("b", 1, 5);
INSERT INTO share_jpa.book_image(image_url, sort_no, book_id)
VALUES ("c", 2, 5);