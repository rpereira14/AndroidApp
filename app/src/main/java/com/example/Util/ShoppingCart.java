package com.example.Util;

import com.example.objects.Product;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private Map<Product, Integer> produtosCarrinho = new HashMap<>();


    public void addItem(Product produto, Integer quantity){
        produtosCarrinho.put(produto, quantity);


    }

    public void removeProduct (Product produto) {
        produtosCarrinho.remove(produto);
    }

    public Map<Product,Integer> getCart(){
        return produtosCarrinho;
    }

    public void resetCart(){
        produtosCarrinho = new HashMap<>();
    }

}
