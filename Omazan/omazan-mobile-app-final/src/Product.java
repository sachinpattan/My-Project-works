// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Product.java

import java.io.Serializable;

public class Product
    implements Serializable
{

    public Product()
    {
        productOrdered = false;
    }

    public String getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(String categoryId)
    {
        this.categoryId = categoryId;
    }

    public String getImageURL()
    {
        return imageURL;
    }

    public void setImageURL(String imageURL)
    {
        this.imageURL = imageURL;
    }

    public int getProductId()
    {
        return productId;
    }

    public void setProductId(int productId)
    {
        this.productId = productId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public String getStringProductId()
    {
        stringProductId = (new StringBuilder()).append(productId).toString();
        return stringProductId;
    }

    public boolean isProductOrdered()
    {
        return productOrdered;
    }

    public void setProductOrdered(boolean productOrdered)
    {
        this.productOrdered = productOrdered;
    }

    private int productId;
    private String categoryId;
    private String imageURL;
    private String name;
    private String description;
    private int price;
    private int quantity;
    private String stringProductId;
    private boolean productOrdered;
}
