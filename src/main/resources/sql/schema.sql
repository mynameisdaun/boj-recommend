/* 22.08.17 회원 테이블 */

drop table if exists quiz;
drop table if exists assignment_detail;
drop table if exists assignment;
drop table if exists member_workbook_mapping;
drop table if exists chapter_word_mapping;
drop table if exists chapter;
drop table if exists workbook;
drop table if exists word;
drop table if exists token;
drop table if exists member;


CREATE TABLE member
(
    id          int auto_increment  not null comment '회원 구분자(seq)',
    email       varchar(100) unique not null comment '회원 email',
    password    varchar(100) comment '회원 비밀번호 추후 수정 22.08.16',
    nickname    varchar(20)         not null comment '회원 닉네임',
    social_type char(1)             not null comment '회원가입 시 사용한 Social Portal, W: 자체회원, K: 카카오 / G: 구글 / N : 네이버',
    login_count int                 not null default 1,
    last_login_at datetime          not null default current_timestamp comment '마지막 로그인 일시',
    created_at  datetime            not null default current_timestamp comment '데이터 생성일시',
    updated_at  datetime            not null default current_timestamp comment '데이터 수정일시',
    primary key (id)
) comment '회원';

/* 22.08.18 토큰 테이블 */

CREATE TABLE token
(
    member_email                      varchar(100) unique not null comment '회원 email',
    access_token                      varchar(255) comment 'Api 액세스 토큰',
    access_token_expired_date         datetime comment 'Api 액세스 토큰 만료일시',
    refresh_token                     varchar(255) comment 'Api 리프레쉬 토큰',
    refresh_token_expired_date        datetime comment 'Api 리프레쉬 토큰 만료일시',
    member_social_type                char(1)             not null comment '회원가입 시 사용한 Social Portal, W: 자체회원, K: 카카오 / G: 구글 / N : 네이버',
    social_access_token               varchar(255) comment 'Social에서 발급 받은 액세스 토큰',
    social_access_token_expired_date  datetime comment 'Social 액세스 토큰 만료일시',
    social_refresh_token              varchar(255) comment 'Social에서 발급 받은 리프레쉬 토큰',
    social_refresh_token_expired_date datetime comment 'Social에서 발급 받은 리프레쉬 토큰 만료일시',
    created_at                        datetime            not null default current_timestamp comment '데이터 생성일시',
    updated_at                        datetime            not null default current_timestamp comment '데이터 수정일시',
    PRIMARY KEY (member_email),
    FOREIGN KEY (member_email) REFERENCES member (email)
) comment '토큰';

/* 22.08.18 단어장 테이블 */

CREATE TABLE workbook
(
    id              int auto_increment not null comment '단어장 일련번호(seq)',
    title           varchar(100)       not null comment '단어장 제목',
    author          varchar(100)       not null comment '단어장 저자',
    description     text               not null comment '단어장 설명',
    cover_image_url text               not null comment '단어장 커버이미지 URL 주소',
    created_at      datetime           not null default current_timestamp comment '데이터 생성일시',
    updated_at      datetime           not null default current_timestamp comment '데이터 수정일시',
    PRIMARY KEY (id)
) comment '단어장';

/* 22.08.18 챕터 테이블 */

CREATE TABLE chapter
(
    id          int auto_increment not null comment '챕터 일련번호(seq)',
    title       varchar(100)       not null comment '챕터 제목',
    workbook_id int                not null comment '단어장 일련번호(seq)',
    created_at  datetime           not null default current_timestamp comment '데이터 생성일시',
    updated_at  datetime           not null default current_timestamp comment '데이터 수정일시',
    PRIMARY KEY (id),
    FOREIGN KEY (workbook_id) REFERENCES workbook (id)
) comment '챕터';

/* 22.08.18 단어 테이블 */

CREATE TABLE word
(
    id         int auto_increment not null comment '아이디 일련번호(seq)',
    english    varchar(100)       not null comment '영어 표기',
    korean     varchar(100)       not null comment '한글 표기',
    created_by varchar(100)       not null comment '생성자',
    created_at datetime           not null default current_timestamp comment '데이터 생성일시',
    updated_at datetime           not null default current_timestamp comment '데이터 수정일시',
    PRIMARY KEY (id)
) comment '단어';

