package dao;

import model.ScheduleJob;

import java.util.List;

/**
 * Created by shilc on 2016/3/18.
 */
public interface ScheduleJobDao {
    /**
     * 查询任务
     * */
    public List<ScheduleJob> GetScheduleJob();
    /**
     * 添加新任务
     * */
    public int InsertScheduleJob(ScheduleJob jobId);
    /**
     * 更新任务
     * */
    public int UpdateScheduleJob(ScheduleJob jobId);
    /**
     * 删除任务
     * */
    public int DeleteScheduleJob(String jobId);
}
