CREATE TABLE topico (
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(255),
    mensaje TEXT,
    fechacreacion TIMESTAMP,
    curso VARCHAR(50),
    PRIMARY KEY (id)
);