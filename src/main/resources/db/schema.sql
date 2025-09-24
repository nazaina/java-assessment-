CREATE TABLE IF NOT EXISTS customers (
    id UUID PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    address VARCHAR(255),
    phone_no VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS products (
    id UUID PRIMARY KEY,
    book_title VARCHAR(255),
    book_price DECIMAL(10,2),
    book_quantity INTEGER

    );

