package com.test.mapper;

import com.test.dto.TestPressure;
import  java.util.List;

public interface TestPressureMapper {
    int insert(TestPressure record);

    int insertSelective(TestPressure record);

     int batchInsert(List<TestPressure> testPressureList);
}