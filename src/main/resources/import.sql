/* member */
/*insert into member (member_id,email,last_login_at,name,password,social_type,level,rate) values (x'f1860abc2ea1411bbd4abaa44f0d5580','daun9870jung',sysdate(),'daun','$2a$10$j.X5k/3SVnZI/VxSFkjw..n2cc5auOyWYp2z.kksSU0iYCgHcwfyS','W',13,'g3');*/
insert into member (member_id,email,last_login_at,name,password,social_type,level,rate) values (x'f1860abc2ea1411bbd4abaa44f0d5580','daun9870jung',sysdate(),'정다운','$2a$10$j.X5k/3SVnZI/VxSFkjw..n2cc5auOyWYp2z.kksSU0iYCgHcwfyS','W',15,'g1');
insert into member (member_id,email,last_login_at,name,password,social_type,level,rate) values (x'c5ee925c3dbb4941b825021446f24446','pine98',sysdate(),'pine','$2a$10$j.X5k/3SVnZI/VxSFkjw..n2cc5auOyWYp2z.kksSU0iYCgHcwfyS','W',13,'g3');
insert into member (member_id,email,last_login_at,name,password,social_type,level,rate) values (x'625c6fc4145d408f8dd533c16ba26064','shp7724',sysdate(),'shp','$2a$10$j.X5k/3SVnZI/VxSFkjw..n2cc5auOyWYp2z.kksSU0iYCgHcwfyS','W',13,'g3');
insert into member (member_id,email,last_login_at,name,password,social_type,level,rate) values (x'4721ee722ff3417fade3acd0a804605b','baggomsoon96',sysdate(),'baggomsoon','$2a$10$j.X5k/3SVnZI/VxSFkjw..n2cc5auOyWYp2z.kksSU0iYCgHcwfyS','W',13,'g3');

/* tag */
insert into tag(tag_id, tag_key, title) values (33, 'greedy','그리디 알고리즘');
insert into tag(tag_id, tag_key, title) values (71, 'stack','스택');
insert into tag(tag_id, tag_key, title) values (158, 'string','문자열');
insert into tag(tag_id, tag_key, title) values (175, 'data_structures','자료 구조');
insert into tag(tag_id, tag_key, title) values (100, 'geometry','기하학');
insert into tag(tag_id, tag_key, title) values (124, 'math','수학');

/* problem */
insert into problem (problem_id, level, rate, title, url,accepted_user_count) values(16120, 12, 'g4', 'PPAP', 'https://www.acmicpc.net/problem/16120', 1379);
insert into problem (problem_id, level, rate, title, url,accepted_user_count) values(1002, 8, 's3', '터렛', 'https://www.acmicpc.net/problem/1002', 29321);

/* problem tag */
insert into problem_tag(id,problem_id, tag_id) values (300000,16120,33);
insert into problem_tag(id,problem_id, tag_id) values (300001,16120,71);
insert into problem_tag(id,problem_id, tag_id) values (300002,16120,158);
insert into problem_tag(id,problem_id, tag_id) values (300003,16120,175);
insert into problem_tag(id,problem_id, tag_id) values (300004,1002,100);
insert into problem_tag(id,problem_id, tag_id) values (300005,1002,124);

/* study and study member */
insert into study(study_id,hash,name,member_id) values (x'1ee72417fea22ff34a80dac3d075b046','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4','example_study',x'f1860abc2ea1411bbd4abaa44f0d5580');
insert into study_member(id,member_id,study_id) values (3000001,x'f1860abc2ea1411bbd4abaa44f0d5580',x'1ee72417fea22ff34a80dac3d075b046');
insert into study_member(id,member_id,study_id) values (3000002,x'c5ee925c3dbb4941b825021446f24446',x'1ee72417fea22ff34a80dac3d075b046');
insert into study_member(id,member_id,study_id) values (3000003,x'625c6fc4145d408f8dd533c16ba26064',x'1ee72417fea22ff34a80dac3d075b046');
insert into study_member(id,member_id,study_id) values (3000004,x'4721ee722ff3417fade3acd0a804605b',x'1ee72417fea22ff34a80dac3d075b046');

/* assignment */
insert into assignment(assignment_id, complete, member_id, problem_id) values(x'145d408f8dd5317fade3acd0a804605b',0,x'f1860abc2ea1411bbd4abaa44f0d5580',16120);
