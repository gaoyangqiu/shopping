package com.car.shopping.entity;

public class TbAccount {
    private Integer id;

    private Integer acctNum;

    private Integer balance;

    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAcctNum() {
        return acctNum;
    }

    public void setAcctNum(Integer acctNum) {
        this.acctNum = acctNum;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}