
CREATE TABLE IF NOT EXISTS functions (
    fun_id SERIAL PRIMARY KEY,
    fun_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS roles (
    role_id SERIAL PRIMARY KEY,
    role_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS categories (
    cat_id SERIAL PRIMARY KEY,
    cat_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS sections (
    sec_id SERIAL PRIMARY KEY,
    sec_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS rings (
    ring_id SERIAL PRIMARY KEY,
    ring_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS statuses (
    stat_id SERIAL PRIMARY KEY,
    stat_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    login VARCHAR(255),
    password VARCHAR(255),
    refresh_token VARCHAR(255),
    PRIMARY KEY(user_id),
    CONSTRAINT fk_roles FOREIGN KEY(role_id) REFERENCES roles(role_id)
);

CREATE TABLE IF NOT EXISTS technologies (
    tech_id SERIAL PRIMARY KEY,
    cat_id BIGINT NOT NULL,
    sec_id BIGINT NOT NULL,
    ring_id BIGINT NOT NULL,
    name VARCHAR(255),
    description VARCHAR(255),
    stat_id BIGINT NOT NULL,
    update_time TIMESTAMP,
    CONSTRAINT fk_categories FOREIGN KEY(cat_id) REFERENCES categories(cat_id),
    CONSTRAINT fk_sections FOREIGN KEY(sec_id) REFERENCES sections(sec_id),
    CONSTRAINT fk_rings FOREIGN KEY(ring_id) REFERENCES rings(ring_id),
    CONSTRAINT fk_statuses FOREIGN KEY(stat_id) REFERENCES statuses(stat_id)
    );

CREATE TABLE IF NOT EXISTS polls (
    poll_id SERIAL PRIMARY KEY,
    ring_id BIGINT NOT NULL,
    tech_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    time TIMESTAMP,
    CONSTRAINT fk_users FOREIGN KEY(user_id) REFERENCES users(user_id),
    CONSTRAINT fk_technologies FOREIGN KEY(tech_id) REFERENCES technologies(tech_id),
    CONSTRAINT fk_rings FOREIGN KEY(ring_id) REFERENCES rings(ring_id)
);

CREATE TABLE IF NOT EXISTS mapping (
    id SERIAL PRIMARY KEY,
    fun_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    CONSTRAINT fk_functions FOREIGN KEY(fun_id) REFERENCES functions(fun_id),
    CONSTRAINT fk_roles FOREIGN KEY(role_id) REFERENCES roles(role_id)
);



INSERT INTO functions (fun_name)
VALUES ('Вход/регистрация'),
       ('Просмотр техрадара'),
       ('Фильтрация техрадара (секция, категория, дата)'),
       ('Экспорт техрадара PDF/CSV'),
       ('Группировка техрадара по командам'),
       ('Голосование за распределение продуктов'),
       ('Добавление продукта каталог технологий'),
       ('Просмотр каталога технологий'),
       ('Просмотр дашборда по продукту'),
       ('Архивирование/ Удаление/ редактирование продукта на техрадаре'),
       ('Поиск, сортировка, фильтрация по каталогу технологий');

INSERT INTO roles (role_name)
VALUES ('tech_lead'),
       ('developer');

INSERT INTO categories (cat_name)
VALUES ('Backend'),
       ('Frontend'),
       ('IOS'),
       ('Android');

INSERT INTO sections (sec_name)
VALUES ('Languages'),
       ('Tools'),
       ('Techniques'),
       ('Platforms');

INSERT INTO rings (ring_name)
VALUES ('ADOPT'),
       ('TRIAL'),
       ('ASSETS'),
       ('HOLD'),
       ('BACKLOG');

INSERT INTO statuses (stat_name)
VALUES ('MOVED UP'),
       ('MOVED DOWN'),
       ('NEW'),
       ('NO CHANGE'),
       ('ARCHIVED');





