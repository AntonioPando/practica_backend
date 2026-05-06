INSERT INTO `genero` (`nombre`) VALUES ('Hombre'),('Mujer');

INSERT INTO `puesto_de_trabajo` (`nombre`) VALUES ('Desarrollador'),('Analista'),('Tester'),('Jefe de proyecto');

INSERT INTO `usuario` (`fecha_hora_creacion`, `fecha_nacimiento`, `hora_desayuno`, `nick_usuario`, `password`,`nombre`, `primer_apellido`, `segundo_apellido`, `genero_id`, `puesto_de_trabajo_id`) VALUES
('2024-01-01 10:00:00', '1990-05-15', '08:00:00', 'juan123', 'password1','Juan', 'Pérez', 'García', 1, 1);

INSERT INTO `direccion` (`direccion_principal`, `nombre_calle`, `numero_calle`, `usuario_id`) VALUES
(1, 'Calle Mayor', 10, 1),
(0, 'Avenida Central', 5, 1);