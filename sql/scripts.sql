drop table usuario;
drop table funcionario;
drop table registro_ponto;
drop table config;

create table usuario (
    id serial primary key,
    nome varchar(255),
    senha varchar(255),
    isAdmin boolean
);

create table funcionario (
    id serial primary key,
    name varchar(255),
    salario decimal(10,2),
    hora_base integer,
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



select * from config;