/*22.08.18 챕터 단어 매핑 테이블*/

create table chapter_word_mapping
(
    id         int      NOT NULL AUTO_INCREMENT PRIMARY KEY comment '챕터 단어 매핑 구분자(seq)',
    chapter_id int      not null comment '챕터 구분자(seq)',
    word_id    int      not null comment '단어 구분자(seq)',
    created_at datetime not null default current_timestamp comment '데이터 생성일시',
    updated_at datetime not null default current_timestamp comment '데이터 수정일시',
    FOREIGN KEY (word_id) REFERENCES word (id),
    FOREIGN KEY (chapter_id) REFERENCES chapter (id)
) comment '챕터 단어 매핑';


/* 22.08.22 회원-단어장 매핑 */

create table member_workbook_mapping
(
    id          int      NOT NULL AUTO_INCREMENT PRIMARY KEY comment '회원 - 단어장 매핑 구분자(seq)',
    member_id   int      not null comment '회원 구분자(seq)',
    workbook_id int      not null comment '단어장 구분자(seq)',
    created_at  datetime not null default current_timestamp comment '데이터 생성일시',
    updated_at  datetime not null default current_timestamp comment '데이터 수정일시',
    FOREIGN KEY (member_id) REFERENCES member (id),
    FOREIGN KEY (workbook_id) REFERENCES workbook (id)
) comment '회원 단어장 매핑';

/* 22.08.22 과제 */

create table assignment
(
    id          int          not null AUTO_INCREMENT PRIMARY KEY comment '과제 구분자(seq)',
    workbook_id int          not null comment '단어장 구분자(seq)',
    assign_from varchar(100) not null comment '과제를 만든 회원 email',
    assign_to   varchar(100) not null comment '과제가 할당된 회원 email',
    created_at  datetime     not null default current_timestamp comment '데이터 생성일시',
    updated_at  datetime     not null default current_timestamp comment '데이터 수정일시',
    FOREIGN KEY (workbook_id) REFERENCES workbook (id),
    FOREIGN KEY (assign_from) REFERENCES member (email),
    FOREIGN KEY (assign_to) REFERENCES member (email)
) comment '과제';

/* 22.08.22 과제 상세 정보 */

create table assignment_detail
(
    id                 int      not null AUTO_INCREMENT PRIMARY KEY comment '과제 상세정보 구분자(seq)',
    assignment_id      int      not null comment '과제 구분자(seq)',
    chapter_id         int      not null comment '챕터 구분자(seq)',
    start_date_time    datetime not null comment '과제 시작일시',
    end_date_time      datetime not null comment '과제 종료일시',
    open_yn            char(1)  not null default 'N' comment '과제 열람 여부',
    open_date_time     datetime comment '과제 열람 일시',
    complete_yn        char(1)  not null default 'N' comment '과제 제출 여부',
    complete_date_time datetime comment '과제 제출 일시',
    created_at         datetime not null default current_timestamp comment '데이터 생성일시',
    updated_at         datetime not null default current_timestamp comment '데이터 수정일시',
    FOREIGN KEY (assignment_id) REFERENCES assignment (id),
    FOREIGN KEY (chapter_id) REFERENCES chapter (id)
) comment '과제 상세 정보';

create table quiz
(
    id          int auto_increment primary key not null comment '퀴즈 구분자(seq)',
    chapter_id  int                            not null comment '챕터 id',
    word_id     int                            not null comment '단어 id',
    options     text                           not null comment '선택지 (구분자,)',
    quiz_type   varchar(50)                    not null comment '퀴즈 타입',
    quiz_status varchar(50)                    not null comment '퀴즈 제출 상태',
    submission  varchar(100) comment '제출',
    created_at  datetime                       not null default current_timestamp comment '데이터 생성일시',
    updated_at  datetime                       not null default current_timestamp comment '데이터 수정일시',
    FOREIGN KEY (word_id) REFERENCES word (id),
    FOREIGN KEY (chapter_id) REFERENCES chapter (id)
) comment '퀴즈';



