package com.example.appexpetimentalnivel1;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Toast;
import com.example.objects.Cliente;
import com.example.objects.Product;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InventarioActivity extends AppCompatActivity
        implements ProductAdapter.ListItemClickListener, NumberPicker.OnValueChangeListener , ProductAdapter.BottonClickListener{

    private EditText mSearchProductEditText;
    private RecyclerView mProductList;
    private ProductAdapter mAdapter;
    private ProductAdapter mDialogAdapter;
    private Toast mToast;
    private List<Product> productList = new ArrayList<>();
    private ArrayList<Product> selectedProductList = new ArrayList<>();
    private Cliente clienteSelecionado;
    private FloatingActionButton mFloatingButton;



    void criarProdutos(String nome, int id, double price){
        productList.add(new Product(nome,id, price));
    }

    public ArrayList<Product> obterProdutosSelecionados(){
        selectedProductList.clear();
        for(Product product : productList){
            if(product.getQuantity()!=0){
                selectedProductList.add(product);
            }
        }
        return selectedProductList;
    }

    public Cliente getCliente(){
        return (Cliente) getIntent().getParcelableExtra("clienteSelecionado");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);
        Log.d("TAG", "OnCreate");
        clienteSelecionado = getCliente();
        Log.d("TAG", clienteSelecionado.getNomeCliente() + " numCliente : " + clienteSelecionado.getNumCliente());
        for(int i = 0; i< 100 ; i++){
            int numRandom10 = (int)(Math.random()*10);
            int numRandom100 = (int)(Math.random()*100);
            double doubleRandom = Math.random() * 10;

            criarProdutos("Serra " + numRandom10, numRandom100,doubleRandom);
        }



        //editText para iniciar pesquisa de produto
        mSearchProductEditText = (EditText) findViewById(R.id.et_search_inventario);
        mSearchProductEditText.setHint("Procura um produto");

        mProductList = (RecyclerView) findViewById(R.id.rv_items);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mProductList.setLayoutManager(layoutManager);
        mProductList.setHasFixedSize(true);

        mAdapter = new ProductAdapter(productList,this, this);
        mProductList.setAdapter(mAdapter);




        mFloatingButton = findViewById(R.id.floatingActionButton4);
        //actividade ao cliclar o floatingButton

        mFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                ArrayList<Product> productsSelected = obterProdutosSelecionados();
                Intent intent;
                Context context = view.getContext();
                Class activityDestino = CarrinhoActivity.class;

                intent = new Intent(context, activityDestino);

                intent.putParcelableArrayListExtra("produtosSelecionado", productsSelected);
                context.startActivity(intent);

                /*
                Context context = view.getContext();
                Button mButtonOrçamento;
                Button mVoltarInventário;
                RecyclerView mCarrinhoView;
                final Dialog mCartDialog;

                mCarrinhoView = (RecyclerView) findViewById(R.id.rv_items);
                LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                mCarrinhoView.setLayoutManager(layoutManager);
                mCarrinhoView.setHasFixedSize(true);


                //interface para o button de eliminar quantidade
                ProductAdapter.BottonClickListener buttonListener = new ProductAdapter.BottonClickListener() {
                    @Override
                    public void onButtonClick(Product product) {

                    }
                };
                //interface definir açoes de click nas views
                ProductAdapter.ListItemClickListener listener = new ProductAdapter.ListItemClickListener() {
                    @Override
                    public void onListItemClick(final Product product) {
                        //NumberPicker Dialog para definir a quantitdade de cada produto.
                        NumberPicker.OnValueChangeListener onValueChangeListener = new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                            }
                        };

                        Context context = view.getContext();
                        NumberPicker mNumberPicker;

                        final Dialog mDialog = new Dialog(context);
                        mDialog.setTitle("Quantidade");
                        mDialog.setContentView(R.layout.dialog);
                        ImageButton b1 = (ImageButton) mDialog.findViewById(R.id.button1);
                        final NumberPicker np = (NumberPicker) mDialog.findViewById(R.id.numberPicker1);
                        np.setMaxValue(100);
                        np.setMinValue(0);
                        np.setValue(product.getQuantity());
                        np.setWrapSelectorWheel(false);
                        np.setOnValueChangedListener(onValueChangeListener);
                        b1.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v) {
                                product.setQuantity(np.getValue());
                                mDialog.dismiss();
                                mAdapter.notifyDataSetChanged();
                            }

                        });
                        Log.d("TAG", "Depois do botao - Valor quantity: "+ product.getProductName() + product.getQuantity());
                        mDialog.show();

                    }
                };
                mDialogAdapter = new ProductAdapter(obterProdutosSelecionados(),listener, buttonListener);
                mCarrinhoView.setAdapter(mDialogAdapter);
                mCartDialog = new Dialog(context);
                mCartDialog.setContentView(R.layout.carrinho_dialog);
                mButtonOrçamento = (Button) mCartDialog.findViewById(R.id.button_orcamento);


                mCartDialog.show();*/
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemThatWasSelected = item.getItemId();
        if(menuItemThatWasSelected == R.id.action_search){
            Context context = InventarioActivity.this;
            String message = "Search clicked";
            Toast.makeText(context,message, Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListItemClick(final Product product) {

       //NumberPicker Dialog para definir a quantitdade de cada produto.
        NumberPicker mNumberPicker;
        final Dialog mDialog = new Dialog(this);
        mDialog.setTitle("Quantidade");
        mDialog.setContentView(R.layout.dialog);
        ImageButton b1 = (ImageButton) mDialog.findViewById(R.id.button1);
        final NumberPicker np = (NumberPicker) mDialog.findViewById(R.id.numberPicker1);
        np.setMaxValue(100);
        np.setMinValue(0);
        np.setValue(product.getQuantity());
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);
        b1.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    product.setQuantity(np.getValue());
                    mDialog.dismiss();
                    mAdapter.notifyDataSetChanged();
                }

            });
        Log.d("TAG", "Depois do botao - Valor quantity: "+ product.getProductName() + product.getQuantity());
        mDialog.show();




    }





        //String price = String.valueOf(productList.get(clickedPosition).getPrice());
       // mToast = Toast.makeText(this, "Cliente n: "+clienteSelecionado.getNumCliente() + "\nPreço da " +productList.get(clickedPosition).getProductName() + ": "+ price.substring(0,4),Toast.LENGTH_LONG);
       // mToast.show();




    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

    }

    @Override
    public void onButtonClick(Product product) {
//configuração do botao para apagar produtos
        if (product.wasReseted) {
            Log.d("TAG", "was reseted");
            //product.setQuantity(0);
            mAdapter.notifyDataSetChanged();
        }
    }





}
