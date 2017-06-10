create table users (
    id serial primary key,
    name varchar(255),
    password varchar(255),
    isAdmin boolean
);

create table funcionarios (
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
    percent_aplicado decimal(5,2), -- 50% ou 100% (controlado pela aplicaçao)
    horas_excedidas int, -- quantidade de minutos excedidos
    valor_extra decimal(10,2) -- valor acumulado de horas extras no dia
    total_recebido decimal(10,2) --valor cheio recebido no dia com horas extras
)