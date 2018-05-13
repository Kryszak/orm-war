package com.pp.mybatis;

import com.pp.mybatis.model.Actor;
import org.apache.ibatis.annotations.Select;

public interface MyBatisMapper {

    @Select("SELECT * FROM actors LIMIT 1")
    Actor testActorSelect();

}
