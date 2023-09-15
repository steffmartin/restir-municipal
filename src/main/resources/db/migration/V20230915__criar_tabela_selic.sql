    create table public.selic (
          id bigserial not null,
          mes timestamp(6) not null unique,
          valor numeric(38,2) not null,
          primary key (id)
    );