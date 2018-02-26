package controller;

import service.QuartzFactoryService;
import service.impl.QuartzFactoryServiceImpl;
import model.ScheduleJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ScheduleJobService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shilc on 2016/3/18.
 */
@Controller
@RequestMapping("/job")
public class ScheduleJobController {
    @Autowired
    private ScheduleJobService scheduleJobService;

    @Autowired
    private QuartzFactoryService quartzFactoryService;

    @RequestMapping("index")
    public String index(){return "index";}

    @RequestMapping(value = "",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object>  GetScheduleJob ()throws Exception
    {
        List<ScheduleJob>  scheduleJobList=scheduleJobService.GetScheduleJob();
        quartzFactoryService.init(scheduleJobList);
        Map<String,Object> modelMap= new HashMap<String, Object>(3);
        modelMap.put("total","1");
        modelMap.put("data", scheduleJobList);
        modelMap.put("success", "true");
        return modelMap;
    }
}
