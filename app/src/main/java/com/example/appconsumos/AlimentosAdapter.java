package com.example.appconsumos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AlimentosAdapter extends RecyclerView.Adapter<AlimentosAdapter.MyViewHolder> {

    private List<Alimentos> alimentosList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView id, desc;

        public MyViewHolder(View view) {
            super(view);
            desc = (TextView) view.findViewById(R.id.descalimento);
        }
    }


    public AlimentosAdapter(List<Alimentos> alimentosList) {
        this.alimentosList = alimentosList;
    }

    @Override
    public AlimentosAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.alimentos_fila, parent, false);

        return new AlimentosAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(AlimentosAdapter.MyViewHolder holder, int position) {
        Alimentos alimento = alimentosList.get(position);
        holder.desc.setText(alimento.getDesc_alimento());
    }

    @Override
    public int getItemCount() {
        return alimentosList.size();
    }
}