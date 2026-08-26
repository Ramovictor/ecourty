SELECT
    q.id AS quadra_id,
    q.nome AS quadra,
    tq.tipo AS tipo_quadra,
    q.valor_hora,
    i.id AS imagem_id,
    i.caminho
FROM quadra q
INNER JOIN tipo_quadra tq
    ON tq.id = q.tipoquadra_id
LEFT JOIN imagens i
    ON i.quadra_id = q.id
ORDER BY q.id, i.id;
