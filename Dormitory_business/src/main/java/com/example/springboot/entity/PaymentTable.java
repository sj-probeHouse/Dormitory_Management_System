package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("payment_table") // 指定表名
public class PaymentTable {
    @TableId(value = "id", type = IdType.AUTO) // 主键自增
    private Integer id;

    private String studentName;
    private BigDecimal amount;
    private int isPaid;
}
