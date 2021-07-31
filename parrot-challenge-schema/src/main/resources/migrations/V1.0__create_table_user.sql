DROP TABLE IF EXISTS public.users;

CREATE TABLE public.users (
    id SERIAL,
    email VARCHAR NOT NULL,
    name VARCHAR NOT NULL,
    created_at TIMESTAMP NOT NULL default now(),
    constraint pk_users PRIMARY KEY (email)
);

CREATE INDEX index_users_name ON public.users(name);