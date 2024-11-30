/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.orderdetail;

import java.io.Serializable;

/**
 *
 * @author Do Dat
 */
public class OrderDetailDTO implements Serializable{
    private int id;
    private int productId;
    private float unitPrice;
    private int quantity;
    private String orderId;
    private float total;

    public OrderDetailDTO(int productId, float unitPrice, int quantity, String orderId, float total) {
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.orderId = orderId;
        this.total = total;
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    // Getters and setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }   
}
