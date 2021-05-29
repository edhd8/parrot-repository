CREATE TABLE public.satellite (
    id SERIAL,
    name VARCHAR NOT NULL,
    distance NUMERIC(10,1) NOT NULL,
    message          TEXT,
    created_at TIMESTAMP NOT NULL default now(),
    updated_at TIMESTAMP NOT NULL default now(),
    constraint pk_satellite PRIMARY KEY (id)
);

CREATE INDEX index_satellite_id ON satellite(id);