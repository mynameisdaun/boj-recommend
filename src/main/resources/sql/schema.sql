/* 22.08.17 회원 테이블 */
CREATE TABLE member
(
    id          int auto_increment  not null comment '회원 구분자(seq)',
    email       varchar(100) unique not null comment '회원 email',
    password    varchar(100) comment '회원 비밀번호 추후 수정 22.08.16',
    nickname    varchar(20)         not null comment '회원 닉네임',
    social_type char(1)             not null comment '회원가입 시 사용한 Social Portal, W: 자체회원, K: 카카오 / G: 구글 / N : 네이버',
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
    member_social_type                char(1)  not null comment '회원가입 시 사용한 Social Portal, W: 자체회원, K: 카카오 / G: 구글 / N : 네이버',
    social_access_token               varchar(255) comment 'Social에서 발급 받은 액세스 토큰',
    social_access_token_expired_date  datetime comment 'Social 액세스 토큰 만료일시',
    social_refresh_token              varchar(255) comment 'Social에서 발급 받은 리프레쉬 토큰',
    social_refresh_token_expired_date datetime comment 'Social에서 발급 받은 리프레쉬 토큰 만료일시',
    created_at                        datetime not null default current_timestamp comment '데이터 생성일시',
    updated_at                        datetime not null default current_timestamp comment '데이터 수정일시',
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
    word_list   text               not null comment '단어 리스트 ,로 구분한다',
    created_at  datetime           not null default current_timestamp comment '데이터 생성일시',
    updated_at  datetime           not null default current_timestamp comment '데이터 수정일시',
    PRIMARY KEY (id),
    FOREIGN KEY (workbook_id) REFERENCES workbook (id)
) comment '챕터';

/* 22.08.18 단어 테이블 */
CREATE TABLE word
(
    id         int auto_increment not null comment '아이디 일련번호(seq)',
    english    varchar(100)       not null unique comment '영어 표기',
    korean     varchar(100)       not null comment '한글 표기',
    created_at datetime           not null default current_timestamp comment '데이터 생성일시',
    updated_at datetime           not null default current_timestamp comment '데이터 수정일시',
    PRIMARY KEY (id)
) comment '단어';


