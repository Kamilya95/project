create table Users (
Id Serial  PRIMARY KEY not null ,
login text not null,
password text not null,	
role text,
status text

);
