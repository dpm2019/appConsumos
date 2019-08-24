package com.example.appconsumos;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SupermercadosAdapter extends RecyclerView.Adapter<SupermercadosAdapter.MyViewHolder> {

    private List<Supermercados> smercadosList;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{

        //crear variable para mostrarlos en cada fila del recyclerview
        public TextView id, desc;
        //crear variable para menu contextual de recyclerview
        public LinearLayout supmercadosLayout;


        public MyViewHolder(View view) {
            super(view);
            desc = (TextView) view.findViewById(R.id.descsupermercado);
            supmercadosLayout = (LinearLayout) view.findViewById(R.id.supmercadosLinearLayout);
            supmercadosLayout.setOnCreateContextMenuListener(this);
        }

        //metodo del implements View.OnCreateContextMenuListener
        //la funcion de este metodo es dibujar el menu contextual en el recyclerview
        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("Selecciona una opción...");
            contextMenu.add(this.getAdapterPosition(),200,0,"Llamar");
            contextMenu.add(this.getAdapterPosition(),201,0,"Ver ubicación en Maps");

        }
    }


    public SupermercadosAdapter(List<Supermercados> smercadosList) {
        this.smercadosList = smercadosList;
    }

    @Override
    public SupermercadosAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                //Antes de existir esta clase adapter debe existir el archivo fila xml que servira para el recyclerview
                .inflate(R.layout.supermercados_fila, parent, false);

        return new SupermercadosAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(SupermercadosAdapter.MyViewHolder holder, int position) {
        Supermercados smercado = smercadosList.get(position);
        holder.desc.setText(smercado.getDesc_smercado());
    }

    @Override
    public int getItemCount() {
        return smercadosList.size();
    }
}
