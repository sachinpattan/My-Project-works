// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Order.java

import java.io.Serializable;
import java.sql.Timestamp;

public class Order
    implements Serializable
{

    public Order()
    {
    }

    public double getOrderTotal()
    {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal)
    {
        this.orderTotal = orderTotal;
    }

    public Timestamp getOrderPlacedTime()
    {
        return orderPlacedTime;
    }

    public void setOrderPlacedTime(Timestamp orderPlacedTime)
    {
        this.orderPlacedTime = orderPlacedTime;
    }

    public int getOrderId()
    {
        return orderId;
    }

    public void setOrderId(int orderId)
    {
        this.orderId = orderId;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public int getAddressId()
    {
        return addressId;
    }

    public void setAddressId(int addressId)
    {
        this.addressId = addressId;
    }

    private int orderId;
    private int userId;
    private String status;
    private int addressId;
    private double orderTotal;
    private Timestamp orderPlacedTime;
}
