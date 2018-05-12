package com.pp.mybatis;

import com.pp.mybatis.model.Test;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface MyBatisMapper {

    @Select("SELECT * FROM test LIMIT 1")
    Test testSelect();

    @Insert("INSERT INTO test VALUES(#{test})")
    void insertTest(Test test);

    @Update("CREATE TABLE IF NOT EXISTS test2(id SERIAL)")
    void createTable();
}
