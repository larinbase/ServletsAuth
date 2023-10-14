create TABLE account(
    id uuid primary key uuid_generate_v4() NOT NULL,
    name varchar(50) NOT NULL ,
    password varchar(50) NOT NULL
);

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";