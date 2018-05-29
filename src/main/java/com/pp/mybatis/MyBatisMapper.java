package com.pp.mybatis;

import com.pp.mybatis.model.Actor;
import com.pp.mybatis.model.Movie;
import com.pp.mybatis.model.U2BaseFull;
import com.pp.mybatis.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MyBatisMapper {

    @Select("SELECT * FROM actors LIMIT 1")
    Actor testActorSelect();

    @Select("SELECT * FROM actors WHERE a_gender = 'F'")
    List<Actor> selectActressess();

    @Results(value = {
            @Result(property = "rating", column = "rating"),
            @Result(property = "user", column = "userid", javaType = User.class, one = @One(select = "selectUser")),
            @Result(property = "movie", column = "movieid", javaType = Movie.class, one = @One(select = "selectMovie"))
    })
    @Select("SELECT rating FROM u2base WHERE rating = '4'")
    List<U2BaseFull> selectJoin();

    @Select("SELECT * FROM users WHERE userid = #{userId}")
    User selectUser(String userId);

    @Select("SELECT * FROM movies WHERE movieid = #{movieId}")
    Movie selectMovie(String movieId);

    @Update("UPDATE directors SET avg_revenue = 10000 WHERE d_quality > 4")
    int update();
}
