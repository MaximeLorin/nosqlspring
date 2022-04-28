CREATE EXTENSION IF NOT EXISTS pg_trgm;

create table if not exists users(
    id char(36) primary key,
    email text not null unique,
    password text not null,
    username text not null unique
);

create table if not exists articles(
    id char(36) primary key,
    image text not null,
    title text not null,
    summary text not null,
    content text not null,
    draft boolean not null,
    user_id char(36) REFERENCES users(id)
);
