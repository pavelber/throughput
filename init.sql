create table test (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name CHAR(30) NOT NULL);


DELIMITER $$
CREATE PROCEDURE InsertRand(IN NumRows INT)
    BEGIN
        DECLARE i INT;
        SET i = 1;
        START TRANSACTION;
        WHILE i <= NumRows DO
            INSERT INTO test (name) values (SUBSTR(MD5(RAND()),1,30));
            SET i = i + 1;
        END WHILE;
        COMMIT;
    END$$
DELIMITER ;

CALL InsertRand(10000000);