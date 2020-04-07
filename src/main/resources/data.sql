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