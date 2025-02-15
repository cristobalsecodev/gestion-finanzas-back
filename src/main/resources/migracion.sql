-- Tabla de URLs de uso único / expirables
create table one_time_url (
    token text primary key,
    used boolean not null default false,
    expiration_time bigint,
    email text,
    type text,
    url text
);

create table currency_exchange_ratio (
    currency_code varchar(3) primary key,
    currency_name varchar(100) not null unique,
    exchange_rate_to_usd numeric(10, 4) not null,
)

-- Tabla de usuarios
create table users (
    id bigint primary key generated always as identity,
    name varchar(255),
    email text not null unique,
    surnames varchar(255),
    favorite_currency varchar(3) not null default 'USD',
    creation_date date not null default now(),
    account_activation_code varchar(10),
    password varchar(255)
);

-- Insertar usuario TEST
INSERT INTO users (name, email, surnames, favorite_currency, creation_date, account_activation_code, password)
VALUES ('Test', 'test@example.com', 'User', 'EUR', now(), null, '$2a$10$wHh/xs0uPZb7hA6BGxyHveRJh/s5htHK1QGcglhD8I9YV3T1Ow5vC');

-- Tabla de categorías de ingresos / gastos
create table income_or_expense_category (
    id bigint primary key generated always as identity,
    name varchar(30) not null unique,
    type varchar(10) check (type in ('income', 'expense')) not null,
    color varchar(7) not null,
    linked boolean default false,
    active boolean default true,
    user_id bigint not null references users (id) on delete cascade
);

-- Tabla de subcategorías de ingresos / gastos
create table income_or_expense_subcategory (
    id bigint primary key generated always as identity,
    name varchar(30) not null unique,
    type varchar(10) check (type in ('income', 'expense')) not null,
    linked boolean default false,
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
    currency varchar(10) not null,
    exchange_rate_to_usd numeric(10, 4) not null,
    type varchar(10) check (type in ('income', 'expense')) not null,
    notes varchar(150),
    user_id bigint not null references users (id) on delete cascade,
    recurrence_details_id bigint references recurrence_details(id) on delete cascade,
    category_id bigint references income_or_expense_category(id),
    subcategory_id bigint references income_or_expense_subcategory(id)
);
