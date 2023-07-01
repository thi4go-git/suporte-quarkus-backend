-- ADICIONANDO Pessoas na tabela para execução dos testes
INSERT INTO pessoa (id, cep, cpf, data_cadastro, email, nacionalidade, nome, telefone, titulo_eleitoral, ativo)
VALUES (1, '74913330', '57555840014' , current_date, 'user1@gmail.com', 1, 'Flávio da Silva','62982545980',true );

INSERT INTO pessoa (id, cep, cpf, data_cadastro, email, nacionalidade, nome, telefone, titulo_eleitoral, ativo)
VALUES (2, '74914220', '69076853029' , current_date, 'user2@gmail.com', 2, 'Paulo Pedro','61982525982',false );

INSERT INTO pessoa (id, cep, cpf, data_cadastro, email, nacionalidade, nome, telefone, titulo_eleitoral, ativo)
VALUES (1, '74913339', '50745666060' , current_date, 'user3@gmail.com', 1, 'Silas Neves Judas','55982542289',true );
