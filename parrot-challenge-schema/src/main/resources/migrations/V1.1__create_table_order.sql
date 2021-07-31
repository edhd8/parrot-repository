DROP TABLE IF EXISTS public.orders;

CREATE TABLE public.orders (
    id SERIAL,
    name VARCHAR NOT NULL,
    products TEXT NOT NULL,
    quantities TEXT NOT NULL,
    subtotals TEXT NOT NULL,
    total_price NUMERIC(8,2) NOT NULL,
    created_at TIMESTAMP NOT NULL default now(),
    constraint pk_orders_name PRIMARY KEY (name)
);

CREATE INDEX index_orders_name ON public.orders(name);