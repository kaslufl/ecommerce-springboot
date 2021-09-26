insert into produto(nome, descricao, dataCadastro, dataUltimaAtualizacao, valorUnitario)
values ('Refrigereco', 'Refrigereco de Qualidade 500ml', '2021-09-23 22:56:18.772429', '2021-09-23 22:56:18.772429', 1);

insert into categoria(nome, descricao)
values ('Bebidas', 'Bebidas');

insert into categoria(nome, descricao)
values ('Refrigerante', 'Refrigerantes');

insert into categoriaProduto(categoriaId, produtoId)
values (1, 1);

insert into promocao(nome, descricao, dataInicio, dataFim, dataCadastro, dataUltimaAtualizacao)
values ('Promoção caçulinha', 'Bebidas com menos de 1L', '2021-09-21',  '2021-09-28', '2021-09-23 22:56:18.772429', '2021-09-23 22:56:18.772429');

insert into promocaoItem(valorPromocao, dataCadastro, dataUltimaAtualizacao, produtoId, promocaoId)
values (2, '2021-09-23 22:56:18.772429', '2021-09-23 22:56:18.772429', 1, 1);
