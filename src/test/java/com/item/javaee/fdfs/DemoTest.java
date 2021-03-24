package com.item.javaee.fdfs;

import com.item.javaee.utils.FastDfsUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * @ClassName: DemoTest
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-07 20:27
 * @Version: 1.0
 **/
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class DemoTest {

    private String local_fileName = "C:\\Users\\jff\\Pictures\\lion.jpg" ;
    @Test
    public void testUpload() throws IOException, MyException {
        NameValuePair nvp = new NameValuePair("age", "18") ;


        String x = FastDfsUtils.upload(local_fileName, "jpg");

        System.out.println(x) ;
    }
}
