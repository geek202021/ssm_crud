package com.jun.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jun.bean.Employee;
import com.jun.bean.Msg;
import com.jun.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author HuangJun7
 * @date 2021-11-08 18:18
 */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/emps")
    @ResponseBody
    //自动将返回的对象转为JSON字符串（springmvc）,需导入jackson包，负责将PageInfo对象转换成json字符串
    public Msg getEmpsWithJson(@RequestParam(value = "pn", defaultValue = "1")Integer pn) {
        PageHelper.startPage(pn, 5);
        List<Employee> emps =  employeeService.getAll();
        PageInfo page = new PageInfo(emps,5);
        return Msg.success().add("pageInfo",page);
    }

    /**
     *查询员工数据：分页查询
     */
    //@RequestMapping("/emps")
    public String getEmps(@RequestParam(value="pn",defaultValue = "1")Integer pn, Model model) {
        //引入PageHelper分页插件,查询之前只需要调用startPage(pageNum,pageSize)
        PageHelper.startPage(pn, 5);
        //startPage后面跟着的这个查询就是一个分页查询
        List<Employee> emps =  employeeService.getAll();
        //使用pageInfo包装查询后的结果
        //PageInfo就是一个分页Bean
        PageInfo page = new PageInfo(emps,5);
        model.addAttribute("pageInfo", page);
        return "list";
    }
}
