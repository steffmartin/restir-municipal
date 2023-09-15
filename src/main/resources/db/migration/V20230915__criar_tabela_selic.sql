    create table public.selic (
          id bigserial not null,
          periodo date not null unique,
          valor numeric(38,2) not null,
          primary key (id)
    );