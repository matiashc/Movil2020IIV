package co.edu.ufps.movil2020ii;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import co.edu.ufps.movil2020ii.modelo.Tienda;

public class TiendaAdapter extends RecyclerView.Adapter<TiendaAdapter.TiendaViewHolder> {
    private Context context;
    private ArrayList<Tienda> tiendas;
    private int cursor;

    public TiendaAdapter(Context context, ArrayList<Tienda> tiendas, int cursor) {
        this.context = context;
        this.tiendas = tiendas;
        this.cursor = cursor;
    }

    @NonNull
    @Override
    public TiendaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card,parent,false);
        return new TiendaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TiendaViewHolder holder, int position) {
        Picasso.get().load(tiendas.get(position).getFoto()).into(holder.foto);
        holder.nombre.setText(tiendas.get(position).getNombre());
        holder.descripcion.setText(tiendas.get(position).getDescripcion());
        holder.horario.setText(tiendas.get(position).getHorario());
    }

    @Override
    public int getItemCount() {
        return tiendas.size();
    }

    class TiendaViewHolder extends RecyclerView.ViewHolder{
        ImageView foto, localizacion, telefono;
        TextView nombre, descripcion,horario;
        public TiendaViewHolder(@NonNull View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.foto);
            localizacion = itemView.findViewById(R.id.localizacion);
            telefono = itemView.findViewById(R.id.telefono);
            nombre = itemView.findViewById(R.id.nombre);
            descripcion = itemView.findViewById(R.id.descripcion);
            horario = itemView.findViewById(R.id.horario);
        }
    }


}
