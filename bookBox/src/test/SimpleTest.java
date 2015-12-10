package test;


import edu.hljcu.hibernate.all.*;
import edu.hljcu.service.IBookBoxSrv;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * 设置要加载的配置文件
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@TransactionConfiguration(defaultRollback = false)
public class SimpleTest extends AbstractTransactionalJUnit4SpringContextTests {
//    ApplicationContext appCt = new ClassPathXmlApplicationContext(new String[]{"file:D:/workspace/bookBox/web/WEB-INF/applicationContext.xml"});
//
//    ApplicationContext context = new FileSystemXmlApplicationContext("web/WEB-INF/applicationContext.xml");
//
//    @Before
//    public void setUp() throws Exception {
//        System.out.println("---before");
//
//    }
//
//
//    @After
//    public void tearDown() throws Exception {
//        System.out.println("----after--");
//    }
//
//    @Test
//    public void  testFindBox() {
//        System.out.println("---findBox---");
//    }
////
////


//    @Autowired
//    BoxDAO boxDAO ;
//    @Autowired
//    RowCateDAO rowCateDAO;

//    public String[] getConfigLocations() {
//        String[] configLocations = {"file:D:/workspace/bookBox/web/WEB-INF/applicationContext.xml"};
//        return configLocations;
//    }
//
//    public void onSetUp() throws Exception {
//       // super.onSetUp();
//    }

    @Test
    public void testGetProduct() {
//        ApplicationContext ctx =
//                new FileSystemXmlApplicationContext("file:D:/workspace/bookBox/web/WEB-INF/applicationContext.xml");
        //boxDAO = (BoxDAO) ctx.getBean("boxDAO");
        //assertEquals("4", boxDAO.findById(20).getBoxName());
//        System.out.println("--------------");
//        System.out.println(boxDAO.findById(67).getBoxName());
//        System.out.println(boxDAO.findById(67).getBoxName());
//        System.out.println(boxDAO.findById(69).getBoxName());
//        System.out.println(boxDAO.findById(69).getBoxName());
//        System.out.println(3363);
//        System.out.println(boxDAO.findById(3633));
//        boxDAO.cleanCache();
//        System.out.println(3363);
//        System.out.println(boxDAO.findById(3633));
//        System.out.println(3634);
//        System.out.println(boxDAO.findById(3634));
//        System.out.println(3634);
//        System.out.println(boxDAO.findById(3634));
//        Box box = new Box();
//        box.setBoxId(9922);
//        box.setBoxName("wahaha");
//        box.setStatus("0");
//        RowCate rowCate = rowCateDAO.findById(62);
//        box.setRowCate(rowCate);
//        //box.setBookInfos();
//        boxDAO.save(box);

    }
}
