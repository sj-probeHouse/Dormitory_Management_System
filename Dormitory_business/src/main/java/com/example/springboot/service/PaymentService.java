package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.PayAdmitInfo;
import com.example.springboot.entity.PaymentTable;

public interface PaymentService extends IService<PaymentTable> {
    //查询缴费信息
    Page find(Integer pageNum, Integer pageSize, String search);

    //宿管添加缴费信息
    PayAdmitInfo savePayment(PayAdmitInfo payAdmitInfo);

}
