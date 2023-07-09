create table Users(
    id int primary key generated by default as identity,
    firstname varchar,
    surname varchar,
    login varchar(20) not null,
    phone_number varchar check
        (phone_number similar to '^((8|\+7)[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{7,10}$'),
    email varchar check (email similar to '^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$'),
    password char[] not null,
    gender varchar default 'NONE',
    created_at timestamp not null,
    non_locked bool default true
);

create table Role(
    id int primary key references Users (id) on delete cascade,
    role varchar default 'USER'
);

create table Post(
    id int primary key generated by default as identity,
    user_id int references Users (id) on delete cascade,
    heading varchar(50),
    text varchar(1000),
    path_to_image varchar,
    created_at timestamp not null
);

create table Dialog(
                       id int primary key generated by default as identity,
                       owner_id int references Users(id) on delete cascade not null,
                       companion_id int references Users(id) on delete cascade not null
);

create table Message(
                        id int primary key generated by default as identity,
                        dialog_id int references Dialog(id) on delete cascade not NULL,
                        text varchar not null,
                        is_reading boolean default false,
                        created_at timestamp
);

create table Relationship(
                             id int primary key generated by default as identity,
                             first_user_id int references Users(id) on delete cascade not null,
                             second_user_id int references Users(id) on delete cascade not null,
                             relationship_type varchar not null
);

