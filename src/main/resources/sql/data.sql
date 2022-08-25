/* 회원*/

INSERT INTO member (email, password, nickname, social_type)
VALUES ('tester1@weword.com', 'fake-password', '테스터', 'W');

INSERT INTO member(email, password, nickname, social_type)
VALUES ('tester2@weword.com', 'fake-password', '테스터2', 'W');

INSERT INTO member(email, password, nickname, social_type)
VALUES ('tester3@weword.com', 'fake-password', '테스터3', 'W');
/* 토큰 */
INSERT INTO token (member_email, access_token, access_token_expired_date, refresh_token, refresh_token_expired_date,
                   member_social_type, social_access_token, social_access_token_expired_date, social_refresh_token,
                   social_refresh_token_expired_date)
VALUES ('tester1@weword.com',
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
INSERT INTO word (english, korean)
VALUES ('hi', '안녕');
INSERT INTO word (english, korean)
VALUES ('assignment', '안녕하세요');
INSERT INTO word (english, korean)
VALUES ('name', '이름');
INSERT INTO word (english, korean)
VALUES ('you', '너');
INSERT INTO word (english, korean)
VALUES ('love', '사랑하다');
INSERT INTO word (english, korean)
VALUES ('get', '얻다');
INSERT INTO word (english, korean)
VALUES ('post', '게시하다');
INSERT INTO word (english, korean)
VALUES ('put', '놓다');
INSERT INTO word (english, korean)
VALUES ('delete', '삭제하다');
INSERT INTO word (english, korean)
VALUES ('option', '선택');

/* 단어장 */
INSERT INTO workbook (title, author, description, cover_image_url)
VALUES ('재밌는 단어장', '운영자', '아주 친절한 설명', 'https://weword.com/fake-image');

/* 챕터 */
INSERT INTO chapter (title, workbook_id)
VALUES ('Day 1', '1');
INSERT INTO chapter (title, workbook_id)
VALUES ('Day 2', '1');
INSERT INTO chapter (title, workbook_id)
VALUES ('Day 3', '1');

/* 챕터_단어_매핑 */
INSERT INTO chapter_word_mapping (chapter_id, word_id)
VALUES ('1', '1');
INSERT INTO chapter_word_mapping (chapter_id, word_id)
VALUES ('1', '2');
INSERT INTO chapter_word_mapping (chapter_id, word_id)
VALUES ('1', '3');
INSERT INTO chapter_word_mapping (chapter_id, word_id)
VALUES ('1', '4');
INSERT INTO chapter_word_mapping (chapter_id, word_id)
VALUES ('2', '5');
INSERT INTO chapter_word_mapping (chapter_id, word_id)
VALUES ('2', '6');
INSERT INTO chapter_word_mapping (chapter_id, word_id)
VALUES ('2', '7');
INSERT INTO chapter_word_mapping (chapter_id, word_id)
VALUES ('2', '8');
INSERT INTO chapter_word_mapping (chapter_id, word_id)
VALUES ('3', '9');
INSERT INTO chapter_word_mapping (chapter_id, word_id)
VALUES ('3', '10');
INSERT INTO chapter_word_mapping (chapter_id, word_id)
VALUES ('3', '11');
INSERT INTO chapter_word_mapping (chapter_id, word_id)
VALUES ('3', '12');

/* 과제 */
INSERT INTO assignment (workbook_id, assign_from, assign_to)
VALUES ('1', 'tester1@weword.com', 'tester2@weword.com');

/* 과제 상세 */
/* TODO: quiz 가짜로 만들어둔것 다 고쳐야 한다*/
/*open complete*/
INSERT INTO assignment_detail(assignment_id, chapter_id, start_date_time, end_date_time, open_yn, open_date_time,
                              complete_yn, complete_date_time, quiz, submission)
VALUES (1, 1, '2022-08-20 15:43:51', '2023-08-21 15:43:51', 'Y', '2022-08-21 15:43:51', 'Y', '2022-08-21 16:43:51',
        'fake-quiz', 'fake-submission');
/*open un complete*/
INSERT INTO assignment_detail(assignment_id, chapter_id, start_date_time, end_date_time, open_yn, open_date_time, quiz)
VALUES (1, 2, '2022-08-20 15:43:51', '2023-08-21 15:43:51', 'Y', '2022-08-21 15:43:51', 'fake-quiz');
/*un open un complete*/
INSERT INTO assignment_detail(assignment_id, chapter_id, start_date_time, end_date_time, quiz)
VALUES (1, 3, '2022-08-20 15:43:51', '2023-08-21 15:43:51', 'fake-quiz');
