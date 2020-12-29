import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.generator.dao.EmployeeMapper;
import org.generator.pojo.Employee;
import org.junit.Test;
import tk.mybatis.mapper.mapperhelper.MapperHelper;

/**
 * @author lijichen
 * @date 2020/12/12 - 19:49
 */
public class TestMGB {
    @Test
    public void test() {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = builder.build(TestMGB.class.getClassLoader().getResourceAsStream("mybatis-config.xml"));

        SqlSession sqlSession = build.openSession();

        //需要 通用mapper的MapperHelper处理一下sqlSession
        //不然会报错
        MapperHelper mapperHelper = new MapperHelper();
        Configuration configuration = sqlSession.getConfiguration();
        mapperHelper.processConfiguration(configuration);
        //============

        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

        System.out.println(mapper.selectAll());
    }
}
