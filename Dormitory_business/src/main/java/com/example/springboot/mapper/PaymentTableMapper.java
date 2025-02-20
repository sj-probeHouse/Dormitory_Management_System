package com.example.springboot.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.PaymentTable;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentTableMapper extends BaseMapper<PaymentTable> {
}
