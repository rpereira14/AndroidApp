package com.example.appexpetimentalnivel1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.objects.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteActivity extends AppCompatActivity implements CarrinhoFragment.OnListFragmentInteractionListener {

    MyItemRecyclerViewAdapter mAdapter;
    RecyclerView mClientList;
    private List<Cliente> listaClientes = new ArrayList<>();
    FrameLayout currentLayout;




    //metodo para criar clientes aleatorios
    void addClientes(String nome, int num){
        listaClientes.add(new Cliente(nome, num));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        //instaciar um layout
        currentLayout = (FrameLayout) findViewById(R.id.viewholder);


        mClientList = (RecyclerView) findViewById(R.id.rv_clients);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mClientList.setLayoutManager(layoutManager);
        mClientList.setHasFixedSize(true);

        mAdapter = new MyItemRecyclerViewAdapter(listaClientes,this);
        mClientList.setAdapter(mAdapter);
    }

    public ClienteActivity(){
        addClientes("Cliente 1", 112);
        for(int i = 0; i<30;i++){
            int randomNum = (int) (Math.random() * 100);
            addClientes("Jonas",randomNum);}
    }

    @Override
    public void onListFragmentInteraction(Cliente cliente) {
        Log.d("TAG","Clique Teste");
        MyItemRecyclerViewAdapter.ViewHolder viewHolder;
        //Button mButtonConfirmar;


        if(!(cliente.isSelecionado())){
            cliente.setSelecionado(true);

            for (Cliente clienteFor : listaClientes){
                clienteFor.setSelecionado(false);
            }
            cliente.setSelecionado(true);
            mAdapter.notifyDataSetChanged();

        } else {
            mAdapter.notifyDataSetChanged();
            cliente.setSelecionado(false);

        }

    }
}
