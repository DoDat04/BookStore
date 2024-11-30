/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.cart;

import datdt.product.ProductDTO;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Do Dat
 */
public class CartBean implements Serializable {
    private Map<Integer, ProductDTO> items;

    public Map<Integer, ProductDTO> getItems() {
        return items;
    }

    public void addItemTocart(ProductDTO item) {
        if (item == null) {
            return;
        }
        //1. Check whether Cart existed
        if (this.items == null) {
            this.items = new HashMap<>();
        }// Cart has not existed
        int sku = item.getSku();
        int quantity = item.getQuantity();
        //2. Check whether item existed
        if (this.items.containsKey(sku)) {
            quantity += this.items.get(sku).getQuantity();
        }// item has already existed in cart
        item.setQuantity(quantity);
        //3. Drop an item into cart
        this.items.put(sku, item);
    }

    public void removeItemFromCart(String item) {
        if (item == null) {
            return;
        }

        if (item.trim().isEmpty()) {
            return;
        }
        //1.check existed items
        if (this.items == null) {
            return;
        }
        int sku = Integer.parseInt(item);
        //2.check existed item
        if (this.items.containsKey(sku)) {
            //remove item from items
            this.items.remove(sku);
            if (this.items.isEmpty()) {
                this.items = null; // neu items khong chua item thi tra ve null khong can ktra size
            }
        }
        //3.remove item from items
    }

    public int getTotalQuantity() {
        int totalQuantity = 0;
        if (items != null) {
            for (ProductDTO product : items.values()) {
                totalQuantity += product.getQuantity();
            }
        }
        return totalQuantity;
    }

    public float getTotalPrice() {
        float totalPrice = 0;
        if (items != null) {
            for (ProductDTO product : items.values()) {
                totalPrice += product.getPrice() * product.getQuantity();
            }
        }
        return totalPrice;
    }
}
