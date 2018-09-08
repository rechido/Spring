create table tbl_member(
userid VARCHAR(50) not null,
userpw VARCHAR(50) not null,
username VARCHAR(50) not null,
email VARCHAR(50) not null,
regdate TIMESTAMP DEFAULT NOW(),
updatedate TIMESTAMP DEFAULT NOW(),
PRIMARY KEY (userid)
);

select * from tbl_member;