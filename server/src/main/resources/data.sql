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

INSERT INTO share_jpa.category(name, display, created_at, created_by, modified_at, modified_by)
VALUES ("운영체제", 1, now(), "SYS", now(), "SYS");
INSERT INTO share_jpa.category(name, display, created_at, created_by, modified_at, modified_by)
VALUES ("네트워크", 1, now(), "SYS", now(), "SYS");
INSERT INTO share_jpa.category(name, display, created_at, created_by, modified_at, modified_by)
VALUES ("프로그래밍 언어", 1, now(), "SYS", now(), "SYS");

INSERT INTO share_jpa.book(title, author, publisher, category_id, description, image_url, owner_id, created_at, created_by, modified_at, modified_by)
VALUES ("이것이 우분투 리눅스다", "우재남", "한빛미디어", 1,"description for test book1", "https://placeimg.com/200/300/animals", 1, now(), "test1@asd.com", now(), "test1@asd.com");
INSERT INTO share_jpa.book(title, author, publisher, category_id, description, image_url, owner_id, created_at, created_by, modified_at, modified_by)
VALUES ("리눅스 시스템 원리와 실제", "창병모", "생능출판사", 1,"description ", "https://placeimg.com/200/300/animals", 1, now(), "test1@asd.com", now(), "test1@asd.com");
INSERT INTO share_jpa.book(title, author, publisher, category_id, description, image_url, owner_id, created_at, created_by, modified_at, modified_by)
VALUES ("컴퓨터 네트워킹 : 하향식 접근(7판)", "James F. Kurose", "퍼스트북", 2,"description for test book3", "https://placeimg.com/200/300/animals", 1, now(), "test1@asd.com", now(), "test1@asd.com");
INSERT INTO share_jpa.book(title, author, publisher, category_id, description, image_url, owner_id, created_at, created_by, modified_at, modified_by)
VALUES ("두근두근 파이썬", "천인국", "생능출판사", 3,"description ", "https://placeimg.com/200/300/animals", 1, now(), "test1@asd.com", now(), "test1@asd.com");
INSERT INTO share_jpa.book(title, author, publisher, category_id, description, image_url, owner_id, created_at, created_by, modified_at, modified_by)
VALUES ("명품 C++ Programming", "황기태", "생능출판사 ", 3,"description for test book5", "https://placeimg.com/200/300/animals", 1, now(), "test1@asd.com", now(), "test1@asd.com");

INSERT INTO share_jpa.book(title, author, publisher, category_id, description, image_url, owner_id, created_at, created_by, modified_at, modified_by)
VALUES ("이것이 우분투 리눅스다", "우재남", "한빛미디어", 1,"description for test book1", "https://placeimg.com/200/300/animals", 1, now(), "test1@asd.com", now(), "test1@asd.com");
INSERT INTO share_jpa.book(title, author, publisher, category_id, description, image_url, owner_id, created_at, created_by, modified_at, modified_by)
VALUES ("리눅스 시스템 원리와 실제", "창병모", "생능출판사", 1,"description ", "https://placeimg.com/200/300/animals", 1, now(), "test1@asd.com", now(), "test1@asd.com");
INSERT INTO share_jpa.book(title, author, publisher, category_id, description, image_url, owner_id, created_at, created_by, modified_at, modified_by)
VALUES ("컴퓨터 네트워킹 : 하향식 접근(7판)", "James F. Kurose", "퍼스트북", 2,"description for test book3", "https://placeimg.com/200/300/animals", 1, now(), "test1@asd.com", now(), "test1@asd.com");
INSERT INTO share_jpa.book(title, author, publisher, category_id, description, image_url, owner_id, created_at, created_by, modified_at, modified_by)
VALUES ("두근두근 파이썬", "천인국", "생능출판사", 3,"description ", "https://placeimg.com/200/300/animals", 1, now(), "test1@asd.com", now(), "test1@asd.com");
INSERT INTO share_jpa.book(title, author, publisher, category_id, description, image_url, owner_id, created_at, created_by, modified_at, modified_by)
VALUES ("명품 C++ Programming", "황기태", "생능출판사 ", 3,"description for test book5", "https://placeimg.com/200/300/animals", 1, now(), "test1@asd.com", now(), "test1@asd.com");

INSERT INTO share_jpa.book(title, author, publisher, category_id, description, image_url, owner_id, created_at, created_by, modified_at, modified_by)
VALUES ("이것이 우분투 리눅스다", "우재남", "한빛미디어", 1,"description for test book1", "https://placeimg.com/200/300/animals", 1, now(), "test1@asd.com", now(), "test1@asd.com");
INSERT INTO share_jpa.book(title, author, publisher, category_id, description, image_url, owner_id, created_at, created_by, modified_at, modified_by)
VALUES ("리눅스 시스템 원리와 실제", "창병모", "생능출판사", 1,"description ", "https://placeimg.com/200/300/animals", 1, now(), "test1@asd.com", now(), "test1@asd.com");
INSERT INTO share_jpa.book(title, author, publisher, category_id, description, image_url, owner_id, created_at, created_by, modified_at, modified_by)
VALUES ("컴퓨터 네트워킹 : 하향식 접근(7판)", "James F. Kurose", "퍼스트북", 2,"description for test book3", "https://placeimg.com/200/300/animals", 1, now(), "test1@asd.com", now(), "test1@asd.com");
INSERT INTO share_jpa.book(title, author, publisher, category_id, description, image_url, owner_id, created_at, created_by, modified_at, modified_by)
VALUES ("두근두근 파이썬", "천인국", "생능출판사", 3,"description ", "https://placeimg.com/200/300/animals", 1, now(), "test1@asd.com", now(), "test1@asd.com");
INSERT INTO share_jpa.book(title, author, publisher, category_id, description, image_url, owner_id, created_at, created_by, modified_at, modified_by)
VALUES ("명품 C++ Programming", "황기태", "생능출판사 ", 3,"description for test book5", "https://placeimg.com/200/300/animals", 1, now(), "test1@asd.com", now(), "test1@asd.com");

