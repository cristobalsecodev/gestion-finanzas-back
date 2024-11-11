-- Tabla de URLs de uso único / expirables
create table one_time_url (
    token text primary key,
    used boolean not null default false,
    expiration_time bigint,
    email text,
    type text,
    url text
);

-- Tabla de usuarios
create table users (
    id bigint primary key generated always as identity,
    name varchar(255),
    email text not null unique,
    surnames varchar(255),
    creation_date date not null default now(),
    account_activation_code varchar(10),
    password varchar(255)
);

-- Tabla de categorías de ingresos / gastos
create table income_or_expense_category (
    id bigint primary key generated always as identity,
    name varchar(50) not null unique,
    type varchar(10) check (type in ('income', 'expense')) not null,
    user_id bigint not null references users (id) on delete cascade
);

-- Tabla de subcategorías de ingresos / gastos
create table income_or_expense_subcategory (
    id bigint primary key generated always as identity,
    name varchar(50) not null unique,
    type varchar(10) check (type in ('income', 'expense')) not null,
    user_id bigint not null references users (id) on delete cascade,
    income_category_id bigint not null references income_or_expense_category (id) on delete cascade
);

-- Tabla de recurrencias
create table recurrence_details (
    id bigint primary key generated always as identity,
    recurrence_type varchar(10) check (recurrence_type in ('daily', 'weekly', 'monthly', 'yearly')) not null,
    frequency integer not null,
    end_date date,
    occurrences integer
);

-- Tabla de ingresos / gastos
create table income_or_expense (
    id bigint primary key generated always as identity,
    date date not null,
    amount numeric(15, 2) not null,
    category varchar(50),
    sub_category varchar(50),
    currency varchar(10) not null,
    type varchar(10) check (type in ('income', 'expense')) not null,
    notes text,
    user_id bigint not null references users (id) on delete cascade,
    recurrence_details_id bigint references recurrence_details(id) on delete cascade
);

-- Tabla de categorías de inversiones
create table investment_category (
    id bigint primary key generated always as identity,
    name varchar(50) not null unique,
    user_id bigint not null references users (id) on delete cascade
);

-- Tabla de subcategorías de inversiones
create table investment_subcategory (
    id bigint primary key generated always as identity,
    name varchar(50) not null unique,
    user_id bigint not null references users (id) on delete cascade,
    investment_category_id bigint not null references investment_category (id) on delete cascade
);

-- Tabla de inversiones
create table investment (
  id bigint primary key generated always as identity,
  purchase_date date not null,
  purchase_amount numeric(15, 2) not null,
  category varchar(50),
  subcategory varchar(50),
  notes text,
  sale_amount numeric(15, 2),
  sale_date date,
  user_id bigint not null references users (id) on delete cascade
);