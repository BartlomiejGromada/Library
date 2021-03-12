INSERT INTO author(date_of_birth, first_name, last_name)
VALUES ('1977-02-12', 'Jan', 'Kowalski'),
       ('1998-05-12', 'Anna', 'Nowak'),
       ('2000-01-20', 'Adam', 'Mickiewicz'),
       ('1997-11-15', 'Jan', 'Brzechwa'),
       ('1920-12-30', 'Andrzej', 'Ptak');

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


INSERT INTO book(isbn, title, id_category, id_author)
VALUES ('1234345674567', 'Witaj Barabaszu', 3, 1),
       ('5645345435435', 'Virus', 2, 2),
       ('7865675456765', 'Czerwony Kapturek', 5, 4),
       ('8987897654324', 'Wiersze', 7, 5),
       ('7845654564345', 'Calineczka', 1, 3);

INSERT INTO account_role(role, description)
VALUES ('ROLE_USER', 'Default role for user'),
       ('ROLE_ADMIN', 'User with all permissions');

INSERT INTO account(email, first_name, last_name, password)
VALUES ('bartek@wp.pl', 'Bartek', 'Gromada',
        '{bcrypt}$2y$12$8W973MF5dP2JHHT.Ofl0EOJMhZd49zrHxg.K/bzttzqEuF1EfWEAy');

INSERT INTO account_roles(account_id_account, roles_id_user_role)
VALUES (1, 2);

