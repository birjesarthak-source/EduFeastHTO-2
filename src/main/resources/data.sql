INSERT INTO departments (department_code, department_name)
SELECT 'CSE','Computer Science and Engineering' WHERE NOT EXISTS (SELECT 1 FROM departments WHERE department_code='CSE');
INSERT INTO departments (department_code, department_name)
SELECT 'ISE','Information Science and Engineering' WHERE NOT EXISTS (SELECT 1 FROM departments WHERE department_code='ISE');
INSERT INTO departments (department_code, department_name)
SELECT 'ECE','Electronics and Communication Engineering' WHERE NOT EXISTS (SELECT 1 FROM departments WHERE department_code='ECE');

INSERT INTO class_sections (class_name,semester,section,department_id,student_count)
SELECT 'CSE',5,'A',id,30 FROM departments WHERE department_code='CSE'
AND NOT EXISTS (SELECT 1 FROM class_sections WHERE class_name='CSE' AND semester=5 AND section='A');
INSERT INTO class_sections (class_name,semester,section,department_id,student_count)
SELECT 'ISE',5,'A',id,30 FROM departments WHERE department_code='ISE'
AND NOT EXISTS (SELECT 1 FROM class_sections WHERE class_name='ISE' AND semester=5 AND section='A');
INSERT INTO class_sections (class_name,semester,section,department_id,student_count)
SELECT 'ECE',5,'A',id,25 FROM departments WHERE department_code='ECE'
AND NOT EXISTS (SELECT 1 FROM class_sections WHERE class_name='ECE' AND semester=5 AND section='A');

INSERT INTO classrooms (room_number,building,floor,capacity,room_type,availability_status)
SELECT '101','Main Block',1,40,'CLASSROOM','AVAILABLE' WHERE NOT EXISTS (SELECT 1 FROM classrooms WHERE room_number='101');
INSERT INTO classrooms (room_number,building,floor,capacity,room_type,availability_status)
SELECT '102','Main Block',1,40,'CLASSROOM','AVAILABLE' WHERE NOT EXISTS (SELECT 1 FROM classrooms WHERE room_number='102');
INSERT INTO classrooms (room_number,building,floor,capacity,room_type,availability_status)
SELECT '103','Main Block',1,50,'CLASSROOM','AVAILABLE' WHERE NOT EXISTS (SELECT 1 FROM classrooms WHERE room_number='103');
INSERT INTO classrooms (room_number,building,floor,capacity,room_type,availability_status)
SELECT '104','Main Block',1,60,'CLASSROOM','AVAILABLE' WHERE NOT EXISTS (SELECT 1 FROM classrooms WHERE room_number='104');
INSERT INTO classrooms (room_number,building,floor,capacity,room_type,availability_status)
SELECT '201','Science Block',2,30,'CLASSROOM','AVAILABLE' WHERE NOT EXISTS (SELECT 1 FROM classrooms WHERE room_number='201');
INSERT INTO classrooms (room_number,building,floor,capacity,room_type,availability_status)
SELECT '202','Science Block',2,50,'LAB','AVAILABLE' WHERE NOT EXISTS (SELECT 1 FROM classrooms WHERE room_number='202');
INSERT INTO classrooms (room_number,building,floor,capacity,room_type,availability_status)
SELECT '203','Science Block',2,60,'CLASSROOM','AVAILABLE' WHERE NOT EXISTS (SELECT 1 FROM classrooms WHERE room_number='203');
INSERT INTO classrooms (room_number,building,floor,capacity,room_type,availability_status)
SELECT '204','Main Block',2,80,'SEMINAR_HALL','AVAILABLE' WHERE NOT EXISTS (SELECT 1 FROM classrooms WHERE room_number='204');

INSERT INTO users (name,email,password,role)
SELECT 'System Administrator','admin@edufest.local','$2a$10$zv/Vnu6UXqjtuSkf8DchJ..IsOPbvy.NtZlkRNeCDkZ5IKLs4CsJq','ADMIN'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE email='admin@edufest.local');

INSERT INTO events (event_name,description,event_date,start_time,end_time,expected_participants,event_type,status,required_room_type)
SELECT 'Tech Symposium','Sample college technology event','2026-10-20','10:00:00','13:00:00',120,'TECHNICAL','DRAFT','CLASSROOM'
WHERE NOT EXISTS (SELECT 1 FROM events WHERE event_name='Tech Symposium');

INSERT INTO exams (exam_name,subject_code,exam_date,start_time,end_time,semester,department_id)
SELECT 'Database Management Systems','DBMS','2026-10-22','10:00:00','13:00:00',5,id FROM departments WHERE department_code='CSE'
AND NOT EXISTS (SELECT 1 FROM exams WHERE exam_name='Database Management Systems');
