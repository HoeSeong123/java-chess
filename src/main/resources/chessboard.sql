USE chess;

CREATE TABLE board
(
    id          BIGINT   NOT NULL AUTO_INCREMENT,
    game_status CHAR(10) NOT NULL,
    position    CHAR(2)  NOT NULL,
    piece_id    TINYINT  NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE piece
(
    id TINYINT NOT NULL AUTO_INCREMENT,
    type char(6) NOT NULL,
    team char(5) NOT NULL,
    PRIMARY KEY (id)
);

