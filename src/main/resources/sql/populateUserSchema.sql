insert into users(username, password, enabled)
values('admin', 'pass', 1);

insert into users(username, password, enabled)
values('user', 'pass', 1);

INSERT INTO authorities (username, authority)
values('admin', 'ROLE_ADMIN');

INSERT INTO authorities (username, authority)
values('user', 'ROLE_USER');