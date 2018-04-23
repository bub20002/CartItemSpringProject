USE store;

INSERT INTO carts (name, capacity) VALUES 
('first cart', 10), ('second cart', 20), ('third cart', 40), ('fourth cart', NULL), ('fifth cart', 35);

INSERT INTO items (name, description, price) VALUES
('first item', 'random item', 50), ('second item', 'random item 2', 20), ('third item', 'random item 3', 30),
('fourth item', 'random item 4', 100), ('fifth item', 'random item 5', 80), ('sixth item', 'random item 6', 60);

INSERT INTO itemtocart (cartid, itemid) VALUES
(1, 1), (1, 2), (1, 6), (2, 2), (2, 4), (2, 6), (3, 4), (3, 5), (3, 6), (4, 3), (4, 4), (5, 1), (5, 2), (5, 3), (5, 4), (5, 6);
