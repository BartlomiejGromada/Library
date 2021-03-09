INSERT INTO author(date_of_birth, first_name, last_name)
VALUES ('1977-02-12', 'Jan', 'Kowalski'),
       ('1988-10-11', 'Anna', 'Nowak'),
       ('1998-01-16', 'Adam', 'Mickiewicz'),
       ('1997-03-01', 'Jan', 'Brzechwa'),
       ('1994-01-16', 'Andrzej', 'Ptak');

INSERT INTO category(name)
VALUES ('Autobiography'),
       ('Bestseller'),
       ('Drama'),
       ('Encyclopedia'),
       ('Fable'),
       ('Novel'),
       ('Poetry'),
       ('Romantic Novel'),
       ('Crime Story'),
       ('Thriller');


INSERT INTO book(isbn, title)
VALUES ('1234345674567', 'Witaj Barabaszu'),
       ('5645345435435', 'Virus'),
       ('7865675456765', 'Czerwony Kapturek'),
       ('8987897654324', 'Wiersze'),
       ('7845654564345', 'Calineczka');

INSERT INTO book_author(id_book, id_author)
VALUES (1, 2),
       (2, 3),
       (3, 2),
       (4, 4),
       (1, 1),
       (5, 3),
       (5, 4),
       (5, 5);

INSERT INTO book_category(id_book, id_category)
VALUES (1, 3),
       (1, 2),
       (2, 10),
       (2, 9),
       (3, 5),
       (4, 2),
       (5, 5),
       (5, 2);
