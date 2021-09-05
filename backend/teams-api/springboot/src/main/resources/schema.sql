DROP TABLE IF EXISTS team;
DROP TABLE IF EXISTS teammate;
DROP TABLE IF EXISTS membership;

create sequence hibernate_sequence;

CREATE TABLE team (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

CREATE TABLE teammate (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

CREATE TABLE membership (
  teamid INT  NOT NULL,
  teammateid INT NOT NULL
);