package com.pp.mybatis;

import com.pp.mybatis.model.Test;
import org.apache.ibatis.annotations.Select;

public interface MyBatisMapper {

    @Select("Select * FROM test LIMIT 1")
    Test testSelect();
}
