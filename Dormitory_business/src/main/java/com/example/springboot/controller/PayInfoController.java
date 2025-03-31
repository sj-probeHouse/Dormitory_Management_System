package com.example.springboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.PayAdmitInfo;
import com.example.springboot.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/pay")
public class PayInfoController {

    @Resource
    private PaymentService paymentService;

    @PostMapping("/savePayment")
    public Result<?> savePayment(@RequestBody PayAdmitInfo payAdmitInfo){
        PayAdmitInfo savePayment = paymentService.savePayment(payAdmitInfo);
        if (savePayment != null) {
            return Result.success(savePayment);
        } else {
            return Result.error("-1", "更新失败");
        }
    }

    @GetMapping("/find")
    public Result<?> findPayInfo(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        Page page = paymentService.find(pageNum, pageSize, search);
        if (page != null) {
            return Result.success(page);
        } else {
            return Result.error("-1", "查询失败");
        }
    }


}
