DROP TABLE IF EXISTS public."movie";

CREATE TABLE public."movie"
(
    id    BIGINT  NOT NULL,
    title VARCHAR(255),
    releaseYear  INTEGER NOT NULL,
    CONSTRAINT "pk_movıe" PRIMARY KEY (id)
);