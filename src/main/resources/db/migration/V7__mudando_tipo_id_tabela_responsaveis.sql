-- 1. Remove a FK
ALTER TABLE paciente_responsavel
DROP FOREIGN KEY paciente_responsavel_ibfk_2;

-- 2. Altera o tipo na tabela pai
ALTER TABLE responsaveis
MODIFY COLUMN id_responsavel BIGINT NOT NULL AUTO_INCREMENT;

-- 3. Altera o tipo na coluna filha
ALTER TABLE paciente_responsavel
MODIFY COLUMN id_responsavel BIGINT NOT NULL;

-- 4. Recria a FK
ALTER TABLE paciente_responsavel
ADD CONSTRAINT paciente_responsavel_ibfk_2
FOREIGN KEY (id_responsavel) REFERENCES responsaveis(id_responsavel);