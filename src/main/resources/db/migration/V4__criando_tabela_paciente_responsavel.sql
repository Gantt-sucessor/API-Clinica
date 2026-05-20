create table paciente_responsavel(
    id int primary key not null,
    id_profissional INT NOT NULL,
    id_responsavel INT NOT NULL,
    foreign key (id_profissional) references profissionais(id_profissional),
    foreign key (id_responsavel) references responsaveis(id_responsavel)
);