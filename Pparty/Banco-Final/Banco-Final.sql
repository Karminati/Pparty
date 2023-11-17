DROP SCHEMA if exists SistemaParty;
CREATE DATABASE SistemaParty;
USE SistemaParty;



CREATE TABLE tbusuario (
  email varchar(60) NOT NULL,
  datanasc date NOT NULL,
  nome varchar(80) NOT NULL,
  senha varchar(45) NOT NULL,
  endereco varchar(2000) NOT NULL,
  idUsuario int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (idUsuario),
  UNIQUE KEY email_UNIQUE (email),
  UNIQUE KEY idUsuario_UNIQUE (idUsuario)
);
CREATE TABLE TbFesta (
  idFesta int(11) NOT NULL AUTO_INCREMENT,
  data date NOT NULL,
  titulo varchar(45) NOT NULL,
  enderecofesta varchar(255) NOT NULL,
  valorIngresso double NOT NULL,
  cardapio varchar(300) NOT NULL,
  qingresso int(11) NOT NULL,
  categoria varchar(45) NOT NULL,
  latitude varchar (45),
  longitude varchar (45),
  idUsuariof int(11) NOT NULL,
  PRIMARY KEY (idFesta,idUsuariof),
  UNIQUE KEY idFesta_UNIQUE (idFesta),
  KEY fk_TbFesta_TbUsuario1_idx (idUsuariof),
  CONSTRAINT fk_TbFesta_TbUsuario1 FOREIGN KEY (idUsuariof) REFERENCES tbusuario (idUsuario) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE tbfotoFesta (
  idFoto int(11) NOT NULL AUTO_INCREMENT,
  url varchar(500) NOT NULL,
  fkFotoFesta int(11) NOT NULL,
  PRIMARY KEY (idFoto,fkFotoFesta),
  KEY fk_idFesta_idx (fkFotoFesta),
  CONSTRAINT fk_TbFotoFesta_TbFesta1 FOREIGN KEY (fkFotoFesta) REFERENCES tbfesta (idFesta) ON DELETE NO ACTION ON UPDATE NO ACTION
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

CREATE TABLE tbAvaliacao (
  idAvaliacao INT(11) NOT NULL AUTO_INCREMENT,
  quantAvaliacao INT(11),
  tbFesta_idFesta INT(11) NOT NULL,
  idUsuarioa int(11) NOT NULL,
  PRIMARY KEY (idAvaliacao,tbFesta_idFesta,idUsuarioa),
  KEY fk_tbAvaliacao_tbFesta_idx (tbFesta_idFesta),
  KEY fk_tbAvaliacao_tbusuarioidx (idUsuarioa),
  CONSTRAINT fk_tbAvaliacao_tbusuarioidx FOREIGN KEY (idUsuarioa) REFERENCES tbusuario (idUsuario) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_tbAvaliacao_tbFesta_idx FOREIGN KEY (tbFesta_idFesta) REFERENCES tbfesta (idFesta) ON DELETE NO ACTION ON UPDATE NO ACTION
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


CREATE TABLE tbcomentario (
  idComentario int(11) NOT NULL  AUTO_INCREMENT,
  coment varchar(210) NOT NULL,
  idAvaliacaoc int(11) NOT NULL,
  PRIMARY KEY (idComentario,idAvaliacaoc),
  KEY fk_TbComentario_TbAvaliacao1_idx (idAvaliacaoc),
  CONSTRAINT fk_TbComentario_TbAvaliacao1_idx FOREIGN KEY (idAvaliacaoc) REFERENCES tbAvaliacao (idAvaliacao) ON DELETE NO ACTION ON UPDATE NO ACTION
);

select * from tbusuario;
select * from TbFesta;
select * from tbAvaliacao;
select * from tbcomentario;


select * from TbFesta where titulo LIKE CONCAT('Festa Te', '%');

update tbcomentario SET coment = "aloooo" where idComentario = 1;


SELECT * from tbUsuario;
SELECT * from tbavaliacao;
SELECT * from tbcomentario;