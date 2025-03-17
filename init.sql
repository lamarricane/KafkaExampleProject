CREATE DATABASE kafka_db;

\c kafka_db;

CREATE TABLE IF NOT EXISTS orders (
    id BIGSERIAL PRIMARY KEY,
    article_numbers BIGINT[] NOT NULL,
    amount NUMERIC(19, 2) NOT NULL,
    order_date DATE,
    status VARCHAR(255) NOT NULL
);