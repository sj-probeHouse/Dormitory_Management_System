package com.example.springboot.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PayAdmitInfo {
    private BigDecimal amount; //缴费金额
    private String memoInfo; //备注
}
