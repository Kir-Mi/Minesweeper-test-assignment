CREATE TABLE IF NOT EXISTS field_dto (
    game_id VARCHAR(36) PRIMARY KEY,
    width INT NOT NULL,
    height INT NOT NULL,
    mines_count INT NOT NULL,
    completed BOOLEAN NOT NULL,
    field TEXT(10000) NOT NULL
);

CREATE TABLE IF NOT EXISTS field_data (
    game_id VARCHAR(36) PRIMARY KEY,
    field TEXT(10000) NOT NULL,
    FOREIGN KEY (game_id) REFERENCES field_dto(game_id)
);