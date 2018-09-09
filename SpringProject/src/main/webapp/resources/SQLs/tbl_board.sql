create table tbl_board(
bno INT not null AUTO_INCREMENT,
title VARCHAR(300) not null,
content TEXT not null,
writer VARCHAR(50) not null,
regdate TIMESTAMP DEFAULT NOW(),
viewcnt INT DEFAULT 0,
PRIMARY KEY (bno)
);

select * from tbl_board;
drop table tbl_board;

insert into
	tbl_board(title, content, writer)
VALUES
	('제목1','내용1','저자1');

select 
		*
	from
		tbl_board
	where 
		bno=1;