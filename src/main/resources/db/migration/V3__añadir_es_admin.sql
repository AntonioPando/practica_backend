-- Añadir columna es_admin (no nullable) al table usuario
ALTER TABLE usuario
  ADD COLUMN es_admin BOOLEAN NOT NULL DEFAULT FALSE;
