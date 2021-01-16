create table "user"
(
    user_id  uuid not null primary key,
    username text not null unique,
    password text not null
);

create table credential
(
    credential_id uuid not null primary key,
    name          text not null,
    owner         text not null
);

create table user__credential
(
    credential_id uuid not null references credential,
    user_id       uuid not null references "user"
);

create table access_log
(
    access_log_entry_id uuid      not null primary key,
    ip                  text      not null,
    user_id             uuid      not null references "user",
    login_time          timestamp not null
);