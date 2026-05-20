-- 1. Remove a FK da tabela filha
ALTER TABLE paciente_responsavel
DROP FOREIGN KEY paciente_responsavel_ibfk_1;

-- 2. Altera o tipo na tabela pai
ALTER TABLE profissionais
MODIFY COLUMN id_profissional BIGINT NOT NULL AUTO_INCREMENT;

-- 3. Altera o tipo na coluna filha também (precisa bater)
ALTER TABLE paciente_responsavel
MODIFY COLUMN id_profissional BIGINT NOT NULL;

-- 4. Recria a FK
ALTER TABLE paciente_responsavel
ADD CONSTRAINT paciente_responsavel_ibfk_1
FOREIGN KEY (id_profissional) REFERENCES profissionais(id_profissional);