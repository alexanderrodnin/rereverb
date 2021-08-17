create table if not exists user_table
(
    id uuid not null,
    email varchar,
    name varchar,
    password varchar
);