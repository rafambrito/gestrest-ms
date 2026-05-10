INSERT INTO tipo_usuario (nome_tipo)
SELECT 'DONO'
WHERE NOT EXISTS (
    SELECT 1
    FROM tipo_usuario
    WHERE nome_tipo = 'DONO'
);

INSERT INTO tipo_usuario (nome_tipo)
SELECT 'CLIENTE'
WHERE NOT EXISTS (
    SELECT 1
    FROM tipo_usuario
    WHERE nome_tipo = 'CLIENTE'
);
