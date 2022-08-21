/* 회원*/

INSERT INTO member (email, password, nickname, social_type)
VALUES ('tester@weword.com', 'fake-password', '테스터', 'W');

INSERT INTO member(email, password, nickname, social_type)
VALUES ('another-tester@weword.com', 'fake-password', '테스터2', 'W');

/* 토큰 */
INSERT INTO token (member_email, access_token, access_token_expired_date, refresh_token, refresh_token_expired_date,
                   member_social_type, social_access_token, social_access_token_expired_date, social_refresh_token,
                   social_refresh_token_expired_date)
VALUES ('tester@weword.com',
        'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3b3JkIiwiaWF0IjoxNjYwOTc2MDMxLCJleHAiOjE2NjA5Nzc4MzEsImVtYWlsIjoiaXJhZGl0QG5hdmVyLmNvbSJ9.Bs9nDgglcyg_IQCcsLQVH48RW1t1-w8QYqkLJissNuU',
        '2022-08-20 15:43:51',
        'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3b3JkIiwiaWF0IjoxNjYwOTc2MDMxLCJleHAiOjE2NjEwMzY1MTEsImVtYWlsIjoiaXJhZGl0QG5hdmVyLmNvbSJ9.uCNJxT-PuD2FNLZcplTULRqu1XO2YEWX_0--35quTGU',
        '2022-08-21 08:01:51', 'W', '7MP8EHWXFLzHxQsi1YNMXs3KVb1paQBpEPLwZb6QCj1zFwAAAYK54uKs', '2022-08-20 15:13:51',
        'oOdpDpTD3juuQy7ZVuEBnYkDH3cWJmM_lUie3eUcCj1zFwAAAYK54uKq', '2022-10-19 15:13:50');

/* 단어 */
INSERT INTO word (english, korean)
VALUES ('we', '우리');

INSERT INTO word (english, korean)
VALUES ('word', '단어');

/* 단어장 */
INSERT INTO workbook (title, author, description, cover_image_url)
VALUES ('재밌는 단어장', '운영자', '아주 친절한 설명', 'https://weword.com/fake-image');

/* 챕터 */
INSERT INTO chapter (title, workbook_id)
VALUES ('Day 1', '1');



