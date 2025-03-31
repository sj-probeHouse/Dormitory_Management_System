package com.example.springboot.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.PayAdmitInfo;
import com.example.springboot.entity.PaymentTable;
import com.example.springboot.mapper.PaymentTableMapper;
import com.example.springboot.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentImpl extends ServiceImpl<PaymentTableMapper, PaymentTable> implements PaymentService {

    /**
     * 注入DAO层对象
     */
    @Resource
    private PaymentTableMapper paymentTableMapper;

    @Override
    public Page find(Integer pageNum, Integer pageSize, String search) {
        Page page = new Page<>(pageNum, pageSize);
        QueryWrapper<PaymentTable> qw = new QueryWrapper<>();
        qw.like("student_name",search);
        Page paymentPage = paymentTableMapper.selectPage(page,qw);
        return paymentPage;
    }

    @Override
    public PayAdmitInfo savePayment(PayAdmitInfo payAdmitInfo) {
        UpdateWrapper<PaymentTable> uw = new UpdateWrapper<>();
        uw.set("amount",payAdmitInfo.getAmount())
                .set("memo",payAdmitInfo.getMemoInfo())
                .set("is_paid",1);   //设置为未缴费状态
        paymentTableMapper.update(null,uw); //对全体进行批量更新
        return payAdmitInfo;
    }
}
