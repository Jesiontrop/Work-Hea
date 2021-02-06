ALTER TABLE organization
    ADD FULLTEXT(name_of_organization);
ALTER TABLE offer
    ADD FULLTEXT(vacancy_title);

DELETE FROM employment;
DELETE FROM schedule;
DELETE FROM specialization;

INSERT INTO employment (id, name) VALUES
            (1, 'Full employment'),
            (2, 'Part-time employment'),
            (3, 'Project work'),
            (4, 'Volunteering'),
            (5, 'Internship');

INSERT INTO schedule(id, name) VALUES
            (1, 'Full day'),
            (2, 'Shift work'),
            (3, 'Flexible schedule'),
            (4, 'Remote work'),
            (5, 'Shift method');

INSERT INTO specialization(id, name) VALUES
            (1, 'Accounting department'),
            (2, 'Administrative staff'),
            (3, 'Arts, Entertainment, Mass Media'),
            (4, 'Car business'),
            (5, 'Construction, Architecture'),
            (6, 'Consulting'),
            (7, 'Extraction of raw materials'),
            (8, 'Finance, Banking, Investments'),
            (9, 'Information technology, Internet, Multimedia'),
            (10, 'Insurance'),
            (11, 'Lawyers'),
            (12, 'Manufacturing, Technology'),
            (13, 'Marketing, Advertising, PR'),
            (14, 'Medicine, Pharmaceuticals'),
            (15, 'Other'),
            (16, 'Personnel Management'),
            (17, 'Procurement'),
            (18, 'Sales'),
            (19, 'Science, Education'),
            (20, 'Tourism, Hotels, Restaurants'),
            (21, 'Transport, Logistics'),
            (22, 'Working staff');