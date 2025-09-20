insert into genres (name, description) values
                                           ('Action','Fast-paced, stunts, fights'),
                                           ('Drama','Character-driven stories'),
                                           ('Comedy','Light-hearted, humorous');

insert into movies (title, description, release_year, genre_id)
select 'Edge of Action', 'Prototype action movie', 2022, g.id from genres g where g.name = 'Action';

insert into movies (title, description, release_year, genre_id)
select 'Tears of Drama', 'Character-driven drama', 2021, g.id from genres g where g.name = 'Drama';
