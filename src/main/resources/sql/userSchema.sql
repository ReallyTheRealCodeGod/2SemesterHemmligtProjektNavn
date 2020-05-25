use autocamper_db;

create table users(
    username varchar(50) not null primary key,
    password varchar(50) not null,
    enabled TINYINT not null default 1
);

create table authorities (
    username varchar(50) not null,
    authority varchar(50) not null,
    foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);