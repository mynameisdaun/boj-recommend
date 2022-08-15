/* 22.08.16 회원 테이블 */
CREATE TABLE member (
                        member_id int auto_increment not null comment '회원 구분자(seq)',
                        member_email varchar(100) unique not null comment '회원 email',
                        member_password varchar(100) comment '회원 비밀번호 추후 수정 22.08.16',
                        member_nickname varchar(20) not null comment '회원 닉네임',
                        member_social_type char(1) comment '회원가입 시 사용한 Social Portal, W: 자체회원, K: 카카오 / G: 구글 / N : 네이버',
                        created_at datetime not null default current_timestamp comment  '데이터 생성일시',
                        updated_at datetime not null default current_timestamp comment  '데이터 수정일시',
                        primary key(member_id)
) comment '회원';


