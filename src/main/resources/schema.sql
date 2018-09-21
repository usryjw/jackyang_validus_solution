drop table if exists artist;
create table artist(
 id number(20) not null auto_increment,
 name varchar2(200) not null,
 created date,
 last_modified date,
 primary key (id) 
);

drop table if exists album;
create table album(
 id number(20) not null auto_increment,
 name varchar2(200) not null,
 year_released number(4),
 created date,
 last_modified date,
 primary key (id)
);

drop table if exists artist_albums;
create table artist_albums(
 artist_id number(20) not null,
 albums_id number(20) not null,
 primary key (artist_id, albums_id),
FOREIGN KEY (albums_id) REFERENCES album (id),
FOREIGN KEY (artist_id) REFERENCES artist (id)
 
);

drop table if exists song;
create table song(
 id number(20) not null auto_increment,
 album_id number(20) not null,
 track number(4),
 name varchar2(200) not null,
 created date,
 last_modified date,
 primary key (id), 
 FOREIGN KEY (album_id) REFERENCES album (id)
);
