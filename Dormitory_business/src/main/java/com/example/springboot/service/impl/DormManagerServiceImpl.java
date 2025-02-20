package com.example.springboot.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.DormManager;
import com.example.springboot.entity.PaymentTable;
import com.example.springboot.mapper.DormManagerMapper;
import com.example.springboot.mapper.PaymentTableMapper;
import com.example.springboot.service.DormManagerService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.math.BigDecimal;


@Service
public class DormManagerServiceImpl extends ServiceImpl<DormManagerMapper, DormManager> implements DormManagerService {

    /**
     * 注入DAO层对象
     */
    @Resource
    private DormManagerMapper dormManagerMapper;

    private PaymentTableMapper paymentTableMapper; //注入 PaymentTableMapper

    /**
     * 宿管登录
     */
    @Override
    public DormManager dormManagerLogin(String username, String password) {
        QueryWrapper<DormManager> qw = new QueryWrapper<>();
        qw.eq("username", username);
        qw.eq("password", password);
        DormManager dormManager = dormManagerMapper.selectOne(qw);
        if (dormManager != null) {
            return dormManager;
        } else {
            return null;
        }
    }

    /**
     * 宿管新增
     */
    @Override
    public int addNewDormManager(DormManager dormManager) {
        int insert = dormManagerMapper.insert(dormManager);
        return insert;
    }

    /**
     * 宿管查找
     */
    @Override
    public Page find(Integer pageNum, Integer pageSize, String search) {
        Page page = new Page<>(pageNum, pageSize);
        QueryWrapper<DormManager> qw = new QueryWrapper<>();
        qw.like("name", search);
        Page dormManagerPage = dormManagerMapper.selectPage(page, qw);
        return dormManagerPage;
    }

    /**
     * 宿管信息更新
     */
    @Override
    public int updateNewDormManager(DormManager dormManager) {
        int i = dormManagerMapper.updateById(dormManager);
        return i;
    }
    /**
     * 宿管删除
     */
    @Override
    public int deleteDormManager(String username) {
        int i = dormManagerMapper.deleteById(username);
        return i;
    }

    /**
     * 宿管发布宿舍缴费金额
     * @param newAmount
     * @return
     */
    @Override
    public int sendPayForStu(BigDecimal newAmount) {
        UpdateWrapper<PaymentTable> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("amount",newAmount).set("is_paid",1);  //更新为未缴费状态
        int rowsAffected = paymentTableMapper.update(null,updateWrapper); //更新操作影响的行数
        if (rowsAffected > 0){
            System.out.println("更新成功，影响行数：" + rowsAffected);
            return 1;
        }else{
            System.out.println("更新失败，未找到匹配的记录");
            return 0;
        }
    }


}
