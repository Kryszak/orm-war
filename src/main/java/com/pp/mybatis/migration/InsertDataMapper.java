package com.pp.mybatis.migration;

import com.pp.mybatis.model.*;
import org.apache.ibatis.annotations.Insert;

public interface InsertDataMapper {

    @Insert("INSERT INTO actors(actorid,a_gender,a_quality) VALUES(#{actorId}, #{actorGender}, #{actorQuality})")
    int insertActor(Actor actor);

    @Insert("INSERT INTO directors(directorid,d_quality,avg_revenue) VALUES(#{directorId}, #{directorQuality}, #{averageRevenue})")
    int insertDirector(Director director);

    @Insert("INSERT INTO movies(movieid,year,isenglish,country,runningtime) VALUES (#{movieId}, #{year}, #{isEnglish}, #{country}, #{runningTime})")
    int insertMovie(Movie movie);

    @Insert("INSERT INTO users(userid, age,u_gender,occupation) VALUES(#{userId}, #{age}, #{userGender}, #{occupation})")
    int insertUser(User user);

    @Insert("INSERT INTO movies2actors(movieid,actorid,cast_num) VALUES(#{movieId}, #{actorId}, #{castNum})")
    int insertMovie2Actor(Movie2Actor movie2Actor);

    @Insert("INSERT INTO movies2directors(movieid,directorid,genre) VALUES(#{movieId}, #{directorId}, #{genre})")
    int insertMovie2Director(Movie2Director movie2Director);

    @Insert("INSERT INTO u2base(userid,movieid,rating) VALUES(#{userId}, #{movieId}, #{rating})")
    int insertU2Base(U2Base u2Base);
}
