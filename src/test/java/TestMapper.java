import com.jun.bean.Department;
import com.jun.bean.Employee;
import com.jun.dao.DepartmentMapper;
import com.jun.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * 测试DepartmentMapper
 * @author HuangJun7
 * @date 2021-11-08 15:03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestMapper {
    /**
     * 推荐Spring项目可以使用Spring的单元测试，自动注入我们需要的组件
     * 1.导入Spring单元测试模块spring-test
     * 2.@ContextConfiguration指定Spring配置文件的位置
     * 3.@RunWith是Junit里面的注解
     * 4.直接@AutoWired要使用的组件即可
     */
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    SqlSession sqlSession;
    @Test
    public void testCRUD() {
        //1.创建SpringIOC容器
        //ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        //2.从容器中获取Mapper
        //DepartmentMapper dept = ctx.getBean(DepartmentMapper.class);

        System.out.println(departmentMapper);
        //a.测试插入几个部门：一定要生成有参，无参构造器
//        departmentMapper.insertSelective(new Department(null,"开发部"));
//        departmentMapper.insertSelective(new Department(null,"测试部"));
        //b.测试生成员工数据，测试员工插入
//        employeeMapper.insertSelective(new Employee(null,"Jelly","M","Jerry@qq.com",1));
        //c.批量插入多个员工，批量，使用可以执行批量操作的sqlSession
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0; i < 1000; i++) {
            String uid = UUID.randomUUID().toString().substring(0, 5) + i;
            mapper.insertSelective(new Employee(null,uid,"M",uid+"@qq.com",1));
        }
        System.out.println("批量完成");
    }
}
