create table produto
(
    id int auto_increment primary key,
    nome varchar(100) not null,
    descricao varchar(200) not null,
    fotoUrl varchar(200),
    dataCadastro timestamp not null,
    dataUltimaAtualizacao timestamp not null,
    valorUnitario decimal not null
);

create table categoria
(
    id int auto_increment primary key,
    nome varchar(100) not null,
    descricao varchar(200) not null,
    imagemSimboloUrl varchar(200)
);

create table categoriaProduto
(
    id int auto_increment primary key,
    categoriaId int not null,
    produtoId int not null,

    foreign key (categoriaId) references categoria(id),
    foreign key (produtoId) references produto(id)
);

create table promocao
(
    id int auto_increment primary key,
    nome varchar(100) not null,
    descricao varchar(200) not null,
    dataInicio date not null,
    dataFim date not null,
    dataCadastro timestamp not null,
    dataUltimaAtualizacao timestamp not null

);

create table promocaoItem
(
    id int auto_increment primary key,
    valorPromocao decimal not null,
    dataCadastro timestamp not null,
    dataUltimaAtualizacao timestamp not null,
    produtoId int not null,
    promocaoId int not null,

    foreign key (promocaoId) references promocao(id),
    foreign key (produtoId) references produto(id)
);