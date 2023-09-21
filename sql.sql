drop database if exists userdb;
create database if not exists userdb;
use userdb;
create table tb_course
(
    id   int auto_increment comment '课程编号'
        primary key,
    name varchar(50) null comment '课程名称'
);

INSERT INTO userdb.tb_course (name)
VALUES ('Java基础');
INSERT INTO userdb.tb_course (name)
VALUES ('Mysql优化');
INSERT INTO userdb.tb_course (name)
VALUES ('Unity游戏开发');
INSERT INTO userdb.tb_course (name)
VALUES ('Python爬虫实战');


create table tb_teacher
(
    id   int auto_increment comment '编号'
        primary key,
    name varchar(50) null,
    cid  int         null,
    constraint tb_teacher_tb_course_id_fk
        foreign key (cid) references tb_course (id)
)
    comment '教师表';

INSERT INTO userdb.tb_teacher (name, cid)
VALUES ('张老师', 1);
INSERT INTO userdb.tb_teacher (name, cid)
VALUES ('李老师', 2);
INSERT INTO userdb.tb_teacher (name, cid)
VALUES ('姬老师', 3);
INSERT INTO userdb.tb_teacher (name, cid)
VALUES ('王老师', 4);


create table tb_student
(
    id       int auto_increment comment '编号'
        primary key,
    name     varchar(50)   null comment '名字',
    sex      int default 0 null comment '0男,1女',
    age      int           null comment '年龄',
    birthday date          null comment '生日',
    img      varchar(255)  null comment '图片',
    hobby    varchar(255)  null comment '爱好',
    tid      int           null comment '教师外键',
    del      int default 0 null comment '逻辑删除 0显示:1不显示',
    constraint tb_student_tb_table_id_fk
        foreign key (tid) references tb_teacher (id)
)
    comment '学生表';

INSERT INTO userdb.tb_student (name, sex, age, birthday, img, hobby, tid, del)
VALUES ('张三666', 1, 180, '2002-09-04', 'cat.png', '吃,和,喝', 4, 0);
INSERT INTO userdb.tb_student (name, sex, age, birthday, img, hobby, tid, del)
VALUES ('李四', 1, 21, '2027-09-15', 'cat.png', '玩,乐', 2, 0);
INSERT INTO userdb.tb_student (name, sex, age, birthday, img, hobby, tid, del)
VALUES ('张三1', 0, 18, '2023-09-19', 'cat.png', '吃,和,玩,乐', 4, 0);
INSERT INTO userdb.tb_student (name, sex, age, birthday, img, hobby, tid, del)
VALUES ('李四2', 1, 21, '2027-09-12', 'cat.png', '玩,乐', 3, 0);
INSERT INTO userdb.tb_student (name, sex, age, birthday, img, hobby, tid, del)
VALUES ('奥斯', 0, 19, '2023-11-08', null, '吃,和,玩,乐', 3, 0);
INSERT INTO userdb.tb_student (name, sex, age, birthday, img, hobby, tid, del)
VALUES ('奥斯阿', 1, 199, '2023-06-06', null, '玩,乐', 4, 0);
INSERT INTO userdb.tb_student (name, sex, age, birthday, img, hobby, tid, del)
VALUES ('三奥松板', 1, 77, '2023-08-30', null, '吃,和,玩,乐', 1, 0);
INSERT INTO userdb.tb_student (name, sex, age, birthday, img, hobby, tid, del)
VALUES ('驱蚊器', 1, 12, '2023-09-30', null, '玩,乐', 3, 0);
