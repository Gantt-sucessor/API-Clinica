-- Adiciona a coluna (usando char(36) para compatibilidade simples com UUID())
alter table profissionais add column public_id char(36);

-- Gera UUIDs para os registros existentes
update profissionais set public_id = (select UUID());

-- Torna a coluna obrigatória e única
alter table profissionais modify column public_id char(36) not null;
alter table profissionais add constraint uk_profissionais_public_id unique (public_id);
