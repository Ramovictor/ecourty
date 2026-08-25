SELECT
    q.id,
    q.nome,
    q.tipoquadra_id,
    tq.tipo AS tipo,
    q.valor_hora
FROM quadra q
INNER JOIN tipo_quadra tq
    ON tq.id = q.tipoquadra_id
ORDER BY q.nome;
