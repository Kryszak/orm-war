package com.pp.mybatis;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

public interface MyBatisMapper {

    @Result(column = "1")
    @Select("Select 'test'")
    String testSelect();
}
