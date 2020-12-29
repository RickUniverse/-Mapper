package com.mapper.pojo;

import com.mapper.typehandler.AddressTypeHandler;
import org.apache.ibatis.type.EnumTypeHandler;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author lijichen
 * @date 2020/12/12 - 15:07
 */
@Table(name = "tb_employee")//指明表名
public class Employee implements Serializable {

    /**
     * 指定主键列,如果不指定,使用selectByPrimaryId(id);方法时
     * 会将所有字段作为联合主键进行查询
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键的生成策略,添加时会自动回显
    private Integer id;

    @Column(name = "last_name")//指定字段名,默认支持驼峰命名和下划线命名之间的转换
    private String lastName;
    private Integer gender;
    private String email;

    //@ColumnType(typeHandler = AddressTypeHandler.class)//字段级别注册typeHandler
    @Column//如果使用的是全局的typeHandler则必须加上@Column注解
    private Address address;

//    @ColumnType(typeHandler = EnumTypeHandler.class)//枚举不可以使用这个
    @Column//只要涉及到了类型转换,就必须要有这个注解
    private EmpState empState;

    @Transient//不是数据库中字段
    private String otherCoulm;

    public Employee(Integer id, String lastName, Integer gender, String email, Address address) {
        this.id = id;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.address = address;
    }

    public Employee(Integer id, String lastName, Integer gender, String email, Address address, EmpState empState) {
        this.id = id;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.empState = empState;
    }

    public EmpState getEmpState() {
        return empState;
    }

    public void setEmpState(EmpState empState) {
        this.empState = empState;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Employee() {
    }

    public Employee(Integer id, String lastName, Integer gender, String email) {
        this.id = id;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", empState=" + empState +
                ", otherCoulm='" + otherCoulm + '\'' +
                '}';
    }
}
