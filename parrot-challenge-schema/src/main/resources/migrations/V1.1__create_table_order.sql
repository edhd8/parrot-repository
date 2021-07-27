DROP TABLE IF EXISTS public.order;

CREATE TABLE public.order (
    id SERIAL,
    name VARCHAR NOT NULL,
    products TEXT NOT NULL,
    total_price NUMERIC(8,2) NOT NULL,
    created_at TIMESTAMP NOT NULL default now(),
    constraint pk_order_name PRIMARY KEY (name)
);

CREATE INDEX index_order_name ON public.order(name);