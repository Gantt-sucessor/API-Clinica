create table responsaveis(
    id primary key not null,
    nome_completo varchar(200) not null,
    cpf varchar(255) not null,
    data_nascimento date not null
);