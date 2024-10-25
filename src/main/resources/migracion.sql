create table usuarios (
  id bigint primary key generated always as identity,
  nombre text not null,
  email text not null unique,
  apellidos text not null,
  fecha_creacion date default now()
);

create table categorias_gasto (
  id bigint primary key generated always as identity,
  codigo text not null unique,
  nombre text not null,
  usuario_id bigint references usuarios (id) on delete cascade
);

create table categorias_ingreso (
  id bigint primary key generated always as identity,
  codigo text not null unique,
  nombre text not null,
  usuario_id bigint references usuarios (id) on delete cascade
);

create table categorias_inversion (
  id bigint primary key generated always as identity,
  codigo text not null unique,
  nombre text not null,
  usuario_id bigint references usuarios (id) on delete cascade
);

create table subcategorias_gasto (
  id bigint primary key generated always as identity,
  codigo text not null unique,
  nombre text not null,
  usuario_id bigint references usuarios (id) on delete cascade,
  categoria_gasto_id bigint references categorias_gasto (id) on delete cascade
);

create table subcategorias_ingreso (
  id bigint primary key generated always as identity,
  codigo text not null unique,
  nombre text not null,
  usuario_id bigint references usuarios (id) on delete cascade,
  categoria_ingreso_id bigint references categorias_ingreso (id) on delete cascade
);

create table subcategorias_inversion (
  id bigint primary key generated always as identity,
  codigo text not null unique,
  nombre text not null,
  usuario_id bigint references usuarios (id) on delete cascade,
  categoria_inversion_id bigint references categorias_inversion (id) on delete cascade
);

create table gastos (
  id bigint primary key generated always as identity,
  fecha date not null,
  cantidad numeric(10, 2) not null,
  categoria_descripcion text,
  subcategoria_descripcion text,
  notas text,
  usuario_id bigint references usuarios (id) on delete cascade
);

create table ingresos (
  id bigint primary key generated always as identity,
  fecha date not null,
  cantidad numeric(10, 2) not null,
  categoria_descripcion text,
  subcategoria_descripcion text,
  notas text,
  usuario_id bigint references usuarios (id) on delete cascade
);

create table inversiones (
  id bigint primary key generated always as identity,
  fecha_compra date not null,
  cantidad_compra numeric(10, 2) not null,
  categoria_descripcion text,
  subcategoria_descripcion text,
  notas text,
  cantidad_venta numeric(10, 2),
  fecha_venta date,
  usuario_id bigint references usuarios (id) on delete cascade
);