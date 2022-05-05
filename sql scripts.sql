cReAte tABle user_role (
	role_id  serial primary key,
	role_name varchar(100) not null

);

create table  users (
	user_id  serial primary key,
	role_id integer not null references user_role,
	full_name varchar(200) not null,
	email varchar(30) unique not null,
	password varchar(30) not null,
	phone varchar(15) 
	
);

cReAte tABle genre (
	genre_id  serial primary key,
	genre_name varchar(100) not null

);

cReAte tABle status (
	status_id  serial primary key,
	status_name varchar(100) not null

);

create table length_type (
length_id serial primary key,
length_name varchar(100)not null
);


create table  story_pitch (
	pitch_id  serial primary key,
	user_id integer not null references users,
	genre_id integer not null references genre,
	status_id integer not null references status,
	length_id integer  not null references length_type,
	tentative_title varchar(100) not null,
	comp_date date not null,
	description varchar(100) not null,
	blurb varchar(1200) not null
	
);

INSERT INTO user_role  (role_id,role_name)
values (default, 'Author'),
(default,'Editor'),
(default,'Senior Editor');

insert into users  (user_id, role_id, full_name , email, password, phone ) values (default , '3', 'Samuel Gebru', 'sami@example.com', '1234', '388-317-5561');
insert into users  (user_id, role_id, full_name , email, password, phone ) values (default, '2', 'John doe', 'john@example.com', '1234', '678-324-8477');
insert into users  (user_id, role_id, full_name , email, password, phone ) values (default, '2', 'Simon Alenov', 'salenov2@archive.org', '1234', '759-498-1828');
insert into users  (user_id, role_id, full_name , email, password, phone ) values (default, '1', 'Amble Bogart', 'amble@prnewswire.com', '1234', '216-852-7945');
insert into users  (user_id, role_id, full_name , email, password, phone ) values (default, '1', 'Ernesto Potticary', 'epotticary4@disqus.com', 'oYnZ2mB', '984-173-7511');
insert into users  (user_id, role_id, full_name , email, password, phone ) values (default, '1', 'Alla Burnall', 'aburnall5@psu.edu', 'zxdQNufF', '647-950-2917');
insert into users  (user_id, role_id, full_name , email, password, phone ) values (default, '1', 'Allie Barday', 'abarday6@simplemachines.org', '0SrDB2Ip6tdf', '851-364-6890');
insert into users  (user_id, role_id, full_name , email, password, phone ) values (default, '1', 'Alaric Stainton - Skinn', 'astainton7@craigslist.org', 'x5Thnfez', '654-760-7373');
insert into users  (user_id, role_id, full_name , email, password, phone ) values (default, '1', 'Tim Pietrowski', 'tpietrowski8@ning.com', 'xtmvi0T41d', '892-342-0176');
insert into users  (user_id, role_id, full_name , email, password, phone ) values (default, '1', 'Kayle Hosby', 'khosby9@hhs.gov', 'J70Q3QiHU6F3', '304-793-0096');


INSERT INTO genre  (genre_id,genre_name) values

(default,'Detective'),  
(default,'Fantasy'), 
(default,'Historical Fiction'), 
(default,'Horror'), 
(default,'Literary Fiction'),
(default,'Other');

insert into status (status_id, status_name) values 
(default, 'Pending Editor Approval'),
(default,'Editor-Approved'),
(default,'Pending Senior Editor Approval'),
(default,'Approved'),
(default,'Rejected');

insert into length_type (length_id, length_name) values 
 (default,'Flash Fiction'),
(default,'Short Story'),
(default,'Novella'),
(default,'Novel');


INSERT INTO story_pitch (pitch_id,user_id,genre_id,status_id,tentative_title,comp_date,length_id,description,blurb)
VALUES
    (default, '1', '6', '4', 'A Touch of Cinaomon', '8/11/2022', '2', 'Your consciousness unfolds into mortal images', '"The childhood friend claims the young guy has been chosen to compete against others of other regions in a competition. The winner will gain not only the honor and glory of winning, but will also never need for anything for the rest of their life.  "'),
    (default, '2', '4', '3', 'Knight Of Yesterday', '8/12/2022', '1', 'Interdependence drives an abundance of creativity', 'The strange woman claims the elderly woman is chosen to be one of the few lucky people who get to leave Earth on the enormous spaceship planned to leave before the imminent end of Earth.'),
    (default, '3', '3', '1', 'Snakes Of Heaven', '9/2/2022', '3', 'A young man with a dark past', 'But what if this strange woman is not speaking the truth. Or what if this is all far bigger than what has been told. How could an ordinary young guy figure out the truth in all of this. No turning back now though.'),
    (default, '3', '1', '2', 'Friends Of The Void', '8/24/2022', '2', 'When a young woman accidentally discovers that she s a princess', 'The hectic life of a young girl is going in a different direction as a strange boy enters her life.'),
    (default, '4', '4', '1', 'Vultures And Serpents', '8/25/2022', '1', 'It is now 30 years since the disappearance of a family in rural New Zealand', 'But what if this stranger is wrong about all this. Or what if the complete opposite is true. How could an ordinary young guy be this important to the situation. We re about to find out.'),
    (default, '5', '5', '5', 'Annihilation Of Ice', '4/16/2022', '3', 'A small town in the western USA with a rich economy', 'The laid-back life of a boy might be changing forever as a new friend enters his life.'),
    (default, '6', '3', '1', 'Robot Scheme', '9/27/2022', '2', 'A man named Sudeep falls in love with his wife.', 'But what if this strange boy is just a crazy person. Or what if all this is an elaborate trap. How could an ordinary young guy be this important to the situation. Time will tell.'),
    (default, '4', '2', '2', 'Parody Can Cook', '6/28/2022', '4', 'To get through the chaotic night, he falls in love with a beautiful dancer', 'The quiet life of a woman has a chance to change, for better or worse, as a stranger enters her life.'),
    (default, '7', '6', '5', 'Fears Of Science', '5/29/2023', '1', 'In the small village of Gobi, the inhabitants of the area are divided.', 'But what if this stranger is not speaking the truth. Or what if everything told is completely true. How could an ordinary elderly man come out of this situation in a better way. Time to find out.');

 CREATE VIEW all_stories 
AS  
   
SELECT  story_pitch.pitch_id ,users.full_name, genre.genre_name,length_type.length_name, story_pitch.tentative_title,
story_pitch.comp_date, story_pitch.description, story_pitch.blurb,status.status_name  
FROM story_pitch 
 full JOIN users
ON story_pitch.user_id = users.user_id 
full JOIN genre 
ON story_pitch.genre_id = genre.genre_id 
inner JOIN status 
ON story_pitch.status_id= status.status_id 
full JOIN length_type 
on story_pitch.length_id= length_type.length_id;  


select * from all_stories ;