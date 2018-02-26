package service.impl;

import dao.ScheduleJobDao;
import model.ScheduleJob;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import service.ScheduleJobService;

import java.util.List;

/**
 * Created by shilc on 2016/3/18.
 */
@Service
public class ScheduleJobServiceImpl  implements ScheduleJobService{

    @Autowired
    private ScheduleJobDao scheduleJobDao;

    @Override
    public List<ScheduleJob> GetScheduleJob() {
        return scheduleJobDao.GetScheduleJob();
    }

    @Override
    public int InsertScheduleJob(ScheduleJob jobId) {
        return 0;
    }

    @Override
    public int UpdateScheduleJob(ScheduleJob jobId) {
        return 0;
    }

    @Override
    public int DeleteScheduleJob(String jobId) {
        return 0;
    }
}
