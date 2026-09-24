USE ecourty;

-- =========================================================
-- 1. USUARIOS
-- =========================================================
-- As senhas abaixo são hashes BCrypt de exemplo.
-- Em produção, os hashes devem ser gerados pela aplicação.

INSERT INTO usuario (id, nome, email, senha) VALUES
(1, 'Administrador', 'admin@ecourty.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy'),
(2, 'João Silva', 'joao.silva@ecourty.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy'),
(3, 'Maria Oliveira', 'maria.oliveira@ecourty.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy'),
(4, 'Carlos Santos', 'carlos.santos@ecourty.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy'),
(5, 'Ana Costa', 'ana.costa@ecourty.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy');


-- =========================================================
-- 2. TIPOS DE QUADRA
-- =========================================================

INSERT INTO tipo_quadra (id, tipo) VALUES
(1, 'Futebol'),
(2, 'Futsal'),
(3, 'Vôlei'),
(4, 'Basquete'),
(5, 'Tênis');


-- =========================================================
-- 3. CLIENTES
-- =========================================================

INSERT INTO cliente (id, nome, telefone, email, usuario_id) VALUES
(1, 'Rafael Almeida', '(48) 99911-2233', 'rafael.almeida@email.com', 1),
(2, 'Juliana Martins', '(48) 99822-3344', 'juliana.martins@email.com', 1),
(3, 'Pedro Henrique', '(48) 99733-4455', 'pedro.henrique@email.com', 1),
(4, 'Camila Souza', '(48) 99644-5566', 'camila.souza@email.com', 1),
(5, 'Lucas Ferreira', '(48) 99555-6677', 'lucas.ferreira@email.com', 1);


-- =========================================================
-- 4. QUADRAS
-- =========================================================

INSERT INTO quadra (id, nome, tipoquadra_id, valor_hora, usuario_id) VALUES
(1, 'Campo Principal', 1, 180.00, 1),
(2, 'Quadra Futsal A', 2, 120.00, 1),
(3, 'Quadra Vôlei Arena', 3, 100.00, 1),
(4, 'Quadra Basquete Central', 4, 90.00, 1),
(5, 'Quadra de Tênis Premium', 5, 110.00, 1);


-- =========================================================
-- 5. RESERVAS
-- =========================================================

INSERT INTO reserva
    (id, cliente_id, quadra_id, data, horario_inicio, horario_fim, valor, status_pagamento)
VALUES
(1, 1, 1, '2026-09-01', '18:00:00', '20:00:00', 360.00, 'Pago'),

(2, 2, 2, '2026-09-02', '19:00:00', '20:00:00', 120.00, 'Pago'),

(3, 3, 3, '2026-09-03', '17:00:00', '19:00:00', 200.00, 'Pendente'),

(4, 4, 4, '2026-09-04', '20:00:00', '21:00:00', 90.00, 'Pendente'),

(5, 5, 5, '2026-09-05', '15:00:00', '17:00:00', 220.00, 'Pago');


-- =========================================================
-- 6. IMAGENS
-- =========================================================
-- URLs públicas de imagens hospedadas no Wikimedia Commons.
-- Special:Redirect/file permite acessar diretamente o arquivo.

INSERT INTO imagens (id, caminho, quadra_id) VALUES

(1,
 'https://commons.wikimedia.org/wiki/Special:Redirect/file/Futebol.JPG',
 1),

(2,
 'https://commons.wikimedia.org/wiki/Special:Redirect/file/Football_Field_B.jpg',
 1),

(3,
 'https://commons.wikimedia.org/wiki/Special:Redirect/file/Futebol.JPG',
 2),

(4,
 'https://commons.wikimedia.org/wiki/Special:Redirect/file/Volleyball_court.jpg',
 3),

(5,
 'https://commons.wikimedia.org/wiki/Special:Redirect/file/Volleyball_Earls_Court.jpg',
 3),

(6,
 'https://commons.wikimedia.org/wiki/Special:Redirect/file/Basketball_Court.jpg',
 4),

(7,
 'https://commons.wikimedia.org/wiki/Special:Redirect/file/Basketballcourt.jpg',
 4),

(8,
 'https://commons.wikimedia.org/wiki/Special:Redirect/file/Tennis_courts.jpg',
 5),

(9,
 'https://commons.wikimedia.org/wiki/Special:Redirect/file/Tennis_Court..JPG',
 5),

(10,
 'https://commons.wikimedia.org/wiki/Special:Redirect/file/Tennis_Court.JPG',
 5);
