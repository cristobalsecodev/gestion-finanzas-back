
create table categorias_gasto (
  id bigint primary key generated always as identity,
  codigo text not null unique,
  nombre text not null
);

create table categorias_ingreso (
  id bigint primary key generated always as identity,
  codigo text not null unique,
  nombre text not null
);

create table categorias_inversion (
  id bigint primary key generated always as identity,
  codigo text not null unique,
  nombre text not null
);

create table subcategorias_gasto (
  id bigint primary key generated always as identity,
  codigo text not null unique,
  nombre text not null,
  categoria_gasto_id bigint references categorias_gasto (id)
);

create table subcategorias_ingreso (
  id bigint primary key generated always as identity,
  codigo text not null unique,
  nombre text not null,
  categoria_ingreso_id bigint references categorias_ingreso (id)
);

create table subcategorias_inversion (
  id bigint primary key generated always as identity,
  codigo text not null unique,
  nombre text not null,
  categoria_inversion_id bigint references categorias_inversion (id)
);

create table gastos (
  id bigint primary key generated always as identity,
  fecha date not null,
  cantidad numeric(10, 2) not null,
  tipo_gasto_id bigint references tipos_gastos (id) not null,
  notas text
);

create table ingresos (
  id bigint primary key generated always as identity,
  fecha date not null,
  cantidad numeric(10, 2) not null,
  tipo_ingreso_id bigint references tipos_ingresos (id) not null,
  notas text
);

create table inversiones (
  id bigint primary key generated always as identity,
  fecha_compra date not null,
  cantidad_compra numeric(10, 2) not null,
  tipo_inversion_id bigint references tipos_inversiones (id) not null,
  notas text,
  cantidad_venta numeric(10, 2),
  fecha_venta date
);