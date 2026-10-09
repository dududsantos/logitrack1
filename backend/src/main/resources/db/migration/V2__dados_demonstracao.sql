-- Dados de demonstração com datas relativas ao dia em que o banco é criado..

-- Manutenções futuras (cronograma + projeção do mês)
INSERT INTO manutencoes (veiculo_id, data_inicio, data_finalizacao, tipo_servico, custo_estimado, status) VALUES
(4, CURRENT_DATE + 2,  CURRENT_DATE + 3,  'Troca de Óleo',      400.00, 'PENDENTE'),
(2, CURRENT_DATE + 5,  CURRENT_DATE + 7,  'Motor',             4800.00, 'PENDENTE'),
(3, CURRENT_DATE + 9,  CURRENT_DATE + 9,  'Troca de Pneus',    2100.00, 'PENDENTE'),
(1, CURRENT_DATE + 15, CURRENT_DATE + 16, 'Revisão de Freios',  900.00, 'PENDENTE'),
(4, CURRENT_DATE + 20, CURRENT_DATE + 22, 'Suspensão',         3200.00, 'PENDENTE'),
(2, CURRENT_DATE + 40, CURRENT_DATE + 41, 'Troca de Óleo',      600.00, 'PENDENTE');

-- Viagens recentes (veículos que ainda não tinham viagens na V1)
INSERT INTO viagens (veiculo_id, data_saida, data_chegada, origem, destino, km_percorrida) VALUES
(3, NOW() - INTERVAL '10 days', NOW() - INTERVAL '10 days' + INTERVAL '6 hours', 'Natal',    'Recife',   290.00),
(4, NOW() - INTERVAL '7 days',  NOW() - INTERVAL '6 days',                        'Recife',   'Salvador', 840.00),
(4, NOW() - INTERVAL '3 days',  NOW() - INTERVAL '2 days',                        'Salvador', 'Aracaju',  330.00);
