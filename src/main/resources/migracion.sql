create table users (
  id bigint primary key generated always as identity,
  name text,
  email text not null unique,
  surnames text,
  creation_date date not null default now(),
  account_activation_code text,
  password varchar(255)
);

create table expense_categories (
  id bigint primary key generated always as identity,
  code text not null unique,
  name text not null,
  user_id bigint not null references users (id) on delete cascade
);

create table expense_subcategories (
  id bigint primary key generated always as identity,
  code text not null unique,
  name text not null,
  user_id bigint not null references users (id) on delete cascade,
  expense_category_id bigint not null references expense_categories (id) on delete cascade
);

create table income_categories (
  id bigint primary key generated always as identity,
  code text not null unique,
  name text not null,
  user_id bigint not null references users (id) on delete cascade
);

create table income_subcategories (
  id bigint primary key generated always as identity,
  code text not null unique,
  name text not null,
  user_id bigint not null references users (id) on delete cascade,
  income_category_id bigint not null references income_categories (id) on delete cascade
);

create table investment_categories (
  id bigint primary key generated always as identity,
  code text not null unique,
  name text not null,
  user_id bigint not null references users (id) on delete cascade
);

create table investment_subcategories (
  id bigint primary key generated always as identity,
  code text not null unique,
  name text not null,
  user_id bigint not null references users (id) on delete cascade,
  investment_category_id bigint not null references investment_categories (id) on delete cascade
);

create table expenses (
  id bigint primary key generated always as identity,
  date date not null,
  amount numeric(10, 2) not null,
  category_description text,
  subcategory_description text,
  notes text,
  user_id bigint not null references users (id) on delete cascade
);

create table incomes (
  id bigint primary key generated always as identity,
  date date not null,
  amount numeric(10, 2) not null,
  category_description text,
  subcategory_description text,
  notes text,
  user_id bigint not null references users (id) on delete cascade
);

create table investments (
  id bigint primary key generated always as identity,
  purchase_date date not null,
  purchase_amount numeric(10, 2) not null,
  category_description text,
  subcategory_description text,
  notes text,
  sale_amount numeric(10, 2),
  sale_date date,
  user_id bigint not null references users (id) on delete cascade
);