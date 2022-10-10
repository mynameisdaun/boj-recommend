drop table if exists recommend;
drop table if exists assignment;
drop table if exists problem_tag;
drop table if exists problem;
drop table if exists tag;
drop table if exists token;
drop table if exists member;

/* 22.08.17 회원 테이블 */
CREATE TABLE member
(
    id            int auto_increment  not null comment '회원 구분자(seq)',
    email         varchar(100) unique not null comment '회원 email',
    password      varchar(100) comment '회원 비밀번호 추후 수정 22.08.16',
    nickname      varchar(20)         not null comment '회원 닉네임',
    tier          int                 not null comment '회원 백준 티어',
    social_type   char(1)             not null comment '회원가입 시 사용한 Social Portal, W: 자체회원, K: 카카오 / G: 구글 / N : 네이버',
    login_count   int                 not null default 0,
    last_login_at datetime            not null default current_timestamp comment '마지막 로그인 일시',
    created_at    datetime            not null default current_timestamp comment '데이터 생성일시',
    updated_at    datetime            not null default current_timestamp comment '데이터 수정일시',
    primary key (id)
) comment '회원';

/* 22.08.18 토큰 테이블 */
CREATE TABLE token
(
    member_email                      varchar(100) unique not null comment '회원 email',
    access_token                      varchar(400) comment 'Api 액세스 토큰',
    access_token_expired_date         datetime comment 'Api 액세스 토큰 만료일시',
    refresh_token                     varchar(400) comment 'Api 리프레쉬 토큰',
    refresh_token_expired_date        datetime comment 'Api 리프레쉬 토큰 만료일시',
    member_social_type                char(1)             not null comment '회원가입 시 사용한 Social Portal, W: 자체회원, K: 카카오 / G: 구글 / N : 네이버',
    social_access_token               varchar(400) comment 'Social에서 발급 받은 액세스 토큰',
    social_access_token_expired_date  datetime comment 'Social 액세스 토큰 만료일시',
    social_refresh_token              varchar(400) comment 'Social에서 발급 받은 리프레쉬 토큰',
    social_refresh_token_expired_date datetime comment 'Social에서 발급 받은 리프레쉬 토큰 만료일시',
    created_at                        datetime            not null default current_timestamp comment '데이터 생성일시',
    updated_at                        datetime            not null default current_timestamp comment '데이터 수정일시',
    PRIMARY KEY (member_email),
    FOREIGN KEY (member_email) REFERENCES member (email)
) comment '토큰';

/* 22.10.03 문제 */
create table problem
(
    id                  int primary key not null comment '문제 번호',
    title               varchar(100)    not null comment '문제 명',
    url                 varchar(300)    not null comment '문제 url',
    tier                int comment '문제 티어',
    accepted_user_count int comment '푼 사람 수(BOJ 기준)',
    recommended_count   int                      default 0 comment '추천 된 횟수',
    created_at          datetime        not null default current_timestamp comment '데이터 생성일시',
    updated_at          datetime        not null default current_timestamp comment '데이터 수정일시'
) comment '문제';

/* 22.10.03 태그 */
create table tag
(
    id         int primary key not null comment '태그 번호',
    tag_key    varchar(100)    not null comment '태그 key',
    title      varchar(100)    not null comment '태그 이름',
    created_at datetime        not null default current_timestamp comment '데이터 생성일시',
    updated_at datetime        not null default current_timestamp comment '데이터 수정일시'
) comment '태그';

/* 22.10.03 문제_태그 */
create table problem_tag
(
    pk         varchar(50) primary key not null default '' comment '문제_태그 매핑 key(composite, problem_id-tag-id) 형식',
    problem_id int comment '문제 번호',
    tag_id     int comment '태그 번호',
    FOREIGN KEY (problem_id) REFERENCES problem (id),
    FOREIGN KEY (tag_id) REFERENCES tag (id)
) comment '문제_태그';

/* 22.10.03 과제 */
create table assignment
(
    id                 int          not null AUTO_INCREMENT PRIMARY KEY comment '과제 구분자(seq)',
    problem_id         int          not null comment '문제 id',
    assign_from        varchar(100) not null comment '과제를 만든 회원 email',
    assign_to          varchar(100) not null comment '과제가 할당된 회원 email',
    start_date_time    datetime     not null comment '과제 시작 시간',
    end_date_time      datetime     not null comment '과제 종료 기한',
    open_yn            char(1)      not null default 'N' comment '과제 열람 여부',
    open_date_time     datetime comment '과제 열람 일시',
    complete_yn        char(1)      not null default 'N' comment '과제 제출 여부',
    complete_date_time datetime comment '과제 제출 일시',
    created_at         datetime     not null default current_timestamp comment '데이터 생성일시',
    updated_at         datetime     not null default current_timestamp comment '데이터 수정일시',
    FOREIGN KEY (problem_id) REFERENCES problem (id),
    FOREIGN KEY (assign_from) REFERENCES member (email),
    FOREIGN KEY (assign_to) REFERENCES member (email)
) comment '과제 상세 정보';

/* 22.10.06 문제 추천 */
create table recommend
(
    id                int          not null auto_increment comment '문제 추천 이력 history',
    problem_id        int          not null comment '문제 id',
    email             varchar(100) not null comment '회원 email',
    recommended_count int          not null default 1 comment '문제가 회원에게 추천된 횟수',
    choose_yn         varchar(1)   not null default 'N' comment '회원 선택 여부',
    choose_date_time  datetime     not null default current_timestamp comment '선택 일시',
    created_at        datetime     not null default current_timestamp comment '데이터 생성일시',
    updated_at        datetime     not null default current_timestamp comment '데이터 수정일시',
    PRIMARY KEY (id),
    FOREIGN KEY (problem_id) REFERENCES problem (id),
    FOREIGN KEY (email) REFERENCES member (email)
) comment '문제 추천';



