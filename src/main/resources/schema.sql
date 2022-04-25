create table if not exists users(
    id char(36) primary key,
    email text not null unique,
    username text not null
);

create table if not exists articles(
    id char(36) primary key,
    title text not null,
    summary text not null,
    content text not null
);
