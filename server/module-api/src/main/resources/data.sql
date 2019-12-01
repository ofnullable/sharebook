INSERT INTO share_jpa.account(email, password, name, created_at, created_by, modified_at, modified_by)
VALUES
       ("test1@asd.com", "{noop}test", "test user1", now(), "test1@asd.com", now(), "test1@asd.com"),
       ("test2@asd.com", "{noop}test", "test user2", now(), "test2@asd.com", now(), "test2@asd.com"),
       ("test3@asd.com", "{noop}test", "test user3", now(), "test3@asd.com", now(), "test3@asd.com"),
       ("test4@asd.com", "{noop}test", "test user4", now(), "test4@asd.com", now(), "test4@asd.com"),
       ("test5@asd.com", "{noop}test", "test user5", now(), "test5@asd.com", now(), "test5@asd.com");

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

INSERT INTO share_jpa.book(title, author, publisher, category_id, description, status, image_uri, owner_id, current_borrower_id, created_at, created_by, modified_at, modified_by)
VALUES
       ("이것이 우분투 리눅스다", "우재남", "한빛미디어", 1,"description for test book1", 1, "/image/이것이-우분투-리눅스다.jpg", 2, 3, now(), "test2@asd.com", now(), "test2@asd.com"),
       ("리눅스 시스템 원리와 실제", "창병모", "생능출판사", 1,"description for test book2", 1, "/image/리눅스-시스템-원리와-실제.jpg", 2, 2, now(), "test2@asd.com", now(), "test2@asd.com"),
       ("컴퓨터 네트워킹 : 하향식 접근(7판)", "James F. Kurose", "퍼스트북", 2,"description for test book3", 1, "/image/컴퓨터-네트워킹-하향식-접근.jpg", 2, 2, now(), "test2@asd.com", now(), "test2@asd.com"),
       ("두근두근 파이썬", "천인국", "생능출판사", 3,"description for test book4", 1, "/image/두근두근-파이썬.jpg", 2, null, now(), "test2@asd.com", now(), "test2@asd.com"),
       ("명품 C++ Programming", "황기태", "생능출판사 ", 3,"description for test book5", 1, "/image/명품-C++-프로그래밍.jpg", 2, null, now(), "test2@asd.com", now(), "test2@asd.com"),

       ("이것이 우분투 리눅스다", "우재남", "한빛미디어", 1,"description for test book1", 1, "/image/이것이-우분투-리눅스다.jpg", 2, null, now(), "test2@asd.com", now(), "test2@asd.com"),
       ("리눅스 시스템 원리와 실제", "창병모", "생능출판사", 1,"description for test book2", 1, "/image/리눅스-시스템-원리와-실제.jpg", 2, null, now(), "test2@asd.com", now(), "test2@asd.com"),
       ("컴퓨터 네트워킹 : 하향식 접근(7판)", "James F. Kurose", "퍼스트북", 2,"description for test book3", 1, "/image/컴퓨터-네트워킹-하향식-접근.jpg", 2, null, now(), "test2@asd.com", now(), "test2@asd.com"),
       ("두근두근 파이썬", "천인국", "생능출판사", 3,"description for test book4", 1, "/image/두근두근-파이썬.jpg", 2, null, now(), "test2@asd.com", now(), "test2@asd.com"),
       ("명품 C++ Programming", "황기태", "생능출판사 ", 3,"description for test book5", 1, "/image/명품-C++-프로그래밍.jpg", 2, null, now(), "test2@asd.com", now(), "test2@asd.com"),

       ("이것이 우분투 리눅스다", "우재남", "한빛미디어", 1,"description for test book1", 1, "/image/이것이-우분투-리눅스다.jpg", 2, null, now(), "test2@asd.com", now(), "test2@asd.com"),
       ("리눅스 시스템 원리와 실제", "창병모", "생능출판사", 1,"description for test book2", 1, "/image/리눅스-시스템-원리와-실제.jpg", 2, null, now(), "test2@asd.com", now(), "test2@asd.com"),
       ("컴퓨터 네트워킹 : 하향식 접근(7판)", "James F. Kurose", "퍼스트북", 2,"description for test book3", 1, "/image/컴퓨터-네트워킹-하향식-접근.jpg", 2, null, now(), "test2@asd.com", now(), "test2@asd.com"),
       ("두근두근 파이썬", "천인국", "생능출판사", 3,"description for test book4", 1, "/image/두근두근-파이썬.jpg", 2, null, now(), "test2@asd.com", now(), "test2@asd.com"),
       ("명품 C++ Programming", "황기태", "생능출판사 ", 3,"description for test book5", 1, "/image/명품-C++-프로그래밍.jpg", 2, null, now(), "test2@asd.com", now(), "test2@asd.com"),

       ("이것이 우분투 리눅스다", "우재남", "한빛미디어", 1,"description for test book1", 1, "/image/이것이-우분투-리눅스다.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("리눅스 시스템 원리와 실제", "창병모", "생능출판사", 1,"description for test book2", 1, "/image/리눅스-시스템-원리와-실제.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("컴퓨터 네트워킹 : 하향식 접근(7판)", "James F. Kurose", "퍼스트북", 2,"description for test book3", 1, "/image/컴퓨터-네트워킹-하향식-접근.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("두근두근 파이썬", "천인국", "생능출판사", 3,"description for test book4", 1, "/image/두근두근-파이썬.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("명품 C++ Programming", "황기태", "생능출판사 ", 3,"description for test book5", 1, "/image/명품-C++-프로그래밍.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),

       ("이것이 우분투 리눅스다", "우재남", "한빛미디어", 1,"description for test book1", 1, "/image/이것이-우분투-리눅스다.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("리눅스 시스템 원리와 실제", "창병모", "생능출판사", 1,"description for test book2", 1, "/image/리눅스-시스템-원리와-실제.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("컴퓨터 네트워킹 : 하향식 접근(7판)", "James F. Kurose", "퍼스트북", 2,"description for test book3", 1, "/image/컴퓨터-네트워킹-하향식-접근.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("두근두근 파이썬", "천인국", "생능출판사", 3,"description for test book4", 1, "/image/두근두근-파이썬.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("명품 C++ Programming", "황기태", "생능출판사 ", 3,"description for test book5", 1, "/image/명품-C++-프로그래밍.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),

       ("이것이 우분투 리눅스다", "우재남", "한빛미디어", 1,"description for test book1", 1, "/image/이것이-우분투-리눅스다.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("리눅스 시스템 원리와 실제", "창병모", "생능출판사", 1,"description for test book2", 1, "/image/리눅스-시스템-원리와-실제.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("컴퓨터 네트워킹 : 하향식 접근(7판)", "James F. Kurose", "퍼스트북", 2,"description for test book3", 1, "/image/컴퓨터-네트워킹-하향식-접근.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("두근두근 파이썬", "천인국", "생능출판사", 3,"description for test book4", 1, "/image/두근두근-파이썬.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("명품 C++ Programming", "황기태", "생능출판사 ", 3,"description for test book5", 1, "/image/명품-C++-프로그래밍.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),

       ("이것이 우분투 리눅스다", "우재남", "한빛미디어", 1,"description for test book1", 1, "/image/이것이-우분투-리눅스다.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("리눅스 시스템 원리와 실제", "창병모", "생능출판사", 1,"description for test book2", 1, "/image/리눅스-시스템-원리와-실제.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("컴퓨터 네트워킹 : 하향식 접근(7판)", "James F. Kurose", "퍼스트북", 2,"description for test book3", 1, "/image/컴퓨터-네트워킹-하향식-접근.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("두근두근 파이썬", "천인국", "생능출판사", 3,"description for test book4", 1, "/image/두근두근-파이썬.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("명품 C++ Programming", "황기태", "생능출판사 ", 3,"description for test book5", 1, "/image/명품-C++-프로그래밍.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),

       ("이것이 우분투 리눅스다", "우재남", "한빛미디어", 1,"description for test book1", 1, "/image/이것이-우분투-리눅스다.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("리눅스 시스템 원리와 실제", "창병모", "생능출판사", 1,"description for test book2", 1, "/image/리눅스-시스템-원리와-실제.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("컴퓨터 네트워킹 : 하향식 접근(7판)", "James F. Kurose", "퍼스트북", 2,"description for test book3", 1, "/image/컴퓨터-네트워킹-하향식-접근.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("두근두근 파이썬", "천인국", "생능출판사", 3,"description for test book4", 1, "/image/두근두근-파이썬.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("명품 C++ Programming", "황기태", "생능출판사 ", 3,"description for test book5", 1, "/image/명품-C++-프로그래밍.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),

       ("이것이 우분투 리눅스다", "우재남", "한빛미디어", 1,"description for test book1", 1, "/image/이것이-우분투-리눅스다.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("리눅스 시스템 원리와 실제", "창병모", "생능출판사", 1,"description for test book2", 1, "/image/리눅스-시스템-원리와-실제.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("컴퓨터 네트워킹 : 하향식 접근(7판)", "James F. Kurose", "퍼스트북", 2,"description for test book3", 1, "/image/컴퓨터-네트워킹-하향식-접근.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("두근두근 파이썬", "천인국", "생능출판사", 3,"description for test book4", 1, "/image/두근두근-파이썬.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("명품 C++ Programming", "황기태", "생능출판사 ", 3,"description for test book5", 1, "/image/명품-C++-프로그래밍.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),

       ("이것이 우분투 리눅스다", "우재남", "한빛미디어", 1,"description for test book1", 1, "/image/이것이-우분투-리눅스다.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("리눅스 시스템 원리와 실제", "창병모", "생능출판사", 1,"description for test book2", 1, "/image/리눅스-시스템-원리와-실제.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("컴퓨터 네트워킹 : 하향식 접근(7판)", "James F. Kurose", "퍼스트북", 2,"description for test book3", 1, "/image/컴퓨터-네트워킹-하향식-접근.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("두근두근 파이썬", "천인국", "생능출판사", 3,"description for test book4", 1, "/image/두근두근-파이썬.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("명품 C++ Programming", "황기태", "생능출판사 ", 3,"description for test book5", 1, "/image/명품-C++-프로그래밍.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),

       ("이것이 우분투 리눅스다", "우재남", "한빛미디어", 1,"description for test book1", 1, "/image/이것이-우분투-리눅스다.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("리눅스 시스템 원리와 실제", "창병모", "생능출판사", 1,"description for test book2", 1, "/image/리눅스-시스템-원리와-실제.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("컴퓨터 네트워킹 : 하향식 접근(7판)", "James F. Kurose", "퍼스트북", 2,"description for test book3", 1, "/image/컴퓨터-네트워킹-하향식-접근.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("두근두근 파이썬", "천인국", "생능출판사", 3,"description for test book4", 1, "/image/두근두근-파이썬.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("명품 C++ Programming", "황기태", "생능출판사 ", 3,"description for test book5", 1, "/image/명품-C++-프로그래밍.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),

       ("이것이 우분투 리눅스다", "우재남", "한빛미디어", 1,"description for test book1", 1, "/image/이것이-우분투-리눅스다.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("리눅스 시스템 원리와 실제", "창병모", "생능출판사", 1,"description for test book2", 1, "/image/리눅스-시스템-원리와-실제.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("컴퓨터 네트워킹 : 하향식 접근(7판)", "James F. Kurose", "퍼스트북", 2,"description for test book3", 1, "/image/컴퓨터-네트워킹-하향식-접근.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("두근두근 파이썬", "천인국", "생능출판사", 3,"description for test book4", 1, "/image/두근두근-파이썬.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("명품 C++ Programming", "황기태", "생능출판사 ", 3,"description for test book5", 2, "/image/명품-C++-프로그래밍.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),

       ("이것이 우분투 리눅스다", "우재남", "한빛미디어", 1,"description for test book1", 1, "/image/이것이-우분투-리눅스다.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("리눅스 시스템 원리와 실제", "창병모", "생능출판사", 1,"description for test book2", 1, "/image/리눅스-시스템-원리와-실제.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("컴퓨터 네트워킹 : 하향식 접근(7판)", "James F. Kurose", "퍼스트북", 2,"description for test book3", 1, "/image/컴퓨터-네트워킹-하향식-접근.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("두근두근 파이썬", "천인국", "생능출판사", 3,"description for test book4", 1, "/image/두근두근-파이썬.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com"),
       ("명품 C++ Programming", "황기태", "생능출판사 ", 3,"description for test book5", 2, "/image/명품-C++-프로그래밍.jpg", 1, null, now(), "test1@asd.com", now(), "test1@asd.com");

INSERT INTO share_jpa.lending(book_id, borrower_id, current_status, started_at, ended_at)
VALUES
       (65, 2, "RETURNED", now(), now()),
       (65, 3, "CANCELED", null, now()),
       (65, 3, "ACCEPTED", now(), null),
       (64, 2, "ACCEPTED", now(), null),
       (63, 2, "RETURNED", now(), now()),
       (62, 3, "REQUESTED", null, null),
       (61, 2, "REQUESTED", null, null),
       (60, 2, "REQUESTED", null, null);

INSERT INTO share_jpa.lending_history(lending_id, status, created_by, created_at, modified_by, modified_at)
VALUES
       (1, "REQUESTED", "test2@asd.com", now(), "test2@asd.com", now()),
       (1, "ACCEPTED", "test1@asd.com", now(), "test1@asd.com", now()),
       (1, "RETURNED", "test1@asd.com", now(), "test1@asd.com", now()),
       (2, "REQUESTED", "test3@asd.com", now(), "test3@asd.com", now()),
       (2, "CANCELED", "test3@asd.com", now(), "test3@asd.com", now()),
       (3, "REQUESTED", "test3@asd.com", now(), "test3@asd.com", now()),
       (3, "ACCEPTED", "test1@asd.com", now(), "test1@asd.com", now()),
       (4, "REQUESTED", "test2@asd.com", now(), "test2@asd.com", now()),
       (4, "ACCEPTED", "test1@asd.com", now(), "test1@asd.com", now()),
       (5, "REQUESTED", "test2@asd.com", now(), "test2@asd.com", now()),
       (5, "ACCEPTED", "test2@asd.com", now(), "test2@asd.com", now()),
       (5, "RETURNED", "test2@asd.com", now(), "test2@asd.com", now()),
       (6, "REQUESTED", "test3@asd.com", now(), "test3@asd.com", now()),
       (7, "REQUESTED", "test2@asd.com", now(), "test2@asd.com", now()),
       (8, "REQUESTED", "test2@asd.com", now(), "test2@asd.com", now());

INSERT INTO share_jpa.review(book_id, account_id, name, contents, score, created_by, created_at, modified_by, modified_at)
VALUES
       (65, 2, "test2 user", "나는 휴머니즘. 평등주의자라고 강력하게 생각하고 있었는데 이 책을 읽으며 내가 얼마나 편협한 생각을 하고 있었는지 다시 한번 생각하게 되네요. 쉽지 않은 내용인데 쉽게 잘 풀어주셔서 술술 읽으며 많은 밑줄을 긋고 책갈피를 하며 봤습니다", 5, "test2@asd.com", now(), "test2@asd.com", now()),
       (65, 3, "test3 user", "권력과 차별을 이토록 섬세하게 풀어내주시다니요~ 개인적으로 역대 밑줄 친 부분이 가장 많은 책입니다", 3, "test3@asd.com", now(), "test3@asd.com", now());
