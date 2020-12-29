package com.mapper.mymapper;

import com.mapper.pojo.Employee;
import tk.mybatis.mapper.common.base.select.SelectAllMapper;
import tk.mybatis.mapper.common.base.select.SelectMapper;

/**
 * @author lijichen
 * @date 2020/12/12 - 20:05
 */
//该接口不能和EmployeeMapper放在同一个包下
public interface MyMapper<Employee> extends SelectMapper<Employee>,
        SelectAllMapper<Employee>,
        MyBatchUpdateMapper<Employee> {
}
