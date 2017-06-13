drop table usuario;
drop table funcionario;
drop table registro_ponto;
drop table config;


create table usuario (
    id serial primary key,
    nome varchar(255) unique,
    senha varchar(255),
    isAdmin boolean default false
);

create table funcionario (
    id serial primary key,
    nome varchar(255),
    cpf varchar(11) unique,
    salario decimal(10,2),
    hora_dia integer, -- salva em minutos
    valor_hora decimal(10,2)
);

create table registro_ponto (
    id serial primary key,
    dia date,
    id_funcionario int,
    entrada_a int,
    saida_a int,
    entrada_b int,
    saida_b int,
    horas_excedidas int, -- quantidade de minutos excedidos
    percent_aplicado decimal(5,2), -- 50% ou 100% (controlado pela aplicaçao)
    valor_extra decimal(10,2), -- valor acumulado de horas extras no dia
    total_recebido decimal(10,2) --valor cheio recebido no dia com horas extras
);

create table config (
    key_x varchar(10),
    value_x Int
);
insert into config(key_x,value_x) values('lang', 0);
select * from config;

truncate table usuario;
truncate table funcionario;
truncate table registro_ponto;

select * from funcionario;


CREATE FUNCTION fn_min_to_hr(mins bigint)
RETURNS text AS
$BODY$ 
select overlay(to_char(cast(date_part('hours',interval '1 minute' * mins) * 1.0 + 
(date_part('minutes',interval '1 minute' * mins) * .01) as numeric(4,2)), '99.99') placing ':' from 4);
$BODY$
LANGUAGE sql VOLATILE;

-- Relatorio principal (total de horas extras pagas)
select
to_char(rp.dia, 'dd/MM/yyyy'),
f.nome,
f.salario,
f.hora_dia, f.valor_hora,
fn_min_to_hr((rp.saida_b-rp.entrada_a)-(rp.entrada_b-rp.saida_a)) as horas_trabalhadas,
rp.percent_aplicado,
fn_min_to_hr(rp.horas_excedidas) as horas_extras,
rp.valor_extra,
rp.total_recebido
from registro_ponto rp
join funcionario f on f.id = rp.id_funcionario
order by rp.dia

-- Folha de ponto
select to_char(rp.dia,'dd/MM/yyyy') as dia, f.nome,
fn_min_to_hr(rp.entrada_a) as entrada_a,
fn_min_to_hr(rp.saida_a) as saida_a,
fn_min_to_hr(rp.entrada_b) as entrada_b,
fn_min_to_hr(rp.saida_b) as saida_b
from registro_ponto rp
join funcionario f on f.id = rp.id_funcionario 
where rp.id_funcionario = 0
order by  rp.dia;

-- Total recebido por funcionario 
select f.nome, 
fn_min_to_hr(sum(((rp.saida_b-rp.entrada_a)-(rp.entrada_b-rp.saida_a)) + rp.horas_excedidas)) as horas_trabalhadas,
sum(rp.valor_extra) as horas_extras, 
sum(rp.total_recebido) as total_do_dia 
from registro_ponto rp
join funcionario f on f.id = rp.id_funcionario
group by f.nome, rp.id_funcionario;



