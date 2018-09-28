create table tbl_reply(
rno INT not null AUTO_INCREMENT,
bno INT not null DEFAULT 0,
replytext VARCHAR(1000) not null,
replyer VARCHAR(100) not null,
regdate TIMESTAMP DEFAULT NOW(),
updatedate TIMESTAMP DEFAULT NOW(),
PRIMARY KEY (rno)
);

desc tbl_reply;
select * from tbl_reply;

-- 실무에서 처음 테이블 만들 때에는 foreign key를 잘 지정 안한다.
-- 테이블 생성 후 테스트 다 끝난 뒤에 따로 지정해줌.

ALTER TABLE 
	tbl_reply 
add CONSTRAINT 
	fk_board
FOREIGN KEY
	(bno) 
REFERENCES 
	tbl_board(bno);
	
INSERT INTO
	tbl_reply(bno, replytext, replyer )
VALUES
	(1055, '댓글', '댓글러');
	
INSERT INTO
	tbl_reply(replytext, replyer )
VALUES
	('댓글', '댓글러');
