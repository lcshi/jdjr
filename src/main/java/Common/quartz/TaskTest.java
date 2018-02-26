package Common.quartz;

import org.apache.log4j.Logger;

import java.util.Date;

/**
 * Created by shilc on 2016/3/17.
 */
public class TaskTest {
    public final static Logger log=Logger.getLogger(TaskTest.class);
    public void run()
    {
        String msg=new Date()+" ：任务计划一输出！";
        System.out.println(msg);
        log.debug(msg);
    }
}
