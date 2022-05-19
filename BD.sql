Drop Database if exists Scores;
CREATE DATABASE Scores;
use scores;
CREATE TABLE SCORES (
                COINS INT,
                KILLS INT, 
                DIFICULTY INT,
                ENDGAMEHP INT,
                DATE_PLAY DATE,
                POINTS INT
);

INSERT INTO SCORES VALUES(5,2,6,8,'2020-12-21',5);
SELECT*FROM Scores;