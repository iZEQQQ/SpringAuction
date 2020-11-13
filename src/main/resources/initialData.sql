INSERT INTO branches (id, name) VALUES
    (1,'meat'),
    (2,'vegetables'),
    (3,'toys');

INSERT INTO categories (id, name, branch) VALUES
    (1,'car',3),
    (2,'game',3),
    (3,'lego',3);

INSERT INTO auctions (id, name, price, quantity, category) VALUES
    (1,'zabawka',20,4,2),
    (2,'koc',100,7,2),
    (3,'kubek',15,8,3);

INSERT INTO users (login,password) VALUES
    ('Jax','$2a$10$hJMqlcX6yldSFQFL4s4HkeALoIK9JbWbW6F.zBEkCJo4NR.ar8JLS'),
    ('Yuri','$2y$10$xl6SDXEYyV10mQuubEXJteO7vKn9Xv1qTRLvuO9BTGIgC33hrqERG'),
    ('Mate','$2y$10$xl6SDXEYyV10mQuubEXJteO7vKn9Xv1qTRLvuO9BTGIgC33hrqERG');

INSERT INTO roles (user_name, role_name) VALUES
    ('Jax', 'Admin'),
    ('Jax', 'User'),
    ('Yuri', 'User'),
    ('Mate', 'User');

INSERT INTO orders (id, user_name,local_date_time) VALUES
    (1,'Jax','2005-10-20 10:40'),
    (2,'Yuri', '2020-05-31 15:15'),
    (3,'Jax', '2016-9-17 21:21');

INSERT INTO order_items (order_id, auction_id, quantity) VALUES
    (1,2,5),
    (2,3,3),
    (1,3,10);


-- TODO z springa przykladu z rpg znalexc przyklad do wgrania przykladowej bazy danych