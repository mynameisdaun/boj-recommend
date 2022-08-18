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


