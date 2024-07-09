CREATE TABLE usuario (
    id BIGINT NOT NULL AUTO_INCREMENT,
    login VARCHAR(50) NOT NULL,
    clave VARCHAR(100) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_login (login)
);