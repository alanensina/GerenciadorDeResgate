create database stock;

use stock;

create table plano(
id int not null auto_increment,
nome varchar(100) not null,
cnpj varchar(50) not null,
fk_endereco int not null,
data_fundacao date not null,
CONSTRAINT PK_plano PRIMARY KEY (id)
);

create table endereco(
id int not null auto_increment,
logradouro varchar(255) not null,
numero varchar(10) not null,
bairro varchar(255) not null,
complemento varchar(255),
cep varchar(15) not null,
cidade varchar(40) not null,
uf varchar(25) not null,
CONSTRAINT PK_endereco PRIMARY KEY (id)
);

ALTER TABLE plano ADD CONSTRAINT fk_endereco FOREIGN KEY(fk_endereco) REFERENCES endereco(id);

create table participante(
id int not null auto_increment,
nome varchar(100) not null,
cpf varchar(25) not null,
celular varchar(20) not null,
email varchar(50) not null,
data_nascimento date not null,
fk_plano int not null,
data_associacao date not null,
status varchar(20) not null,
fk_endereco int not null,
CONSTRAINT PK_participante PRIMARY KEY (id),
CONSTRAINT fk_endereco_participante FOREIGN KEY(fk_endereco) REFERENCES endereco(id),
CONSTRAINT fk_plano_participante FOREIGN KEY(fk_plano) REFERENCES plano(id)
);

create table conta(
id int not null auto_increment,
fk_participante int not null,
saldoNormal double not null,
saldoAdicional double,
saldoPortabilidade double,
ultimoResgate date,
CONSTRAINT PK_conta PRIMARY KEY (id),
CONSTRAINT fk_conta_participante FOREIGN KEY(fk_participante) REFERENCES participante(id)
);
