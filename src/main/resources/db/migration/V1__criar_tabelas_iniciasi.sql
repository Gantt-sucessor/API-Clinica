create table profissionais(
    id int primary key not null,
    nome_completo varchar(255) not null,
    email varchar(255) not null,
    senha varchar(255) not  null,
    ativo boolean not null
);