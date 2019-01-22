CREATE TABLE `account` (
  `username` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `modified_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ROLE_NAME` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `account_role` (
  `username` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `role_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`username`,`role_id`),
  KEY `fk_account_roles_idx` (`username`) /*!80000 INVISIBLE */,
  KEY `fk_role_roles_idx` (`role_id`) /*!80000 INVISIBLE */,
  CONSTRAINT `fk_account_roles` FOREIGN KEY (`username`) REFERENCES `account` (`username`),
  CONSTRAINT `fk_role_roles` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `book` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `author` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `main_image` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'AVAILABLE' COMMENT 'AVAILABLE\nWAIT_FOR_RESPONSE\nON_LOAN\nON_RESERVED\nLOAN_OVERDUE\nCANCELED',
  `modified_by` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `modified_at` timestamp NULL DEFAULT NULL,
  `created_by` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_modified_account_idx` (`modified_by`),
  KEY `fk_created_account_idx` (`created_by`),
  CONSTRAINT `fk_created_account` FOREIGN KEY (`created_by`) REFERENCES `account` (`username`),
  CONSTRAINT `fk_modified_account` FOREIGN KEY (`modified_by`) REFERENCES `account` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `book_image` (
  `book_id` bigint(20) unsigned NOT NULL,
  `image_url` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ord_no` int(11) DEFAULT '0',
  PRIMARY KEY (`book_id`,`image_url`),
  CONSTRAINT `fk_book_image` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `book_history` (
  `book_id` bigint(20) unsigned NOT NULL,
  `requested_status` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `started_at` timestamp NULL DEFAULT NULL,
  `ended_at` timestamp NULL DEFAULT NULL,
  `requested_user` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `requested_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `fk_book_history_idx` (`book_id`),
  KEY `fk_account_book_history_idx` (`requested_user`),
  CONSTRAINT `fk_account_book_history` FOREIGN KEY (`requested_user`) REFERENCES `account` (`username`),
  CONSTRAINT `fk_book_book_history` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci