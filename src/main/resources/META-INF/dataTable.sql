CREATE TABLE IF NOT EXISTS public.department
(
    id integer NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT department_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.employee
(
    id integer NOT NULL DEFAULT nextval('employee_id_seq'::regclass),
    age character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    department integer,
    CONSTRAINT employee_pkey PRIMARY KEY (id),
    CONSTRAINT fkkxx4wtsgsdt16iix2pso0k126 FOREIGN KEY (department)
        REFERENCES public.department (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);