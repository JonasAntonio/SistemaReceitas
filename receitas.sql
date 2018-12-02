DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `senha` varchar(32) NOT NULL,
  `administrador` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `receita`;

CREATE TABLE `receita` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(30) NOT NULL,
  `imagem` blob,
  `video` varchar(255) DEFAULT NULL,
  `ingredientes` longtext NOT NULL,
  `preparo` longtext NOT NULL,
  `usuario` bigint(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_usuario_receita` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `avaliacao`;

CREATE TABLE `avaliacao` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `avaliacao` int(11) DEFAULT NULL,
  `comentario` longtext,
  `titulo` varchar(30) DEFAULT NULL,
  `receita` bigint(11) NOT NULL,
  `usuario` bigint(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_usuario_avaliacao` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_receita_avaliacao` FOREIGN KEY (`receita`) REFERENCES `receita` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

