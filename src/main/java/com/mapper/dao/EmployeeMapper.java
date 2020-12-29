package com.mapper.dao;

import com.mapper.mymapper.MyMapper;
import com.mapper.pojo.Employee;
import org.apache.ibatis.annotations.CacheNamespace;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author lijichen
 * @date 2020/12/12 - 15:33
 */
/*
* 使用通用mapper继承mapper接口
* */
@CacheNamespace//该mapper使用二级缓存
public interface EmployeeMapper extends Mapper<Employee>, MyMapper<Employee> {
}
