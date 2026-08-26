SELECT
    r.id,
    r.cliente_id,
    c.nome AS cliente,
    r.quadra_id,
    q.nome AS quadra,
    tq.tipo AS tipo_quadra,
    r.data_inicio,
    r.data_final,
    r.valor
FROM reserva r
INNER JOIN cliente c
    ON c.id = r.cliente_id
INNER JOIN quadra q
    ON q.id = r.quadra_id
INNER JOIN tipo_quadra tq
    ON tq.id = q.tipoquadra_id
ORDER BY r.data_inicio;
