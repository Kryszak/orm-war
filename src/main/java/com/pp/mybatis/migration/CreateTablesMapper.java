package com.pp.mybatis.migration;

import org.apache.ibatis.annotations.Update;

public interface CreateTablesMapper {

    @Update("CREATE TABLE IF NOT EXISTS actors(actorid bigint, a_gender varchar, a_quality integer, PRIMARY KEY(actorid));" +
            "CREATE INDEX IF NOT EXISTS gender_idx ON actors(a_gender);" +
            "CREATE INDEX IF NOT EXISTS quality_idx ON actors(a_quality);")
    void createActorsTable();

    @Update("CREATE TABLE IF NOT EXISTS directors(directorid bigint, d_quality integer, avg_revenue integer, PRIMARY KEY(directorid));" +
            "create index if not exists avg_revenue on directors (avg_revenue);" +
            "create index if not exists d_quality on directors (d_quality);")
    void createDirectorsTable();

    @Update("CREATE TABLE IF NOT EXISTS movies(movieid bigint, year integer, isEnglish boolean, country varchar, runningtime integer, PRIMARY KEY(movieid));" +
            "create index if not exists country on movies(country);" +
            "create index if not exists isEnglish on movies(isEnglish);" +
            "create index if not exists runningtime on movies(runningtime);" +
            "create index if not exists year on movies(year);")
    void createMoviesTable();

    @Update("CREATE TABLE IF NOT EXISTS users(userid bigint, age varchar, u_gender varchar, occupation varchar, PRIMARY KEY (userid));" +
            "CREATE INDEX IF NOT EXISTS age on users(age);" +
            "CREATE INDEX IF NOT EXISTS occupation on users(occupation);" +
            "CREATE INDEX IF NOT EXISTS u_gender on users(u_gender);")
    void createUsersTable();

    @Update("CREATE TABLE IF NOT EXISTS movies2actors(movieid bigint REFERENCES movies(movieid), actorid bigint REFERENCES actors(actorid), cast_num integer, PRIMARY KEY(movieid,actorid));" +
            "CREATE INDEX IF NOT EXISTS castnum on movies2actors(cast_num);" +
            "CREATE INDEX IF NOT EXISTS movie on movies2actors(movieid);" +
            "CREATE INDEX IF NOT EXISTS actor on movies2actors(actorid);")
    void createMovies2ActorsTable();

    @Update("CREATE TABLE IF NOT EXISTS movies2directors(movieid bigint REFERENCES movies(movieid), directorid bigint REFERENCES directors(directorid), genre varchar, PRIMARY KEY(movieid,directorid));" +
            "CREATE INDEX IF NOT EXISTS genreidx on movies2directors(genre);" +
            "CREATE INDEX IF NOT EXISTS movieidx on movies2directors(movieid);" +
            "CREATE INDEX IF NOT EXISTS directoridx on movies2directors(directorid);")
    void createMovies2Directors();

    @Update("CREATE TABLE IF NOT EXISTS u2base(userid bigint REFERENCES users(userid), movieid bigint REFERENCES movies(movieid), rating varchar, PRIMARY KEY(userid,movieid));" +
            "CREATE INDEX IF NOT EXISTS ratingidx on u2base(rating);" +
            "CREATE INDEX IF NOT EXISTS useridx on u2base(userid);" +
            "CREATE INDEX IF NOT EXISTS u2basemovieidx on u2base(movieid);")
    void createU2BaseTable();
}
