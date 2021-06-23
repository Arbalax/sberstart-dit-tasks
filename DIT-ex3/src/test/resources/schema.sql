drop table if exists message;

create table message
(
    queue_id serial               not null
        constraint data_pk
        primary key,
    id       integer              not null,
    msg      varchar(4000),
    in_queue boolean default true not null,
    modified boolean default false
);

alter table message
    owner to postgres;

insert into message (id, msg, in_queue, modified)  values (777, '777 Message', true, false);
insert into message (id, msg, in_queue, modified)  values (888, '888 Message', true, false);