package com.example.appconsumos;

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

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        public TextView id, desc, unidad, estado;
        public LinearLayout alimentoLayout;

        public MyViewHolder(View view) {
            super(view);
            desc = (TextView) view.findViewById(R.id.descalimento);
            unidad = (TextView) view.findViewById(R.id.unidalimento);
            estado = (TextView) view.findViewById(R.id.estalimento);
            alimentoLayout = (LinearLayout) view.findViewById(R.id.alimentoLinearLayout);
            alimentoLayout.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {

            contextMenu.setHeaderTitle("Selecciona una opción...");
            contextMenu.add(this.getAdapterPosition(),121,0,"Modificar");
            contextMenu.add(this.getAdapterPosition(),122,0,"Eliminar");

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
        holder.unidad.setText(alimento.getUnidades());
        holder.estado.setText(alimento.getEstado());
    }

    @Override
    public int getItemCount() {
        return alimentosList.size();
    }
}
