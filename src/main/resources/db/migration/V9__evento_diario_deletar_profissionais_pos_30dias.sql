-- Habilitando o Event Scheduler (caso seja necessário)
set global event_scheduler = on;

create event if not exists deletar_usuario_inativos
on schedule every 1 day
-- Rode às 2h da manhã do próximo dia
starts (timestamp(current_date) + interval 1 day + interval 2 hour)
do
    delete from profissionais
    where ativo = 0
        and data_inativacao < now() - interval 30 day;