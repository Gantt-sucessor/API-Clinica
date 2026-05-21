UPDATE profissionais
SET data_inativacao = NOW()
WHERE ativo = 0 AND data_inativacao IS NULL;