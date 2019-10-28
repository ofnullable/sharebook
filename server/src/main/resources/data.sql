INSERT INTO share_jpa.account(email, password, name, created_at, created_by, modified_by, modified_at)
VALUES
       ("test1@asd.com", "{noop}test", "test user1", now(), "test user1", "test user1", now()),
       ("test2@asd.com", "{noop}test", "test user2", now(), "test user2", "test user2", now()),
       ("test3@asd.com", "{noop}test", "test user3", now(), "test user3", "test user3", now()),
       ("test4@asd.com", "{noop}test", "test user4", now(), "test user4", "test user4", now()),
       ("test5@asd.com", "{noop}test", "test user5", now(), "test user5", "test user5", now());

INSERT INTO share_jpa.role(name, account_id)
VALUES
       ("BASIC", 1),
       ("ADMIN", 1),
       ("BASIC", 2),
       ("BASIC", 3),
       ("BASIC", 4),
       ("BASIC", 5);

INSERT INTO share_jpa.category(name, display)
VALUES
       ("운영체제", 1),
       ("네트워크", 1),
       ("프로그래밍 언어", 1);

INSERT INTO share_jpa.book(title, author, publisher, category_id, description, status, image_url, owner_id, current_renter_id, created_at, created_by, modified_at, modified_by)
VALUES
       ("이것이 우분투 리눅스다", "우재남", "한빛미디어", 1,"description for test book1", 2, "https://placeimg.com/200/300/animals", 1, 3, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("리눅스 시스템 원리와 실제", "창병모", "생능출판사", 1,"description for test book2", 2, "https://placeimg.com/200/300/animals", 1, 2, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("컴퓨터 네트워킹 : 하향식 접근(7판)", "James F. Kurose", "퍼스트북", 2,"description for test book3", 2, "https://placeimg.com/200/300/animals", 1, 2, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("두근두근 파이썬", "천인국", "생능출판사", 3,"description for test book4", 1, "https://placeimg.com/200/300/animals", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("명품 C++ Programming", "황기태", "생능출판사 ", 3,"description for test book5", 1, "https://placeimg.com/200/300/animals", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),

       ("이것이 우분투 리눅스다", "우재남", "한빛미디어", 1,"description for test book1", 1, "https://placeimg.com/200/300/animals", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("리눅스 시스템 원리와 실제", "창병모", "생능출판사", 1,"description for test book2", 1, "https://placeimg.com/200/300/animals", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("컴퓨터 네트워킹 : 하향식 접근(7판)", "James F. Kurose", "퍼스트북", 2,"description for test book3", 1, "https://placeimg.com/200/300/animals", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("두근두근 파이썬", "천인국", "생능출판사", 3,"description for test book4", 1, "https://placeimg.com/200/300/animals", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("명품 C++ Programming", "황기태", "생능출판사 ", 3,"description for test book5", 1, "https://placeimg.com/200/300/animals", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),

       ("이것이 우분투 리눅스다", "우재남", "한빛미디어", 1,"description for test book1", 1, "https://placeimg.com/200/300/animals", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("리눅스 시스템 원리와 실제", "창병모", "생능출판사", 1,"description for test book2", 1, "https://placeimg.com/200/300/animals", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("컴퓨터 네트워킹 : 하향식 접근(7판)", "James F. Kurose", "퍼스트북", 2,"description for test book3", 1, "https://placeimg.com/200/300/animals", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("두근두근 파이썬", "천인국", "생능출판사", 3,"description for test book4", 1, "https://placeimg.com/200/300/animals", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("명품 C++ Programming", "황기태", "생능출판사 ", 3,"description for test book5", 1, "https://placeimg.com/200/300/animals", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),

       ("이것이 우분투 리눅스다", "우재남", "한빛미디어", 1,"description for test book1", 1, "https://placeimg.com/200/300/animals", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("리눅스 시스템 원리와 실제", "창병모", "생능출판사", 1,"description for test book2", 1, "https://placeimg.com/200/300/animals", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("컴퓨터 네트워킹 : 하향식 접근(7판)", "James F. Kurose", "퍼스트북", 2,"description for test book3", 1, "https://placeimg.com/200/300/animals", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("두근두근 파이썬", "천인국", "생능출판사", 3,"description for test book4", 1, "https://placeimg.com/200/300/animals", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("명품 C++ Programming", "황기태", "생능출판사 ", 3,"description for test book5", 1, "https://placeimg.com/200/300/animals", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),

       ("이것이 우분투 리눅스다", "우재남", "한빛미디어", 1,"description for test book1", 1, "https://placeimg.com/200/300/animals", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("리눅스 시스템 원리와 실제", "창병모", "생능출판사", 1,"description for test book2", 1, "https://placeimg.com/200/300/animals", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("컴퓨터 네트워킹 : 하향식 접근(7판)", "James F. Kurose", "퍼스트북", 2,"description for test book3", 1, "https://placeimg.com/200/300/animals", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("두근두근 파이썬", "천인국", "생능출판사", 3,"description for test book4", 1, "https://placeimg.com/200/300/animals", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("명품 C++ Programming", "황기태", "생능출판사 ", 3,"description for test book5", 1, "https://placeimg.com/200/300/animals", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),

       ("이것이 우분투 리눅스다", "우재남", "한빛미디어", 1,"description for test book1", 1, "https://placeimg.com/200/300/animals", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("리눅스 시스템 원리와 실제", "창병모", "생능출판사", 1,"description for test book2", 1, "https://placeimg.com/200/300/animals", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("컴퓨터 네트워킹 : 하향식 접근(7판)", "James F. Kurose", "퍼스트북", 2,"description for test book3", 1, "https://placeimg.com/200/300/animals", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("두근두근 파이썬", "천인국", "생능출판사", 3,"description for test book4", 1, "https://placeimg.com/200/300/animals", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("명품 C++ Programming", "황기태", "생능출판사 ", 3,"description for test book5", 1, "https://placeimg.com/200/300/animals", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),

       ("이것이 우분투 리눅스다", "우재남", "한빛미디어", 1,"description for test book1", 1, "https://placeimg.com/200/300/animals", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("리눅스 시스템 원리와 실제", "창병모", "생능출판사", 1,"description for test book2", 1, "https://placeimg.com/200/300/animals", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("컴퓨터 네트워킹 : 하향식 접근(7판)", "James F. Kurose", "퍼스트북", 2,"description for test book3", 1, "https://placeimg.com/200/300/animals", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("두근두근 파이썬", "천인국", "생능출판사", 3,"description for test book4", 1, "https://placeimg.com/200/300/animals", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("명품 C++ Programming", "황기태", "생능출판사 ", 3,"description for test book5", 1, "https://placeimg.com/200/300/animals", 1, null, now(), "test1@asd.com", now(), "test1@asd.com");

INSERT INTO share_jpa.lending(borrower_id, book_id, current_status, started_at, ended_at)
VALUES
       (2, 1, "RETURNED", now(), null),
       (3, 1, "REQUESTED", now(), null),
       (2, 2, "ACCEPTED", null, null),
       (2, 3, "REQUESTED", null, null);

INSERT INTO share_jpa.lending_history(lending_id, status, created_by, created_at, modified_by, modified_at)
VALUES
       (1, "REQUESTED", 2, now(), 2, now()),
       (1, "ACCEPTED", 1, now(), 1, now()),
       (1, "RETURNED", 1, now(), 1, now()),
       (2, "REQUESTED", 3, now(), 3, now()),
       (3, "REQUESTED", 2, now(), 2, now()),
       (3, "ACCEPTED", 1, now(), 1, now()),
       (4, "REQUESTED", 2, now(), 2, now());