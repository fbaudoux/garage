DROP TABLE IF EXISTS team;
DROP TABLE IF EXISTS teammate;
DROP TABLE IF EXISTS membership;

create sequence hibernate_sequence;

CREATE TABLE team (
  id INT default hibernate_sequence.nextval PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

CREATE TABLE teammate (
  id INT default hibernate_sequence.nextval   PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

CREATE TABLE membership (
  teamid INT  NOT NULL,
  teammateid INT NOT NULL
);

CREATE TABLE skill (
  id INT default hibernate_sequence.nextval PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

CREATE TABLE teammate_skill (
  skill_fk INT  NOT NULL,
  teammate_fk INT NOT NULL
);