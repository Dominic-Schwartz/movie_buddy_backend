insert into genres (name, description) values
                                           ('Action','Fast-paced, stunts, fights'),
                                           ('Drama','Character-driven stories'),
                                           ('Comedy','Light-hearted, humorous');

insert into movies (title, description, release_year, genre_id)
select 'Edge of Action', 'Prototype action movie', 2022, g.id from genres g where g.name = 'Action';

insert into movies (title, description, release_year, genre_id)
select 'Tears of Drama', 'Character-driven drama', 2021, g.id from genres g where g.name = 'Drama';

-- tijdelijke user om een profiel aan te koppelen
insert into users (username, email, password_hash, role)
values ('buddytester','buddytester@moviebuddy.nl','demo','ROLE_USER');

-- profiel koppelen via user_id (haal id dynamisch op)
insert into profiles (avatar_url, bio, user_id)
select 'https://example.com/avatar.png','Demo user profile', u.id
from users u where u.username = 'buddytester';
