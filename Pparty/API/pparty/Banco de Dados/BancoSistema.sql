DROP SCHEMA if exists SistemaParty;
CREATE DATABASE SistemaParty;
USE SistemaParty;



CREATE TABLE tbusuario (
  email varchar(60) NOT NULL,
  idade int(11) NOT NULL,
  nome varchar(80) NOT NULL,
  senha varchar(45) NOT NULL,
  endereco varchar(2000) NOT NULL,
  idUsuario int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (idUsuario),
  UNIQUE KEY email_UNIQUE (email),
  UNIQUE KEY idUsuario_UNIQUE (idUsuario)
);
CREATE TABLE tbfesta (
  idFesta int(11) NOT NULL AUTO_INCREMENT,
  data date NOT NULL,
  titulo varchar(45) NOT NULL,
  enderecofesta varchar(255) NOT NULL,
  valorIngresso double NOT NULL,
  cardapio varchar(300) NOT NULL,
  rating int(11) NOT NULL,
  qIngresso int(11) NOT NULL,
  ratingQuant int(11) NOT NULL,
  categoria varchar(45) NOT NULL,
  latitude varchar (45) NOT NULL,
  longitude varchar (45) NOT NULL,
  TbUsuario_idUsuario int(11) NOT NULL,
  PRIMARY KEY (idFesta,TbUsuario_idUsuario),
  UNIQUE KEY idFesta_UNIQUE (idFesta),
  KEY fk_TbFesta_TbUsuario1_idx (TbUsuario_idUsuario),
  CONSTRAINT fk_TbFesta_TbUsuario1 FOREIGN KEY (TbUsuario_idUsuario) REFERENCES tbusuario (idUsuario) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE tbfotoFesta (
  idFoto int(11) NOT NULL AUTO_INCREMENT,
  url varchar(500) NOT NULL,
  idFesta int(11) NOT NULL,
  PRIMARY KEY (idFoto,idFesta),
  KEY fk_idFesta_idx (idFesta),
  CONSTRAINT fk_TbFotoFesta_TbFesta1 FOREIGN KEY (idFesta) REFERENCES tbfesta (idFesta) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE tbfotoUsuario (
  idFoto int(11) NOT NULL AUTO_INCREMENT,
  url varchar(500) NOT NULL,
  idUsuario int(11) NOT NULL,
  PRIMARY KEY (idFoto,idUsuario),
  KEY fk_idUsuario_idx (idUsuario),
  CONSTRAINT fk_tbfotoUsuario_Tbusuario FOREIGN KEY (idUsuario) REFERENCES tbusuario (idUsuario) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE tbchat (
  Mensagem varchar(300) ,
  idMensagem int(11) NOT NULL AUTO_INCREMENT,
  idUsuario1 int(11) NOT NULL,
  idUsuario2 int(11) NOT NULL,
  PRIMARY KEY (IdMensagem,idUsuario1,idUsuario2),
  KEY fk_tbChat_TbUsuario1_idx (idUsuario1),
  KEY fk_tbChat_TbUsuario2_idx (idUsuario2),
  CONSTRAINT fk_tbChat_TbUsuario1 FOREIGN KEY (idUsuario1) REFERENCES tbusuario (idUsuario) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_tbChat_TbUsuario2 FOREIGN KEY (idUsuario2) REFERENCES tbusuario (idUsuario) ON DELETE NO ACTION ON UPDATE NO ACTION
);



CREATE TABLE tbmensagens (
  idMensagens INT(11) NOT NULL AUTO_INCREMENT,
  conteudo VARCHAR(320) NOT NULL,
  tbChat_IdMensagem int(11) NOT NULL,
  remetente TINYINT(4) NOT NULL,
  PRIMARY KEY (idMensagens),
  KEY fk_Mensagens_tbChat1_idx (tbChat_IdMensagem),
  CONSTRAINT fk_Mensagens_tbChat1 FOREIGN KEY (tbChat_IdMensagem) REFERENCES tbchat (IdMensagem) ON DELETE NO ACTION ON UPDATE NO ACTION
    );


CREATE TABLE tbcomentario_festa (
  idComentario int(11) NOT NULL  AUTO_INCREMENT,
  comentario varchar(210) NOT NULL,
  idFesta int(11) NOT NULL,
  idUsuario int(11) NOT NULL,
  PRIMARY KEY (idComentario,idFesta,idUsuario),
  KEY fk_TbComentario_Festa_TbFesta1_idx (idFesta),
  KEY fk_TbComentario_Festa_TbUsuario1_idx (idUsuario),
  CONSTRAINT fk_TbComentario_Festa_TbFesta1 FOREIGN KEY (idFesta) REFERENCES tbfesta (idFesta) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_TbComentario_Festa_TbUsuario1 FOREIGN KEY (idUsuario) REFERENCES tbusuario (idUsuario) ON DELETE NO ACTION ON UPDATE NO ACTION
);


select * from tbfotoFesta