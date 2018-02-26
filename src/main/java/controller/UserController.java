package controller;

import dao.UserDao;
import model.User;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by shilc on 2016/2/23.
 */
@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("index")
    public String index(){
        return "index";
    }
    @RequestMapping(value="user/{proId}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> GetUser(@PathVariable String proId, HttpServletRequest request,HttpServletResponse response) throws  Exception{
        List<User> list=userService.GetUser();
        Map<String, Object> modelMap = new HashMap<String, Object>(3);
        modelMap.put("total", "1");
        modelMap.put("data", list);
        modelMap.put("success", "true");
        return modelMap;
    }
    @RequestMapping(value="user",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> AddUser(@RequestBody User user,HttpServletRequest request,HttpServletResponse response) throws  Exception{
        userService.InsertUser(user);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result","success");
        return modelMap;
    }
    @RequestMapping(value="user",method = RequestMethod.PUT)
    @ResponseBody
    public  Map<String,Object>  UpdateUser(@RequestBody User user,HttpServletRequest request,HttpServletResponse response) throws  Exception{
        userService.UpdateUser(user);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        return modelMap;
    }

    @RequestMapping(value="user/{code}",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object>  DeleteUser(@PathVariable String code,HttpServletRequest request,HttpServletResponse response) throws  Exception{
        userService.DeleteUser(code);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", "success");
        return modelMap;
    }
}
