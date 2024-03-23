CREATE TABLE starscore.movie_category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    image_url VARCHAR(255)

);

CREATE TABLE starscore.movie (
    id SERIAL PRIMARY KEY UNIQUE NOT NULL,
    category_id SERIAL REFERENCES starscore.movie_category(id),
    title VARCHAR(255) NOT NULL,
    description TEXT,
    release_year INTEGER,
    director VARCHAR(100),
    image_url VARCHAR(255),
    background_url VARCHAR(255),
    trailer_url VARCHAR(255)
);

CREATE TABLE starscore.user (
    id SERIAL PRIMARY KEY UNIQUE NOT NULL,
    login VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role TEXT NOT NULL
);

CREATE TABLE starscore.watchlist (
    id SERIAL PRIMARY KEY UNIQUE NOT NULL,
    user_id SERIAL REFERENCES starscore.user(id) ON DELETE CASCADE,
    movie_id SERIAL REFERENCES starscore.movie(id),
    favorite BOOLEAN,
    watched BOOLEAN,
    watched_date DATE,
    CONSTRAINT unique_watchlist UNIQUE (user_id, movie_id)
);

CREATE TABLE starscore.rate (
    id SERIAL PRIMARY KEY UNIQUE NOT NULL,
    user_id SERIAL REFERENCES starscore.user(id),
    movie_id SERIAL REFERENCES starscore.movie(id),
    rating INTEGER CHECK (rating >= 1 AND rating <= 5),
    comment TEXT,
    rate_date DATE,
    CONSTRAINT unique_rating UNIQUE (user_id, movie_id)
);