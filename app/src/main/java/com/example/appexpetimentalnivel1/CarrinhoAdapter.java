package com.example.appexpetimentalnivel1;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.objects.Product;

import java.util.List;

public class CarrinhoAdapter extends RecyclerView.Adapter<CarrinhoAdapter.ProductViewHolder>{

    final private List<Product> mDataset;

    final private ListItemClickListener mOnClickListener;

    final private BottonClickListener mBottonListener;

    public interface BottonClickListener{
        void onButtonClick(Product product);
    }

    public interface ListItemClickListener{
        void onListItemClick(Product product);
    }

    public CarrinhoAdapter(List<Product> productList, ListItemClickListener listener, BottonClickListener buttonListener) {
        Log.d("TAG","Dentro do ProductAdapter");
        mDataset = productList;
        mOnClickListener = listener;
        mBottonListener = buttonListener;

    }

    //metodo para receber o numberpicker selecionado na intent

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        Log.d("TAG","Dentro do onCreateViewHolder");
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_item, parent, false);

        ProductViewHolder vh = new ProductViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ProductViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element


        final String quantityString = String.valueOf(mDataset.get(position).getQuantity());
        holder.mProduct = mDataset.get(position);

        if(holder.mProduct.getQuantity() == 0){
            holder.mRemoveButton.setVisibility(View.INVISIBLE);
        } else {
            holder.mRemoveButton.setVisibility(View.VISIBLE);
        }
        holder.mQuantity.setText(quantityString);
        holder.mProductName.setText(mDataset.get(position).getProductName());
        holder.mRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(null != mOnClickListener) {
                   holder.mProduct.resetQuantity(mDataset.get(position));


                   Log.d("TAG", "botao apagar carregado.");
                  if(null!= mBottonListener){
                      Log.d("TAG", "m<listener");
                      mBottonListener.onButtonClick(holder.mProduct);
                  }
               }
            }
        }
        );

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnClickListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mOnClickListener.onListItemClick(holder.mProduct);
                }
            }
        });

    }



    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class ProductViewHolder extends RecyclerView.ViewHolder
            implements 	NumberPicker.OnValueChangeListener{
        // each data item is just a string in this case
        public final View mView;
        TextView mProductName;
        TextView mQuantity;
        ImageButton mRemoveButton;
        Product mProduct;





        public ProductViewHolder(View v) {
            super(v);


            mView = v;
            mProductName = (TextView) v.findViewById(R.id.tv_product_name);
            mQuantity = (TextView) v.findViewById(R.id.tv_product_quantity);
            mRemoveButton = (ImageButton) v.findViewById(R.id.button_remove_product);


        }

         @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)

    // Create new views (invoked by the layout manager)




    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}










   /* public int show(View view, final List<Product> productList, final int position)
    {

        Context context = view.getContext();
        final Dialog d = new Dialog(context);
        d.setTitle("Quantidade");
        d.setContentView(R.layout.dialog);
        // ImageButton b1 = (ImageButton) d.findViewById(R.id.button1);

        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(100);
        np.setMinValue(0);
        np.setValue(10);
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);
        Log.d("TAG", "Antes do botão - Valor quantity: "+ productList.get(position).getProductName() + productList.get(position).getQuantity());
         /*   b1.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    Log.d("TAG", "Fez algo!!");

                    productList.get(position).setQuantity(np.getValue());

                    newQuantity[0] = np.getValue();
                    Log.d("TAG", "Valor quantity: "+ productList.get(position).getProductName() + productList.get(position).getQuantity());
                    // tv.setText(String.valueOf(np.getValue()));

                    d.dismiss();

                }

            });
        Log.d("TAG", "Depois do botao - Valor quantity: "+ productList.get(position).getProductName() + productList.get(position).getQuantity());
        d.show();
        Log.d("TAG", String.valueOf(newQuantity[0]));
        return newQuantity[0];


    }*/


