CREATE TABLE pawk.favorites (
   course TEXT UNIQUE PRIMARY KEY NOT NULL
);
COMMENT ON TABLE pawk.favorites IS 'Favorites table for a user';