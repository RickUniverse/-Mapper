package org.generator.pojo;

import javax.persistence.*;

@Table(name = "tb_employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "last_name")
    private String lastName;

    private String gender;

    private String email;

    @Column(name = "dept_id")
    private Integer deptId;

    @Column(name = "emp_state")
    private String empState;

    private Integer version;

    @Column(name = "logic_flag")
    private Integer logicFlag;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return last_name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return dept_id
     */
    public Integer getDeptId() {
        return deptId;
    }

    /**
     * @param deptId
     */
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    /**
     * @return emp_state
     */
    public String getEmpState() {
        return empState;
    }

    /**
     * @param empState
     */
    public void setEmpState(String empState) {
        this.empState = empState;
    }

    /**
     * @return version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * @return logic_flag
     */
    public Integer getLogicFlag() {
        return logicFlag;
    }

    /**
     * @param logicFlag
     */
    public void setLogicFlag(Integer logicFlag) {
        this.logicFlag = logicFlag;
    }
}