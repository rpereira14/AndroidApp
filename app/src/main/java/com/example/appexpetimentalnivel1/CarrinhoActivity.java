package com.example.appexpetimentalnivel1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.objects.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CarrinhoActivity extends AppCompatActivity implements
        CarrinhoAdapter.ListItemClickListener, CarrinhoAdapter.BottonClickListener{

    private ArrayList<Product> selectedProductList = new ArrayList<>();
    private RecyclerView mProductList;
    private CarrinhoAdapter mAdapter;
    private TextView mFinalPrice;



    public ArrayList<Product> getProdutosSelecionados(){
        return getIntent().getParcelableArrayListExtra("produtosSelecionado");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        selectedProductList = getProdutosSelecionados();

        mProductList = (RecyclerView) findViewById(R.id.rv_carrinho);

        //LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mProductList.setLayoutManager(new LinearLayoutManager(this));
        mProductList.setHasFixedSize(true);

        mAdapter = new CarrinhoAdapter(selectedProductList,this, this);
        mProductList.setAdapter(mAdapter);

        mFinalPrice = (TextView) findViewById(R.id.tv_preco_final);
        mFinalPrice.setText("Total: " + calculatePrice());

    }

    protected double calculatePrice(){
       double sum = 0.0;
        for(Product product : selectedProductList) {

           double productPrice = product.getPrice();
           sum += productPrice;
       }
       return sum;
    }


    @Override
    public void onButtonClick(Product product) {

    }

    @Override
    public void onListItemClick(Product product) {

    }
}
