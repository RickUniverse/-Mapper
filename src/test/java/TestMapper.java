import com.mapper.dao.EmployeeMapper;
import com.mapper.pojo.Address;
import com.mapper.pojo.EmpState;
import com.mapper.pojo.Employee;
import com.mapper.service.EmployeeService;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tk.mybatis.mapper.entity.Example;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * @author lijichen
 * @date 2020/12/12 - 15:25
 */
public class TestMapper {
    private ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("applicationContext.xml");
    private EmployeeMapper employeeMapper = context.getBean(EmployeeMapper.class);




    //enum
    @Test
    public void testEnum() {
        Employee employee = new Employee(null, "asdf", 1, "s",
                new Address("qq", "ww", "ee"),
                EmpState.LOGOUT);
        //插入
        employeeMapper.insert(employee);

        //查询
        System.out.println(employeeMapper.selectOne(employee));
    }
    //typeHandler
    @Test
    public void testMyTypeHandler() {
        Employee employee = new Employee(null, "asdf", 1, "s", new Address("qq", "ww", "ee"));
        //插入
        employeeMapper.insert(employee);

        //查询
        System.out.println(employeeMapper.selectOne(employee));
    }


    //耳机缓存
    @Test
    public void testSecondLeveCache() {
        List<Employee> employees = employeeMapper.selectAll();


        List<Employee> employees2 = employeeMapper.selectAll();

    }
    //MyBatchUpdateProvider,自己写的
    @Test
    public void testMethodProvider() {
        employeeMapper.batchUpdate(Arrays.asList(new Employee(1,"asd",1,"aee"),
                new Employee(4,"w",1,"aeer"),
                new Employee(5,"ased",1,"aree")
                ));


        context.close();
    }


    @Test
    public void testMethodPage() {
        int pageNo = 1;
        int pageSize = 4;
        int index = (pageNo - 1)*pageSize;
        RowBounds rowBounds = new RowBounds(index,pageSize);
        employeeMapper.selectByRowBounds(null,rowBounds)
                .forEach(System.out::println);

    }
    @Test
    public void testMethod() {
//        Employee name = employeeMapper.selectOne(new Employee(3, "name", null, null));
//        System.out.println(name);

//        System.out.println(employeeMapper.selectByPrimaryKey(1));

        //insert
//        Employee employee = new Employee(null, "name", null, null);
//        employeeMapper.insert(employee);
//        System.out.println(employee.getId());

        //selection
//        Employee employee = new Employee(null, "name", null, null);
//        employeeMapper.insertSelective(employee);
//        System.out.println(employee.getId());
        //example: QBC查询
        Example example = new Example(Employee.class);

        Example.Criteria criteria01 = example.createCriteria();
        Example.Criteria criteria02 = example.createCriteria();

        criteria01.andGreaterThan("id",3)
                .andEqualTo("lastName","name");
        criteria02.andLessThan("id",3000)
                .andNotEqualTo("lastName","张武");

        example.or(criteria02);//使用or拼接两个条件

        example.setDistinct(true);//是否去重
        example.orderBy("id");//排序
        example.excludeProperties("id","email");//排除哪些字段
        //example.selectProperties("id","email");//只包含哪些字段

        employeeMapper.selectByExample(example);
    }


    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSource = (DataSource) context.getBean("dataSource");
        System.out.println(dataSource.getConnection());
    }
}
