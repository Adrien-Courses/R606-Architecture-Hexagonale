INSERT INTO authors (id, name) VALUES
(1, 'Victor Hugo'),
(2, 'George Orwell'),
(3, 'Ursula K. Le Guin');

INSERT INTO books (isbn, title, borrowed, author_id) VALUES
 ('9782070409228', 'Les Miserables', false, 1),
 ('9782253096337', 'Notre-Dame de Paris', false, 1),

 ('9782070368228', '1984', false, 2),
 ('9782070375165', 'Animal Farm', false, 2),

 ('9780441478125', 'A Wizard of Earthsea', false, 3),
 ('9780061054884', 'The Left Hand of Darkness', false, 3);