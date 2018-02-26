package Common.quartz;


import Common.spring.SpringUtils;
import model.ScheduleJob;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;

import java.lang.reflect.Method;


/**
 * Created by shilc on 2016/3/15.
 */
public class TaskUtils {
    public final static Logger log=Logger.getLogger(TaskUtils.class);

    public static void InvokMethod(ScheduleJob scheduleJob)
    {
        Object object=null;
        Class clazz=null;
        if(StringUtils.isNotBlank(scheduleJob.getSpringId()))
        {
            object= SpringUtils.GetBean(scheduleJob.getSpringId());
        }else if(StringUtils.isNoneBlank(scheduleJob.getBeanClass())){
            try{
                clazz=Class.forName(scheduleJob.getBeanClass());
                object=clazz.newInstance();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        if (object == null) {
            log.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，请检查是否配置正确！！！");
            return;
        }
        clazz=object.getClass();
        Method method=null;
        try{
            method=clazz.getDeclaredMethod(scheduleJob.getMethodName());
        }catch (NoSuchMethodException e) {
            log.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，方法名设置错误！！！");
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        if(method!=null)
        {
            try{
                method.invoke(object);
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}
