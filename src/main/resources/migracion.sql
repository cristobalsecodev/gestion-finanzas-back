-- Migrations will appear here as you chat with AI

create table tipos_gastos (
                              id bigint primary key generated always as identity,
                              codigo text not null unique,
                              nombre text not null
);

create table tipos_ingresos (
                                id bigint primary key generated always as identity,
                                codigo text not null unique,
                                nombre text not null
);

create table tipos_inversiones (
                                   id bigint primary key generated always as identity,
                                   codigo text not null unique,
                                   nombre text not null
);

create table gastos (
                        id bigint primary key generated always as identity,
                        fecha date not null,
                        monto numeric(10, 2) not null,
                        tipo_gasto_id bigint references tipos_gastos (id)
);

create table ingresos (
                          id bigint primary key generated always as identity,
                          fecha date not null,
                          monto numeric(10, 2) not null,
                          tipo_ingreso_id bigint references tipos_ingresos (id)
);

create table inversiones (
                             id bigint primary key generated always as identity,
                             fecha date not null,
                             monto numeric(10, 2) not null,
                             tipo_inversion_id bigint references tipos_inversiones (id)
);

alter table gastos
    rename column monto to cantidad;

alter table ingresos
    rename column monto to cantidad;

alter table inversiones
    rename column monto to cantidad;

comment on table gastos is 'Tabla que registra los gastos con su fecha, cantidad y tipo de gasto.';

comment on table ingresos is 'Tabla que registra los ingresos con su fecha, cantidad y tipo de ingreso.';

comment on table inversiones is 'Tabla que registra las inversiones con su fecha, cantidad y tipo de inversión.';

alter table gastos
    add column descripcion text;

alter table ingresos
    add column descripcion text;

alter table inversiones
    add column descripcion text;

alter table inversiones
    rename column cantidad to cantidad_compra;

alter table inversiones
    add column cantidad_venta numeric(10, 2);

alter table inversiones
    rename column fecha to fecha_venta;

alter table inversiones
    add column fecha_compra date;

alter table inversiones
    alter column fecha_venta
        drop not null;

alter table inversiones
    alter column fecha_compra
        set not null;

create table rendimiento_anual_inversiones (
                                               id bigint primary key generated always as identity,
                                               inversion_id bigint references inversiones (id),
                                               anio int not null,
                                               rendimiento numeric(5, 2) not null
);