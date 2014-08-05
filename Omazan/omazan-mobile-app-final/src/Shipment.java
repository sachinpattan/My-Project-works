// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Shipment.java

import java.io.Serializable;

public class Shipment
    implements Serializable
{

    public Shipment()
    {
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public int getShipmentId()
    {
        return shipmentId;
    }

    public void setShipmentId(int shipmentId)
    {
        this.shipmentId = shipmentId;
    }

    public int getOrderId()
    {
        return orderId;
    }

    public void setOrderId(int orderId)
    {
        this.orderId = orderId;
    }

    public String getShipmentPosition()
    {
        return shipmentPosition;
    }

    public void setShipmentPosition(String shipmentPosition)
    {
        this.shipmentPosition = shipmentPosition;
    }

    public int getTruckId()
    {
        return truckId;
    }

    public void setTruckId(int truckId)
    {
        this.truckId = truckId;
    }

    private int shipmentId;
    private int orderId;
    private int truckId;
    private String shipmentPosition;
    private String status;
}
