desc tbl_board;
select * from tbl_board where title='test999';
delete from tbl_board;
insert into 
	tbl_board(title, content, writer) 
VALUES
	('타이틀','내용','저자');
	

DROP PROCEDURE IF EXISTS while_test;
CREATE PROCEDURE while_test()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE (i < 1000) DO     
	    insert into 
			tbl_board(title, content, writer) 
		VALUES
			(CONCAT('test',i),CONCAT('test',i),CONCAT('test',i,'@test.com'));
	    SET i = i + 1;
    END WHILE;
END;

call while_test;
 

