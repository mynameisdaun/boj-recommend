SET MODE MYSQL;

drop table if exists recommend;
drop table if exists assignment;
drop table if exists hibernate_sequence;
drop table if exists problem_tag;
drop table if exists problem;
drop table if exists study_member;
drop table if exists study;
drop table if exists tag;
drop table if exists member;

create table assignment
(
    assignment_id       varbinary(16)                             not null,
    problem_id          integer                                   not null,
    member_id           varbinary(16)                             not null,
    complete            bit             default 0                 not null,
    complete_at         datetime(6)                                       ,
    created_at          timestamp       default current_timestamp not null,
    updated_at          timestamp       default current_timestamp not null,
    deleted             bit             default 0                 not null,
    primary key (assignment_id)
) engine = InnoDB ;


create table hibernate_sequence (
       next_val bigint
    ) engine=InnoDB;

insert into hibernate_sequence values ( 1 );

    create table member (
       member_id varbinary(16) not null,
        created_at timestamp default current_timestamp not null,
        deleted bit default 0,
        updated_at timestamp default current_timestamp not null,
        email varchar(255) not null,
        last_login_at datetime(6),
        login_count int default 0 not null,
        name varchar(255),
        password varchar(255),
        social_type varchar(255),
        level integer not null,
        rate varchar(255) not null,
        role varchar(12) not null,
        primary key (member_id)
    ) engine=InnoDB;

    create table problem (
       problem_id integer not null,
        created_at timestamp default current_timestamp not null,
        deleted bit default 0,
        updated_at timestamp default current_timestamp not null,
        accepted_user_count int default 0 not null,
        level integer not null,
        rate varchar(255) not null,
        title varchar(255) not null,
        url varchar(255) not null,
        primary key (problem_id)
    ) engine=InnoDB;

    create table problem_tag (
       id integer not null,
        created_at timestamp default current_timestamp not null,
        deleted bit default 0,
        updated_at timestamp default current_timestamp not null,
        problem_id integer,
        tag_id integer,
        primary key (id)
    ) engine=InnoDB;

    create table recommend (
       recommend_id varbinary(16) not null,
        created_at timestamp default current_timestamp not null,
        deleted bit default 0,
        updated_at timestamp default current_timestamp not null,
        recommend_type varchar(255),
        member_id varbinary(16),
        problem_id integer,
        primary key (recommend_id)
    ) engine=InnoDB;

    create table study (
       study_id varbinary(16) not null,
        created_at timestamp default current_timestamp not null,
        deleted bit default 0,
        updated_at timestamp default current_timestamp not null,
        hash varbinary(64) not null,
        name varchar(255),
        member_id varbinary(16),
        primary key (study_id)
    ) engine=InnoDB;

    create table study_member (
       id bigint not null,
        created_at timestamp default current_timestamp not null,
        deleted bit default 0,
        updated_at timestamp default current_timestamp not null,
        member_id varbinary(16),
        study_id varbinary(16),
        primary key (id)
    ) engine=InnoDB;

    create table reference
(
    reference_id varbinary(16)                       not null,
    created_at   timestamp default current_timestamp not null,
    deleted      bit       default 0,
    updated_at   timestamp default current_timestamp not null,
    problem_id   integer                             not null,
    url          varchar(500)                        not null,
    allowed      bit       default 0,
    primary key (reference_id)
) engine=InnoDB;

    create table tag (
       tag_id integer not null,
        created_at timestamp default current_timestamp not null,
        deleted bit default 0,
        updated_at timestamp default current_timestamp not null,
        tag_key varchar(255) not null,
        title varchar(255) not null,
        primary key (tag_id)
    ) engine=InnoDB;

    alter table member
        add constraint UK_mbmcqelty0fbrvxp1q58dn57t unique (email);

    alter table assignment
       add constraint FKj4f86psvg51boxt2et6f733v8
       foreign key (member_id)
       references member (member_id);

    alter table assignment
       add constraint FK3l3fel3j9r6lenjib2xswuu75
       foreign key (problem_id)
       references problem (problem_id);

    alter table problem_tag
       add constraint FKsbd0j3yjggts3lk19llwm0lwq
       foreign key (problem_id)
       references problem (problem_id);

    alter table problem_tag
       add constraint FKbme6cvwwregomxxbj1vb7f3nu
       foreign key (tag_id)
       references tag (tag_id);

    alter table recommend
       add constraint FKtp0goy0045lmlgnwfqhqlne6e
       foreign key (member_id)
       references member (member_id);

    alter table recommend
       add constraint FKq7rsqhvkd72tddwj8lt3qbg15
       foreign key (problem_id)
       references problem (problem_id);

    alter table study
       add constraint FKf561xlrfo0qtpl3vwkxiw4cs8
       foreign key (member_id)
       references member (member_id);

    alter table study_member
       add constraint FKf2jvkah9v99o0ujl7ilpshptk
       foreign key (member_id)
       references member (member_id);

    alter table study_member
       add constraint FKxu4jds4ab0mfyrvdxsu60iut
       foreign key (study_id)
       references study (study_id);

   alter table reference
        add constraint UK_reference unique (reference_id, problem_id);
