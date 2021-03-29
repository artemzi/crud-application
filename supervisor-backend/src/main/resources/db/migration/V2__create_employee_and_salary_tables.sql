create table salary
(
    id    bigint auto_increment primary key,
    value bigint null
);

create table employee
(
    id        bigint auto_increment primary key,
    name      varchar(255) null,
    salary_id bigint       null,
    constraint EMPLOYEE_KEY
        foreign key (salary_id) references salary (id)
);
