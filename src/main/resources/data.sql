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

-- 1 demo-review van buddytester voor 'Edge of Action' (rating 8)
insert into reviews (rating, review_text, created_at, user_id, movie_id)
select 8, 'Sterk tempo, toffe actie.', CURRENT_TIMESTAMP, u.id, m.id
from users u
         join movies m on m.title = 'Edge of Action'
where u.username = 'buddytester';

-- één watchlist-item voor buddytester -> Edge of Action
insert into watchlist_items (date_added, status, note, user_id, movie_id)
select CURRENT_DATE, 'TO_WATCH', 'Eerst trailer kijken', u.id, m.id
from users u
         join movies m on m.title = 'Edge of Action'
where u.username = 'buddytester';

-- één upload voor 'Edge of Action'
insert into uploads (filename, content_type, upload_date, movie_id)
select 'edge-of-action-poster.jpg', 'image/jpeg', CURRENT_TIMESTAMP, m.id
from movies m
where m.title = 'Edge of Action';
