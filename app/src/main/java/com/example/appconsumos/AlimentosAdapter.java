package com.example.appconsumos;

        import android.util.Log;
        import android.view.ContextMenu;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.LinearLayout;
        import android.widget.TextView;

        import androidx.recyclerview.widget.RecyclerView;

        import java.util.List;

public class AlimentosAdapter extends RecyclerView.Adapter<AlimentosAdapter.MyViewHolder> {

    private List<Alimentos> alimentosList;
    OnAlimentosListener mOnAlimentosListener;

    public class MyViewHolder extends RecyclerView.ViewHolder
            implements View.OnCreateContextMenuListener, View.OnClickListener{

        public TextView id, desc, unidad, estado;
        public LinearLayout alimentoLayout;
        OnAlimentosListener onAlimentosListener;

        public MyViewHolder(View view, OnAlimentosListener onAlimentosListener) {
            super(view);
            desc = (TextView) view.findViewById(R.id.descalimento);
            unidad = (TextView) view.findViewById(R.id.unidalimento);
            estado = (TextView) view.findViewById(R.id.estalimento);
            alimentoLayout = (LinearLayout) view.findViewById(R.id.alimentoLinearLayout);
            alimentoLayout.setOnCreateContextMenuListener(this);

            //integrar interface Listener (paso 2)
            this.onAlimentosListener = onAlimentosListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {

            contextMenu.setHeaderTitle("Selecciona una opción...");
            contextMenu.add(this.getAdapterPosition(),100,0,"Renovar");
            contextMenu.add(this.getAdapterPosition(),101,0,"Eliminar");

        }

        @Override
        public void onClick(View view) {
            //integrar Listener en evento OnClick(paso 3)
            Log.i("====>","Click en ...ALIMENTOS POSITION ADAPTER!!" + getAdapterPosition());
            onAlimentosListener.onAlimentosClick(getAdapterPosition());
        }
    }


    public AlimentosAdapter(List<Alimentos> alimentosList, OnAlimentosListener onAlimentosListener) {
        this.alimentosList = alimentosList;
        //integrar en constructor uso de Listener(paso 4)
        this.mOnAlimentosListener = onAlimentosListener;
    }

    @Override
    public AlimentosAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.alimentos_fila, parent, false);

        //integrar listener global (paso 5)
        //finalmente ir al fragment para implementar el llamado del listener onClick
        return new AlimentosAdapter.MyViewHolder(itemView, mOnAlimentosListener);
    }

    @Override
    public void onBindViewHolder(AlimentosAdapter.MyViewHolder holder, int position) {
        Alimentos alimento = alimentosList.get(position);
        holder.desc.setText(alimento.getDesc_alimento());
        holder.unidad.setText(alimento.getUnidades());
        holder.estado.setText(alimento.getEstado());

    }

    @Override
    public int getItemCount() {
        return alimentosList.size();
    }

    //crear interface Listener, onClick, en Recycler Adapter (paso 1)
    public interface OnAlimentosListener{
        void onAlimentosClick(int position);
    }
}
