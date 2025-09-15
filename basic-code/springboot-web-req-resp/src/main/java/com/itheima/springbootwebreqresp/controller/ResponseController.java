package com.itheima.springbootwebreqresp.controller;

import com.itheima.springbootwebreqresp.pojo.Address;
import com.itheima.springbootwebreqresp.pojo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@ResponseBody
@Controller
public class ResponseController {
/*    @RequestMapping("/hello")
    public String hello() {
        System.out.println("Hello World!!!");
        return "Hello World~";
    }

    @RequestMapping("/getAddress")
    public Address getAddress() {
        Address address = new Address();
        address.setCity("武汉");
        address.setProvince("湖北");
        return address;
    }

    @RequestMapping("/listAddress")
    public List<Address> listAddress(){
        List<Address> list = new ArrayList<>();
        Address first = new Address();
        first.setProvince("湖南");
        first.setCity("长沙");

        Address second = new Address();
        second.setProvince("江西");
        second.setCity("南昌");

        list.add(first);
        list.add(second);

        return list;
    }*/


    @RequestMapping("/hello")
    public Result hello() {
        System.out.println("Hello World!!!");
//        return new Result(1,"success","Hello World!!!");
        return Result.success("Hello World!!!");
    }

    @RequestMapping("/getAddress")
    public Result getAddress() {
        Address address = new Address();
        address.setCity("武汉");
        address.setProvince("湖北");
//        return address;
        return Result.success(address);
    }

    @RequestMapping("/listAddress")
    public Result listAddress(){
        List<Address> list = new ArrayList<>();
        Address first = new Address();
        first.setProvince("湖南");
        first.setCity("长沙");

        Address second = new Address();
        second.setProvince("江西");
        second.setCity("南昌");

        list.add(first);
        list.add(second);

//        return list;
        return Result.success(list);
    }
}
