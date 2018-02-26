package service;

import model.ScheduleJob;

import java.util.List;

/**
 * Created by shilc on 2016/3/21.
 */
public interface QuartzFactoryService {
    public void init(List<ScheduleJob> jobList);
    public void addJob(ScheduleJob job);
    public List<ScheduleJob> GetAllJob();
    public List<ScheduleJob> getRunningJob();
    public void PauseJob(ScheduleJob scheduleJob);
    public void ResumeJob(ScheduleJob scheduleJob);
    public void DeleteJob(ScheduleJob scheduleJob);
    public void  UpdateJonCronExpression(ScheduleJob scheduleJob);
}
