do
$$
    begin
        create type order_status_type as enum ('ACTIVE', 'SUCCESS', 'CANCELLED');
    exception
        when duplicate_object then null;
    end;
$$
;/

create table if not exists order
(
    id uuid not null,
    advertisement_id uuid not null,
    buyer_id uuid not null,
    order_status order_status_type not null
);

create table if not exists order_chat_message
(
    id uuid not null,
    order_id uuid not null,
    user_id uuid not null,
    date_time time not null,
    message varchar not null
);