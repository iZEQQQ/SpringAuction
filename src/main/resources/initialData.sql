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

INSERT INTO users (login) VALUES
    ('Jax'),
    ('Yuri'),
    ('Mate');

INSERT INTO orders (id, user_name,local_date_time) VALUES
    (1,'Jax','2005-10-20 10:40'),
    (2,'Yuri', '2020-05-31 15:15'),
    (3,'Jax', '2016-9-17 21:21');

INSERT INTO order_items (order_id, auction_id, quantity) VALUES
    (1,2,5),
    (2,3,3),
    (1,3,10);
