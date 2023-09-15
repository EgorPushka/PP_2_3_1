create table if not exists users
(
    id         int PRIMARY KEY AUTO_INCREMENT,
    user_name  varchar(50),
    user_age   int,
    user_email varchar(50) not null
)
collate 'utf8_general_ci';
create unique index user_name_index ON users (user_name);

insert ignore into users (user_name, user_age, user_email)
values ("Luke Skywalker", 23, "luke.skywalker@gmail.com"),
       ("Ellis Ripley", 35, "ellie.arroway@gmail.com"),
       ("Neo Anderson", 30, "neo.anderson@gmail.com"),
       ("Arthur Dent", 32, "arthur.dent@gmail.com"),
       ("Ellie Arroway", 28, "ellie.arroway@gmail.com"),
       ("Yoda", 900, "yoda@gmail.com"),
       ("Gandalf", 2019, "gandalf@gmail.com"),
       ("Optimus Prime", 10000, "optimus.prime@gmail.com");