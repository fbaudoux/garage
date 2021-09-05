INSERT INTO team (name) VALUES ('Marvel');
INSERT INTO teammate (name) VALUES ('Cpt America');
INSERT INTO teammate (name) VALUES ('Hulk');
INSERT INTO teammate (name) VALUES ('IronMan');
INSERT INTO teammate (name) VALUES ('AntMan');


INSERT INTO team (name) VALUES ('StarWars');
INSERT INTO teammate (name) VALUES ('Luke SkyWalker');
INSERT INTO teammate (name) VALUES ('Princess Leia');
INSERT INTO teammate (name) VALUES ('Yoda');
INSERT INTO teammate (name) VALUES ('Han Solo');


INSERT INTO membership(teamid,teammateid) select t.id,ta.id from team t ,teammate ta where t.name ='Marvel' and ta.name  = 'Cpt America';
INSERT INTO membership(teamid,teammateid) select t.id,ta.id from team t ,teammate ta where t.name ='Marvel' and ta.name  = 'Hulk';
INSERT INTO membership(teamid,teammateid) select t.id,ta.id from team t ,teammate ta where t.name ='Marvel' and ta.name  = 'IronMan';
INSERT INTO membership(teamid,teammateid) select t.id,ta.id from team t ,teammate ta where t.name ='Marvel' and ta.name  = 'AntMan';


INSERT INTO membership(teamid,teammateid) select t.id,ta.id from team t ,teammate ta where t.name ='StarWars' and ta.name  = 'Luke SkyWalker';
INSERT INTO membership(teamid,teammateid) select t.id,ta.id from team t ,teammate ta where t.name ='StarWars' and ta.name  = 'Princess Leia';
INSERT INTO membership(teamid,teammateid) select t.id,ta.id from team t ,teammate ta where t.name ='StarWars' and ta.name  = 'Yoda';
INSERT INTO membership(teamid,teammateid) select t.id,ta.id from team t ,teammate ta where t.name ='StarWars' and ta.name  = 'Han Solo';

INSERT INTO skill (name) VALUES ('use the force');
INSERT INTO skill (name) VALUES ('fight with laser saber');
INSERT INTO skill (name) VALUES ('drive a x-wing');


insert into teammate_skill(skill_fk,teammate_fk) select s.id,ta.id from skill s ,teammate ta where s.name ='use the force' and ta.name  = 'Luke SkyWalker';
insert into teammate_skill(skill_fk,teammate_fk) select s.id,ta.id from skill s ,teammate ta where s.name ='fight with laser saber' and ta.name  = 'Luke SkyWalker';
insert into teammate_skill(skill_fk,teammate_fk) select s.id,ta.id from skill s ,teammate ta where s.name ='drive a x-wing' and ta.name  = 'Luke SkyWalker';

