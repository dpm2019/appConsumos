package com.example.appconsumos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class CategoriasAdapter extends RecyclerView.Adapter<CategoriasAdapter.MyViewHolder> {

    private List<Categorias> categoriasList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView id, desc;

        public MyViewHolder(View view) {
            super(view);
            desc = (TextView) view.findViewById(R.id.desccategoria);
        }
    }


    public CategoriasAdapter(List<Categorias> categoriasList) {
        this.categoriasList = categoriasList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categorias_fila, parent, false);

        return new MyViewHolder(itemView);
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
}
