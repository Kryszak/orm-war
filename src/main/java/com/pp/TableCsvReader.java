package com.pp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TableCsvReader {

    public static final String ACTORS_TABLE_FILE = "imdb_MovieLens_actors.csv";

    public static final String DIRECTORS_TABLE_FILE = "imdb_MovieLens_directors.csv";

    public static final String MOVIES_TABLE_FILE = "imdb_MovieLens_movies.csv";

    public static final String MOVIES_2_ACTORS_TABLE_FILE = "imdb_MovieLens_movies2actors.csv";

    public static final String MOVIES_2_DIRECTORS_TABLE_FILE = "imdb_MovieLens_movies2directors.csv";

    public static final String U2BASE_TABLE_FILE = "imdb_MovieLens_users.csv";

    public static final String USERS_TABLE_FILE = "imdb_MovieLens_users.csv";

    public static final String SEPARATOR = ",";

    public Stream<String> streamTableFile(String path) throws IOException {
        return Files.lines(Paths.get(getClass().getClassLoader().getResource(path).getPath()));
    }

}
