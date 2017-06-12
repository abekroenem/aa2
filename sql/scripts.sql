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
    key varchar(10),
    value Int
);
insert into config(key,value) values('lang', 0);

