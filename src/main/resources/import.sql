INSERT INTO doctor (id, name) VALUES(1, '김철수');
INSERT INTO doctor (id, name) VALUES(2, '오현우');

INSERT INTO patient (id, name, birth_date) VALUES (1, '김민준', '1980-05-15');
INSERT INTO patient (id, name, birth_date) VALUES (2, '이서준', '1975-10-20');
INSERT INTO patient (id, name, birth_date) VALUES (3, '박예준', '1990-03-25');
INSERT INTO patient (id, name, birth_date) VALUES (4, '최도윤', '1985-12-10');
INSERT INTO patient (id, name, birth_date) VALUES (5, '정시우', '1978-07-30');

INSERT INTO consultation (id, patient_id, doctor_id, summary, tags, conversation, consultation_date_time) VALUES (1, 1, 1, '환자가 2주 전부터 허리 통증이 심해져 진료를 받았습니다. 통증은 앉아 있을 때 심하며, 무거운 물건을 들면서 시작된 것으로 보입니다. 선생님은 간단한 검사와 스트레칭, 운동을 처방하고, 통증이 지속될 경우 재진료를 권유했습니다.', '허리통증,정형외과,진료,스트레칭,자세', '선생님: 안녕하세요. 오늘은 어떤 증상으로 오셨나요?\n환자: 안녕하세요. 요즘 허리 통증이 심해서 왔어요.\n환자: 특히 앉아 있을 때 더 아파요.\n선생님: 언제부터 통증이 시작되셨나요?\n환자: 한 2주 전부터요. 갑자기 무거운 걸 들다가 시작된 것 같아요.\n선생님: 알겠습니다. 일단 간단한 검사를 해볼게요.\n선생님: 그리고 몇 가지 스트레칭과 운동도 알려드릴게요.\n환자: 네. 감사합니다.\n선생님: 치료 후에도 통증이 지속되면 다시 방문해주세요.\n환자: 네. 알겠습니다.', '2025-06-08T10:54:43.269446');

ALTER TABLE doctor ALTER COLUMN id RESTART WITH 3;
ALTER TABLE patient ALTER COLUMN id RESTART WITH 6;
ALTER TABLE consultation ALTER COLUMN id RESTART WITH 2;