INSERT INTO share_jpa.book(title, author, publisher, category_id, description, image_url, owner_id, created_at, created_by, modified_at, modified_by)
VALUES ("이것이 우분투 리눅스다", "우재남", "한빛미디어", 1,"description for test book1", "https://placeimg.com/200/300/animals", 1, now(), "test1@asd.com", now(), "test1@asd.com");
INSERT INTO share_jpa.book(title, author, publisher, category_id, description, image_url, owner_id, created_at, created_by, modified_at, modified_by)
VALUES ("리눅스 시스템 원리와 실제", "창병모", "생능출판사", 1,"description ", "https://placeimg.com/200/300/animals", 1, now(), "test1@asd.com", now(), "test1@asd.com");
INSERT INTO share_jpa.book(title, author, publisher, category_id, description, image_url, owner_id, created_at, created_by, modified_at, modified_by)
VALUES ("컴퓨터 네트워킹 : 하향식 접근(7판)", "James F. Kurose", "퍼스트북", 2,"description for test book3", "https://placeimg.com/200/300/animals", 1, now(), "test1@asd.com", now(), "test1@asd.com");
INSERT INTO share_jpa.book(title, author, publisher, category_id, description, image_url, owner_id, created_at, created_by, modified_at, modified_by)
VALUES ("두근두근 파이썬", "천인국", "생능출판사", 3,"description ", "https://placeimg.com/200/300/animals", 1, now(), "test1@asd.com", now(), "test1@asd.com");
INSERT INTO share_jpa.book(title, author, publisher, category_id, description, image_url, owner_id, created_at, created_by, modified_at, modified_by)
VALUES ("명품 C++ Programming", "황기태", "생능출판사 ", 3,"description for test book5", "https://placeimg.com/200/300/animals", 1, now(), "test1@asd.com", now(), "test1@asd.com");

INSERT INTO share_jpa.book(title, author, publisher, category_id, description, image_url, owner_id, created_at, created_by, modified_at, modified_by)
VALUES ("이것이 우분투 리눅스다", "우재남", "한빛미디어", 1,"description for test book1", "https://placeimg.com/200/300/animals", 1, now(), "test1@asd.com", now(), "test1@asd.com");
INSERT INTO share_jpa.book(title, author, publisher, category_id, description, image_url, owner_id, created_at, created_by, modified_at, modified_by)
VALUES ("리눅스 시스템 원리와 실제", "창병모", "생능출판사", 1,"description ", "https://placeimg.com/200/300/animals", 1, now(), "test1@asd.com", now(), "test1@asd.com");
INSERT INTO share_jpa.book(title, author, publisher, category_id, description, image_url, owner_id, created_at, created_by, modified_at, modified_by)
VALUES ("컴퓨터 네트워킹 : 하향식 접근(7판)", "James F. Kurose", "퍼스트북", 2,"description for test book3", "https://placeimg.com/200/300/animals", 1, now(), "test1@asd.com", now(), "test1@asd.com");
INSERT INTO share_jpa.book(title, author, publisher, category_id, description, image_url, owner_id, created_at, created_by, modified_at, modified_by)
VALUES ("두근두근 파이썬", "천인국", "생능출판사", 3,"description ", "https://placeimg.com/200/300/animals", 1, now(), "test1@asd.com", now(), "test1@asd.com");
INSERT INTO share_jpa.book(title, author, publisher, category_id, description, image_url, owner_id, created_at, created_by, modified_at, modified_by)
VALUES ("명품 C++ Programming", "황기태", "생능출판사 ", 3,"description for test book5", "https://placeimg.com/200/300/animals", 1, now(), "test1@asd.com", now(), "test1@asd.com");

INSERT INTO share_jpa.book(title, author, publisher, category_id, description, image_url, owner_id, created_at, created_by, modified_at, modified_by)
VALUES ("이것이 우분투 리눅스다", "우재남", "한빛미디어", 1,"description for test book1", "https://placeimg.com/200/300/animals", 1, now(), "test1@asd.com", now(), "test1@asd.com");
INSERT INTO share_jpa.book(title, author, publisher, category_id, description, image_url, owner_id, created_at, created_by, modified_at, modified_by)
VALUES ("리눅스 시스템 원리와 실제", "창병모", "생능출판사", 1,"description ", "https://placeimg.com/200/300/animals", 1, now(), "test1@asd.com", now(), "test1@asd.com");
INSERT INTO share_jpa.book(title, author, publisher, category_id, description, image_url, owner_id, created_at, created_by, modified_at, modified_by)
VALUES ("컴퓨터 네트워킹 : 하향식 접근(7판)", "James F. Kurose", "퍼스트북", 2,"description for test book3", "https://placeimg.com/200/300/animals", 1, now(), "test1@asd.com", now(), "test1@asd.com");
INSERT INTO share_jpa.book(title, author, publisher, category_id, description, image_url, owner_id, created_at, created_by, modified_at, modified_by)
VALUES ("두근두근 파이썬", "천인국", "생능출판사", 3,"description ", "https://placeimg.com/200/300/animals", 1, now(), "test1@asd.com", now(), "test1@asd.com");
INSERT INTO share_jpa.book(title, author, publisher, category_id, description, image_url, owner_id, created_at, created_by, modified_at, modified_by)
VALUES ("명품 C++ Programming", "황기태", "생능출판사 ", 3,"description for test book5", "https://placeimg.com/200/300/animals", 1, now(), "test1@asd.com", now(), "test1@asd.com");