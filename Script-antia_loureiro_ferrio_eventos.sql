-- Creación de la Base de Datos:

CREATE SCHEMA `antia_loureiro_ferrio_eventos`


-- Creación de las tablas:

-- Categoría:

USE `antia_loureiro_ferrio_eventos`;
CREATE TABLE `categoria` (
	`ID` VARCHAR(50) NOT NULL,
	`Nombre` ENUM('taller', 'conferencia', 'actividad') NOT NULL,
	`Descripcion` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`ID`)
);

-- Evento

USE `antia_loureiro_ferrio_eventos`;
CREATE TABLE `evento` (
	`ID_Categoria` VARCHAR(50) NOT NULL,
	`ID_Evento` VARCHAR(50) NOT NULL,
	`Nombre` VARCHAR(100) NOT NULL,
	`Fecha` VARCHAR(16) NOT NULL CHECK (
		`Fecha` REGEXP '^([0-1][0-9]|2[0-3]):([0-5][0-9])-(0[1-9]|[1-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/([0-9]{4})$'
	),
	`Duracion` INT NOT NULL,
	`Tipo` ENUM('online', 'presencial') NOT NULL,
	`Ubicacion` VARCHAR(255) NOT NULL ,
	PRIMARY KEY (`ID_Categoria`, `ID_Evento`),
	CONSTRAINT `ID_Categoria`
		FOREIGN KEY (`ID_Categoria`)
		REFERENCES `antia_loureiro_ferrio_eventos`.`categoria` (`ID`)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
);

-- Usuario

USE `antia_loureiro_ferrio_eventos`;
CREATE TABLE `usuario` (
	`Nick` VARCHAR(50) NOT NULL,
	`Nombre` VARCHAR(50) NOT NULL,
	`Correo-e` VARCHAR(255) NOT NULL CHECK (
		`Correo-e` REGEXP '^[^@]+@[^@]+\\.[^@]+$'
	),
	`Contraseña` VARCHAR(20) NOT NULL CHECK (
		LENGTH(`Contraseña`) BETWEEN 8 AND 20 AND
		`Contraseña` REGEXP '.*[A-Z].*[A-Z]' AND
		`Contraseña` REGEXP '.*[a-z].*[a-z]' AND
		`Contraseña` REGEXP '.*[0-9].*[0-9]' AND
		`Contraseña` REGEXP '.*[^a-zA-Z0-9].*[^a-zA-Z0-9]'
	),
	PRIMARY KEY (`Nick`)
);

-- Organizador

USE `antia_loureiro_ferrio_eventos`;
CREATE TABLE `organizador` (
	`ID` VARCHAR(50) NOT NULL,
	`Nombre` VARCHAR(50) NOT NULL,
	`Correo-e` VARCHAR(255) NOT NULL CHECK (
		`Correo-e` REGEXP '^[^@]+@[^@]+\\.[^@]+$'
	),
	`Contraseña` VARCHAR(20) NOT NULL CHECK (
		LENGTH(`Contraseña`) BETWEEN 8 AND 20 AND
		`Contraseña` REGEXP '.*[A-Z].*[A-Z]' AND
		`Contraseña` REGEXP '.*[a-z].*[a-z]' AND
		`Contraseña` REGEXP '.*[0-9].*[0-9]' AND
		`Contraseña` REGEXP '.*[^a-zA-Z0-9].*[^a-zA-Z0-9]'
	),
	`Tipo` ENUM('institucion', 'empresa', 'persona') NOT NULL,
	`Telefono` CHAR(9) NULL,
	PRIMARY KEY (`ID`)
);

-- Inscribirse

USE `antia_loureiro_ferrio_eventos`;
CREATE TABLE `inscribirse` (
	`ID_Categoria` VARCHAR(50) NOT NULL,
	`ID_Evento` VARCHAR(50) NOT NULL,
	`Nick_Usuario` VARCHAR(50) NOT NULL,
	`Fecha` VARCHAR(10) NOT NULL CHECK (
		Fecha` REGEXP '^0[1-9]|[1-2][0-9]|3[0-1]/0[1-9]|1[0-2]/[0-9]{4}$'
	),
	PRIMARY KEY (`ID_Categoria`, `ID_Evento`, `Nick_Usuario`),
	INDEX `Nick_Usuario_idx` (`Nick_Usuario` ASC) VISIBLE,
	CONSTRAINT `ID_Evento_Categoria_Inscribirse `
		FOREIGN KEY (`ID_Categoria` , `ID_Evento`)
		REFERENCES `antia_loureiro_ferrio_eventos`.`evento` (`ID_Categoria` , `ID_Evento`)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION,
	CONSTRAINT `Nick_Usuario_Inscribirse `
		FOREIGN KEY (`Nick_Usuario`)
		REFERENCES `antia_loureiro_ferrio_eventos`.`usuario` (`Nick`)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
);

-- Crear

USE `antia_loureiro_ferrio_eventos`;
CREATE TABLE `crear` (
	`ID_Categoria` VARCHAR(50) NOT NULL,
	`ID_Evento` VARCHAR(50) NOT NULL,
	`ID_Organizador` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`ID_Categoria`, `ID_Evento`, `ID_Organizador`),
	INDEX `ID_Organizador_idx` (`ID_Organizador` ASC) VISIBLE,
	CONSTRAINT `ID_Evento_Categoria_Crear`
		FOREIGN KEY (`ID_Categoria` , `ID_Evento`)
		REFERENCES `antia_loureiro_ferrio_eventos`.`evento` (`ID_Categoria` , `ID_Evento`)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION,
	CONSTRAINT `ID_Organizador_Crear`
		FOREIGN KEY (`ID_Organizador`)
		REFERENCES `antia_loureiro_ferrio_eventos`.`organizador` (`ID`)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
);


-- Triggers para la tabla Evento:

-- Ubicación derivado:

DELIMITER $$

DROP TRIGGER IF EXISTS ubicacion_evento$$
CREATE TRIGGER ubicacion_evento
BEFORE INSERT ON evento
FOR EACH ROW
BEGIN
	IF NEW.tipo NOT LIKE 'presencial' THEN
		SET NEW.ubicacion = null;
	END IF;
END;
$$

DELIMITER ;

-- Evento débil de Categoría:

DELIMITER $$

DROP TRIGGER IF EXISTS evento_debil_categoria$$
CREATE TRIGGER evento_debil_categoria
BEFORE INSERT ON evento
FOR EACH ROW
BEGIN
	DECLARE categoriaExiste INT;

	SELECT COUNT(*) INTO categoriaExiste
	FROM categoria
	WHERE ID_Categoria = NEW.ID_Categoria;

	IF categoriaExiste = 0 THEN
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'No se puede insertar el evento porque la categoría no existe.';
	END IF;
END;
$$

DELIMITER ;