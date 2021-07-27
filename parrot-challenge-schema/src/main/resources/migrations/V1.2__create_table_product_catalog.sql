DROP TABLE IF EXISTS public.product_catalog;

CREATE TABLE public.product_catalog (
    id SERIAL,
    name VARCHAR NOT NULL,
    unit_price NUMERIC(8,2) NOT NULL,
    created_at TIMESTAMP NOT NULL default now(),
    constraint pk_product_catalog PRIMARY KEY (name)
);

CREATE INDEX index_product_catalog_name ON public.product_catalog(name);