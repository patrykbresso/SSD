INSERT INTO roles(name) VALUES('ROLE_GUEST');
INSERT INTO roles(name) VALUES('ROLE_AUTHOR');
INSERT INTO roles(name) VALUES('ROLE_REVIEWER');
INSERT INTO roles(name) VALUES('ROLE_ORGANISER');

INSERT INTO currency_value (id, amount, currency, type) VALUES ('1', '10', 'PLN', 'STUDENT');
INSERT INTO currency_value (id, amount, currency, type) VALUES ('1', '15', 'PLN', 'STANDARD');
INSERT INTO currency_value (id, amount, currency, type) VALUES ('1', '25', 'PLN', 'VIP');

# address
INSERT INTO address (id, city, country, postal_code, street, street_number) VALUES ('1', 'Wroclaw', 'POLAND', '55-125', 'Ulica', '12');

#conference info
INSERT INTO information (id, description, max_number_of_presentations, max_number_of_seats, topic) VALUES ('1', 'Description 1', '8', '100', 'Machine Learning');

#schedule
INSERT INTO schedules (id, status, conference_id) VALUES ('1', 'IN_PROGRESS', '1');

# conference
INSERT INTO conferences(id, cancelled, end_date, registration_date, start_date, address_id, information_id, schedule_id) VALUES ('1', false, '2019-07-01 8:00', '2019-06-15 23:59', '2019-07-01 16:00', '1', '1', '1');

# organiser
INSERT INTO organisers(id, email, name, password, surname, role_id) VALUES ('1', 'organiser@gmail.com', 'Andrzej', '$2a$10$r8mAqL.VD7cLEh/i9Hl96.E2SYrNUuFauPxG3q4hKywigL8Qx94Vy', 'Strzelba', '4');

#reviewer
INSERT INTO reviewers (id, email, name, password, surname, academic_title, role_id) VALUES ('1', 'reviewer1@gmail.com', 'Jan', '$2a$10$r8mAqL.VD7cLEh/i9Hl96.E2SYrNUuFauPxG3q4hKywigL8Qx94Vy', 'Kowalczyk', 'PROFESSOR', '3');
INSERT INTO reviewers (id, email, name, password, surname, academic_title, role_id) VALUES ('2', 'reviewer2@gmail.com', 'Andrzej', '$2a$10$r8mAqL.VD7cLEh/i9Hl96.E2SYrNUuFauPxG3q4hKywigL8Qx94Vy', 'Dominik', 'PROFESSOR', '3');

# author
INSERT INTO authors (id, email, name, password, surname, academic_title, banned, role_id) VALUES ('1', 'author1@gmail.com', 'Jan', '$2a$10$r8mAqL.VD7cLEh/i9Hl96.E2SYrNUuFauPxG3q4hKywigL8Qx94Vy', 'Kowalczyk', 'PROFESSOR', false, '2');
INSERT INTO authors (id, email, name, password, surname, academic_title, banned, role_id) VALUES ('2', 'author2@gmail.com', 'Andrzej', '$2a$10$r8mAqL.VD7cLEh/i9Hl96.E2SYrNUuFauPxG3q4hKywigL8Qx94Vy', 'Dominik', 'DOCTOR', false, '2');
INSERT INTO authors (id, email, name, password, surname, academic_title, banned, role_id) VALUES ('3', 'author3@gmail.com', 'Jan', '$2a$10$r8mAqL.VD7cLEh/i9Hl96.E2SYrNUuFauPxG3q4hKywigL8Qx94Vy', 'Janusz', 'PROFESSOR', false, '2');

# guest
INSERT INTO guests(id, email, name, password, surname, role_id) VALUES ('1', 'guest1@gmail.com', 'Dominik', '$2a$10$r8mAqL.VD7cLEh/i9Hl96.E2SYrNUuFauPxG3q4hKywigL8Qx94Vy', 'Strzelba', '1');
INSERT INTO guests(id, email, name, password, surname, role_id) VALUES ('2', 'guest2@gmail.com', 'Lukasz', '$2a$10$r8mAqL.VD7cLEh/i9Hl96.E2SYrNUuFauPxG3q4hKywigL8Qx94Vy', 'Strzelba', '1');

# author_payment
INSERT INTO author_payments (id, due_date, payment_date, status, currency_value_id, author_id, conference_id) VALUES ('1', '2019-06-15 22:00', '2019-05-30 08:00', 'ACCEPTED', '3', '1', '1');
INSERT INTO author_payments (id, due_date, status, currency_value_id, author_id, conference_id) VALUES ('2', '2019-06-15 22:00', 'NEW', '2', '2', '1');
INSERT INTO author_payments (id, due_date, payment_date, status, currency_value_id, author_id, conference_id) VALUES ('3', '2019-06-15 22:00', '2019-05-30 08:00', 'ACCEPTED', '3', '3', '1');

# guest_payment
INSERT INTO guest_payments (id, due_date, payment_date, status, currency_value_id, conference_id, guest_id) VALUES ('1', '2019-06-15 22:00', '2019-05-30 08:00', 'ACCEPTED', '1', '1', '1');
INSERT INTO guest_payments (id, due_date, payment_date, status, currency_value_id, conference_id, guest_id) VALUES ('2', '2019-06-15 22:00', '2019-05-30 08:00', 'ACCEPTED', '2', '1', '2');

# conference -> organiser
INSERT INTO organisers_conferences (organisers_id, conferences_id) VALUES ('1', '1');

# conference -> author
INSERT INTO authors_conferences (authors_id, conferences_id) VALUES ('1', '1');
INSERT INTO authors_conferences (authors_id, conferences_id) VALUES ('2', '1');
INSERT INTO authors_conferences (authors_id, conferences_id) VALUES ('3', '1');

# conference -> guest
INSERT INTO guests_conferences (guests_id, conferences_id) VALUES ('1', '1');
INSERT INTO guests_conferences (guests_id, conferences_id) VALUES ('2', '1');

# poster

