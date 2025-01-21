alter table usuarios
add column estado enum('ACTIVO','INACTIVO') default 'ACTIVO';

alter table usuarios
change rol perfil varchar(100);