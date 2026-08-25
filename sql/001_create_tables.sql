-- =========================================================
-- BANCO DE DADOS
-- =========================================================

CREATE DATABASE IF NOT EXISTS ecourty
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE ecourty;


-- =========================================================
-- TABELA: usuario
-- =========================================================

CREATE TABLE usuario (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL,
    senha VARCHAR(255) NOT NULL,

    PRIMARY KEY (id),
    UNIQUE KEY uk_usuario_email (email)
) ENGINE=InnoDB;


-- =========================================================
-- TABELA: cliente
-- =========================================================

CREATE TABLE cliente (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(150),

    PRIMARY KEY (id),
    UNIQUE KEY uk_cliente_cpf (cpf),
    UNIQUE KEY uk_cliente_email (email)
) ENGINE=InnoDB;


-- =========================================================
-- TABELA: tipo_quadRa
-- =========================================================

CREATE TABLE tipo_quadra (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    tipo VARCHAR(100) NOT NULL,

    PRIMARY KEY (id),
    UNIQUE KEY uk_tipo_quadra_tipo (tipo)
) ENGINE=InnoDB;


-- =========================================================
-- TABELA: quadra
-- =========================================================

CREATE TABLE quadra (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    tipoquadra_id BIGINT UNSIGNED NOT NULL,
    valor_hora DECIMAL(10,2) NOT NULL,

    PRIMARY KEY (id),

    CONSTRAINT fk_quadra_tipo
        FOREIGN KEY (tipoquadra_id)
        REFERENCES tipo_quadra (id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE=InnoDB;


-- =========================================================
-- TABELA: reserva
-- =========================================================

CREATE TABLE reserva (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    cliente_id BIGINT UNSIGNED NOT NULL,
    quadra_id BIGINT UNSIGNED NOT NULL,
    data_inicio DATETIME NOT NULL,
    data_final DATETIME NOT NULL,
    valor DECIMAL(10,2) NOT NULL,

    PRIMARY KEY (id),

    CONSTRAINT fk_reserva_cliente
        FOREIGN KEY (cliente_id)
        REFERENCES cliente (id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,

    CONSTRAINT fk_reserva_quadra
        FOREIGN KEY (quadra_id)
        REFERENCES quadra (id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,

    CONSTRAINT chk_reserva_datas
        CHECK (data_final > data_inicio),

    CONSTRAINT chk_reserva_valor
        CHECK (valor >= 0)
) ENGINE=InnoDB;


-- =========================================================
-- TABELA: imagens
-- =========================================================

CREATE TABLE imagens (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    caminho VARCHAR(500) NOT NULL,
    quadra_id BIGINT UNSIGNED NOT NULL,

    PRIMARY KEY (id),

    CONSTRAINT fk_imagens_quadra
        FOREIGN KEY (quadra_id)
        REFERENCES quadra (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
) ENGINE=InnoDB;


-- =========================================================
-- ÍNDICES PARA CONSULTAS
-- =========================================================

CREATE INDEX idx_quadra_tipo
    ON quadra (tipoquadra_id);

CREATE INDEX idx_reserva_cliente
    ON reserva (cliente_id);

CREATE INDEX idx_reserva_quadra
    ON reserva (quadra_id);

CREATE INDEX idx_reserva_data
    ON reserva (data_inicio, data_final);

CREATE INDEX idx_imagens_quadra
    ON imagens (quadra_id);
