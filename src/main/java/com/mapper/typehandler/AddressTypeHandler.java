package com.mapper.typehandler;

import com.mapper.pojo.Address;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author lijichen
 * @date 2020/12/12 - 21:56
 */
//拦截Address类型的数据
public class AddressTypeHandler extends BaseTypeHandler<Address> {

    //设置参数
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Address address, JdbcType jdbcType) throws SQLException {
        if (address==null){
            return;
        }
        String province = address.getProvince();
        String city = address.getCity();
        String street = address.getStreet();
        //設定规则为 使用 :分开
        StringBuilder builder = new StringBuilder();
        builder.append(province)
                .append(":")
                .append(city)
                .append(":")
                .append(street);
        //设置参数
        preparedStatement.setString(i, builder.toString());

    }

    //数据库中查询到的数据封装到对象
    @Override
    public Address getNullableResult(ResultSet resultSet, String columnName) throws SQLException {

        //根据字段获取字段值
        String columnValue = resultSet.getString(columnName);

        if (columnValue == null || columnValue.length() == 0 || !columnValue.contains(":")){
            return null;
        }
        String[] split = columnValue.split(":");
        //封装address然后返回
        return new Address(split[0],split[1],split[2]);
    }

    @Override
    public Address getNullableResult(ResultSet resultSet, int i) throws SQLException {
        //根据字段获取字段值
        String columnValue = resultSet.getString(i);

        if (columnValue == null || columnValue.length() == 0 || !columnValue.contains(":")){
            return null;
        }
        String[] split = columnValue.split(":");
        //封装address然后返回
        return new Address(split[0],split[1],split[2]);
    }

    //存储过程调用
    @Override
    public Address getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        //根据字段获取字段值
        String columnValue = callableStatement.getString(i);

        if (columnValue == null || columnValue.length() == 0 || !columnValue.contains(":")){
            return null;
        }
        String[] split = columnValue.split(":");
        //封装address然后返回
        return new Address(split[0],split[1],split[2]);
    }
}
