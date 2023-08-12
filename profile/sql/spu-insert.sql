DELIMITER //
CREATE PROCEDURE InsertSPUData()
BEGIN
    DECLARE counter INT DEFAULT 1;
    DECLARE titleValue VARCHAR(50);

    WHILE counter <= 100
        DO
            SET titleValue = CONCAT('spu', counter);
            INSERT INTO spu (title, category_id, online, price, is_test)
            VALUES (titleValue,
                    counter % 10,
                    1,
                    counter,
                    0);

            SET counter = counter + 1;
        END WHILE;
END //
DELIMITER ;

CALL InsertSPUData();
