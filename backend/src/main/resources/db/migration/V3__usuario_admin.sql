-- Usuário padrão para acessar o sistema: admin / admin123
-- A senha é gravada como hash BCrypt, nunca em texto puro.
INSERT INTO usuarios (username, password, role)
VALUES ('admin', '$2y$10$nTNyKBJ3c.KY2TS8bcPBCe1KtFElHX.EwMJ2xyC4QwYGpCti7jEcq', 'ADMIN');
