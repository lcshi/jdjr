package service.impl;

import Common.quartz.QuartzJobFactory;
import Common.quartz.QuartzJobFactoryDisallowConcurrentExecution;
import model.ScheduleJob;
import org.quartz.*;

import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import service.QuartzFactoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by shilc on 2016/3/9.
 */
@Service
public class QuartzFactoryServiceImpl implements QuartzFactoryService {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    /**
     * 初始化任务
     * @param jobList
     * @throws Exception
     */
    @Override
   public void init(List<ScheduleJob> jobList) {
        try {
            Scheduler schedule = schedulerFactoryBean.getScheduler();
            for (ScheduleJob job : jobList) {
                addJob(job);
            }
        }catch (Exception e)
        {
            e.printStackTrace();;
        }
    }

    /**
     * 添加任务
     * @param job
     * @throws SchedulerException
     */
    @Override
    public void addJob(ScheduleJob job) {
        if (job == null || !ScheduleJob.STATUS_RUNING.equals(job.getJobStatus())) {
            return;
        }
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (null == trigger) {
                Class clazz = ScheduleJob.CONCURRENT_IS.equals(job.getIsConcurrent()) ? QuartzJobFactory.class : QuartzJobFactoryDisallowConcurrentExecution.class;
                JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobName(), job.getJobGroup()).build();
                jobDetail.getJobDataMap().put("scheduleJob", job);
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
                trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();
                scheduler.scheduleJob(jobDetail, trigger);
            } else {
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有计划中的任务列表
     * @return
     * @throws SchedulerException
     */
    @Override
    public List<ScheduleJob> GetAllJob() {
        List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
            Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);

            for (JobKey jobKey : jobKeys) {
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                for (Trigger trigger : triggers) {
                    ScheduleJob job = new ScheduleJob();
                    job.setJobName(jobKey.getName());
                    job.setJobGroup(jobKey.getGroup());
                    job.setDescription("触发器：" + trigger.getKey());
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                    job.setJobStatus(triggerState.name());
                    if (trigger instanceof CronTrigger) {
                        CronTrigger cronTrigger = (CronTrigger) trigger;
                        String cronExpression = cronTrigger.getCronExpression();
                        job.setCronExpression(cronExpression);
                    }
                    jobList.add(job);
                }
            }
        }catch (SchedulerException e)
        {
            e.printStackTrace();
        }
        return jobList;
    }

    /**
     * 获取正在运行的job
     * @return
     * @throws SchedulerException
     */
    @Override
    public List<ScheduleJob> getRunningJob() {
        List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();

            for (JobExecutionContext executingJob : executingJobs) {
                ScheduleJob job = new ScheduleJob();
                JobDetail jobDetail = executingJob.getJobDetail();
                JobKey jobKey = jobDetail.getKey();
                Trigger trigger = executingJob.getTrigger();
                job.setJobName(jobKey.getName());
                job.setJobGroup(jobKey.getGroup());
                job.setDescription("触发器：" + trigger.getKey());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                job.setJobStatus(triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    job.setCronExpression(cronExpression);
                }
                jobList.add(job);
            }
        }catch (SchedulerException e)
        {
            e.printStackTrace();
        }
        return  jobList;
    }

    /**
     * 暂停一个Job
     * @param scheduleJob
     * @throws SchedulerException
     */
    @Override
    public void PauseJob(ScheduleJob scheduleJob) {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
            scheduler.pauseJob(jobKey);
        }catch (SchedulerException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 恢复一个Job
     * @param scheduleJob
     * @throws SchedulerException
     */
    @Override
    public void ResumeJob(ScheduleJob scheduleJob){
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
            scheduler.resumeJob(jobKey);
        }catch (SchedulerException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 启动一个Job
     * @param scheduleJob
     * @throws SchedulerException
     */
    @Override
    public void DeleteJob(ScheduleJob scheduleJob) {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
            scheduler.triggerJob(jobKey);
        }catch (SchedulerException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 更新Job时间表达式
     * @param scheduleJob
     * @throws SchedulerException
     */
    @Override
    public void  UpdateJonCronExpression(ScheduleJob scheduleJob) {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
            CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
            cronTrigger = cronTrigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();
            scheduler.rescheduleJob(triggerKey, cronTrigger);
        }catch (SchedulerException e)
        {
            e.printStackTrace();
        }
    }
        
}
