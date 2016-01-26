#2016-1-26

#add new table which called tbsystemproperties
CREATE TABLE tbsystemproperties(
 id INT(11) PRIMARY KEY AUTO_INCREMENT,
 proKey VARCHAR(500),
 proValue VARCHAR(500),
 createTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 modifyTime TIMESTAMP
);

#modify table tb3rdclients--add new column 'synCount'
ALTER TABLE tb3rdclients ADD synCount INT(11);

#modify table tbrestfullog--add new column 'synCount'
ALTER TABLE tbrestfullog ADD synCount INT(11);

#add delete trigger on tbsynuserjclass/tbsynuserbb


#add new table tb3rdsynuserdetaillog
#1)create table info
CREATE TABLE tb3rdsynuserdetaillog(
 id INT(11) PRIMARY KEY AUTO_INCREMENT,
 clientId VARCHAR(8),
 synTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 userName VARCHAR(50)
)


#2)create insert trigger info
DELIMITER $$

USE sso_center$$

DROP TRIGGER /*!50032 IF EXISTS */ tg_OnTb3rdSynUserDetailLogInsert$$

CREATE
    /*!50017 DEFINER = 'root'@'%' */
    TRIGGER tg_OnTb3rdSynUserDetailLogInsert AFTER INSERT ON tb3rdsynuserdetaillog
    FOR EACH ROW
BEGIN
  UPDATE tb3rdclients SET num = num + 1;
END;
$$
DELIMITER ;
