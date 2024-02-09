CREATE TABLE field_dto (
    game_id VARCHAR(36) PRIMARY KEY,
    width INT NOT NULL,
    height INT NOT NULL,
    mines_count INT NOT NULL,
    completed BOOLEAN NOT NULL,
    field TEXT NOT NULL
);