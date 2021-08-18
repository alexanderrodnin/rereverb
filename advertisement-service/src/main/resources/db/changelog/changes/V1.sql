do
$$
    begin
        create type advertisement_status_type as enum ('OPENED', 'CLOSED');
    exception
        when duplicate_object then null;
    end;
$$
;/

create table if not exists advertisement
(
    id uuid not null,
    user_id uuid not null,
    header varchar not null,
    description varchar,
    price double precision not null,
    status advertisement_status_type not null
);



