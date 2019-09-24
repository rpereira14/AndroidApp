package com.example.appexpetimentalnivel1;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.example.objects.Cliente;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>  {

    private final List<Cliente> mValues;
    private final CarrinhoFragment.OnListFragmentInteractionListener mListener;

    public MyItemRecyclerViewAdapter(List<Cliente> items, CarrinhoFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.client_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.d("TAG","Dentro do FragmentBindinging");

        boolean isSelecionado = mValues.get(position).isSelecionado();
        if (isSelecionado){
            holder.mButtonConfirmar.setVisibility(View.VISIBLE);
        } else {
            holder.mButtonConfirmar.setVisibility(View.INVISIBLE);
        }
        holder.mItem = mValues.get(position);
        final String numCliente = String.valueOf(mValues.get(position).getNumCliente());
        holder.mIdView.setText(numCliente);
        Log.d("TAG",numCliente);
        holder.mContentView.setText(mValues.get(position).getNomeCliente());
        holder.mButtonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent;
                Context context = v.getContext();
                Class activityDestino = InventarioActivity.class;

                intent = new Intent(context, activityDestino);
                intent.putExtra("clienteSelecionado", (Parcelable) mValues.get(position));
                context.startActivity(intent);

              //  mValues.get(position).setSelecionado(true);


            }
        });


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final Button mButtonConfirmar;

        public Cliente mItem;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
            mButtonConfirmar = (Button) view.findViewById(R.id.button_confirm_cliente);
        }



        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
