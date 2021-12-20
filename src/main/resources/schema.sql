DROP TABLE IF EXISTS Enrollment;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Course;

CREATE TABLE User (
  id       BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(20)  NOT NULL UNIQUE,
  email    VARCHAR(100) NOT NULL
);

CREATE TABLE Course (
  id          BIGINT AUTO_INCREMENT PRIMARY KEY,
  code        VARCHAR(10) NOT NULL UNIQUE,
  name        VARCHAR(20) NOT NULL UNIQUE,
  description VARCHAR(500)
);

CREATE TABLE Enrollment (
  user_id    BIGINT   NOT NULL,
  course_id  BIGINT   NOT NULL,
  created_on DATETIME NOT NULL,

  FOREIGN KEY (user_id) REFERENCES User (id),
  FOREIGN KEY (course_id) REFERENCES Course (id),

  PRIMARY KEY (user_id, course_id)
);
