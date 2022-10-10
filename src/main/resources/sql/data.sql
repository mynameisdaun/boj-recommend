/* 회원*/

INSERT INTO member (email, password, name, tier, social_type)
VALUES ('tester1@weword.com', '$2a$10$j.X5k/3SVnZI/VxSFkjw..n2cc5auOyWYp2z.kksSU0iYCgHcwfyS', '테스터', 15, 'W');

INSERT INTO member(email, password, name, tier, social_type)
VALUES ('daun9870jung', '$2a$10$j.X5k/3SVnZI/VxSFkjw..n2cc5auOyWYp2z.kksSU0iYCgHcwfyS', '테스터2', 15, 'W');

INSERT INTO member(email, password, name, tier, social_type)
VALUES ('tester3@weword.com', '$2a$10$j.X5k/3SVnZI/VxSFkjw..n2cc5auOyWYp2z.kksSU0iYCgHcwfyS', '테스터3', 15, 'W');

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

insert into tag (id, tag_key, title)
values (175, 'data_structures', '자료 구조');

insert into tag (id, tag_key, title)
values (33, 'greedy', '그리디 알고리즘');

insert into tag (id, tag_key, title)
values (71, 'stack', '스택');

insert into tag (id, tag_key, title)
values (158, 'string', '문자열');

insert into tag (id, tag_key, title)
values (5, 'backtracking', '백트래킹');

insert into tag (id, tag_key, title)
values (141, 'simulation', '시뮬레이션');

insert into tag (id, tag_key, title)
values (102, 'implementation', '구현');

insert into tag (id, tag_key, title)
values (11, 'graph_traversal', '그래프 탐색');

insert into tag (id, tag_key, title)
values (7, 'graphs', '그래프 이론');

insert into tag (id, tag_key, title)
values (126, 'bfs', '너비 우선 탐색');

insert into problem (id, title, url, tier, accepted_user_count, recommended_count)
values (16120, 'PPAP', 'https://www.acmicpc.net/problem/16120', 12, 1342, 0);

insert into problem (id, title, url, tier, accepted_user_count, recommended_count)
values (2580, '스도쿠', 'https://www.acmicpc.net/problem/2580', 12, 13583, 0);

insert into problem (id, title, url, tier, accepted_user_count, recommended_count)
values (16236, '아기 상어', 'https://www.acmicpc.net/problem/16236', 13, 13216, 0);

insert into problem (id, title, url, tier, accepted_user_count, recommended_count)
values (19237, '어른 상어', 'https://www.acmicpc.net/problem/19237', 14, 2723, 0);

insert into problem (id, title, url, tier, accepted_user_count, recommended_count)
values (15685, '드래곤 커브', 'https://www.acmicpc.net/problem/15685', 12, 7772, 0);

insert into problem_tag(pk, problem_id, tag_id)
values ('16120-175', 16120, 175);
insert into problem_tag(pk, problem_id, tag_id)
values ('16120-33', 16120, 33);
insert into problem_tag(pk, problem_id, tag_id)
values ('16120-71', 16120, 71);
insert into problem_tag(pk, problem_id, tag_id)
values ('16120-158', 16120, 158);
insert into problem_tag(pk, problem_id, tag_id)
values ('2580-5', 2580, 5);
insert into problem_tag(pk, problem_id, tag_id)
values ('16236-126', 16236, 126);
insert into problem_tag(pk, problem_id, tag_id)
values ('16236-7', 16236, 7);
insert into problem_tag(pk, problem_id, tag_id)
values ('16236-11', 16236, 11);
insert into problem_tag(pk, problem_id, tag_id)
values ('16236-102', 16236, 102);
insert into problem_tag(pk, problem_id, tag_id)
values ('16236-141', 16236, 141);
insert into problem_tag(pk, problem_id, tag_id)
values ('19237-102', 19237, 102);
insert into problem_tag(pk, problem_id, tag_id)
values ('19237-141', 19237, 141);
