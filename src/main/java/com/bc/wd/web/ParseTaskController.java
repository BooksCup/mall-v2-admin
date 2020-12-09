//package com.bc.wd.web;
//
//import com.bc.wd.service.ParseTaskService;
//import com.bc.wd.utils.BaseResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
///**
// * @program: whl-project
// * @description:
// * @author: Mr.Wang
// * @create: 2020-04-23 12:00
// **/
//@RestController
//@RequestMapping("parse")
//public class ParseTaskController {
//
//    @Resource
//    private ParseTaskService parseTaskService;
//
//    @GetMapping("jd")
//    public BaseResult parseJdItems(
//            @RequestParam("start") int start,
//            @RequestParam("end") int end,
//            @RequestParam("keyword") String keyword
//    ){
//        try {
//            parseTaskService.processJd(start,end,keyword);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
