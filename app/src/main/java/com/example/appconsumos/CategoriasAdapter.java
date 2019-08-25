package com.example.appconsumos;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class CategoriasAdapter extends RecyclerView.Adapter<CategoriasAdapter.MyViewHolder> {

    private List<Categorias> categoriasList;
    OnCategoriasListener mOnCategoriasListener;

    public class MyViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        public TextView id, desc;
        OnCategoriasListener onCategoriasListener;

        public MyViewHolder(View view, OnCategoriasListener onCategoriasListener) {
            super(view);
            desc = (TextView) view.findViewById(R.id.desccategoria);

            //integrar interface Listener (paso 2)
            this.onCategoriasListener = onCategoriasListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //integrar Listener en evento OnClick(paso 3)
            Log.i("====>","Click en ...CATEGORIAS POSITION ADAPTER!!" + getAdapterPosition());
            onCategoriasListener.onCategoriasClick(getAdapterPosition());
        }
    }


    public CategoriasAdapter(List<Categorias> categoriasList, OnCategoriasListener onCategoriasListener) {
        this.categoriasList = categoriasList;
        //integrar en constructor uso de Listener(paso 4)
        this.mOnCategoriasListener = onCategoriasListener;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categorias_fila, parent, false);

        //integrar listener global (paso 5)
        //finalmente ir al fragment para implementar el llamado del listener onClick
        return new MyViewHolder(itemView, mOnCategoriasListener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Categorias categoria = categoriasList.get(position);
        holder.desc.setText(categoria.getDesc_categoria());
    }

    @Override
    public int getItemCount() {
        return categoriasList.size();
    }

    //crear interface Listener, onClick, en Recycler Adapter (paso 1)
    public interface OnCategoriasListener{
        void onCategoriasClick(int position);
    }

    public void setFilter(List<Categorias> newsArrayList) {
        categoriasList.clear();
        categoriasList.addAll(newsArrayList);
        notifyDataSetChanged();
    }

}
