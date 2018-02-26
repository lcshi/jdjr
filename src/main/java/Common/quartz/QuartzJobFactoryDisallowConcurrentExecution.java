package Common.quartz;

import model.ScheduleJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by shilc on 2016/3/9.
 */
@DisallowConcurrentExecution
public class QuartzJobFactoryDisallowConcurrentExecution  implements Job{

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob) jobExecutionContext.getMergedJobDataMap().get("scheduleJob");
        TaskUtils.InvokMethod(scheduleJob);
    }
}
