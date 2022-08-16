create table curso(
    id bigint not null auto_increment,
    nome varchar(50) not null,
    categoria varchar(50) not null,
    primary key(id)
);

insert into curso(id, nome, categoria) values(1, 'Kotlin', 'PROGRAMACAO');
insert into curso(id, nome, categoria) values(2, 'Java', 'PROGRAMACAO');
insert into curso(id, nome, categoria) values(3, 'Spring', 'PROGRAMACAO');
insert into curso(id, nome, categoria) values(4, 'Kotlin', 'PROGRAMACAO');
insert into curso(id, nome, categoria) values(5, 'Vegan Food', 'CULINARIA');