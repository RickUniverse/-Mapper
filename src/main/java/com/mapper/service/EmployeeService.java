package com.mapper.service;

import com.mapper.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lijichen
 * @date 2020/12/12 - 15:34
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

}
