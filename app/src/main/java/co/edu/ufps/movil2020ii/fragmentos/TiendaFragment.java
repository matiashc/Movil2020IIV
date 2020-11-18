package co.edu.ufps.movil2020ii.fragmentos;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import co.edu.ufps.movil2020ii.R;
import co.edu.ufps.movil2020ii.RegistrarTiendaActivity;
import co.edu.ufps.movil2020ii.TiendaAdapter;
import co.edu.ufps.movil2020ii.modelo.Tienda;

public class TiendaFragment extends Fragment {
    private static final String TAG = "TiendaFragment";
    RecyclerView contenedorTienda;
    FloatingActionButton registrarTienda;
    TiendaAdapter tiendaAdapter;

    public TiendaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tienda, container, false);
        contenedorTienda = view.findViewById(R.id.contenedortienda);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
        linearLayout.setOrientation(LinearLayoutManager.VERTICAL);
        contenedorTienda.setLayoutManager(linearLayout);
        tiendaAdapter = new TiendaAdapter(getActivity(),cargarDatosFireBase(),R.id.card);
        contenedorTienda.setAdapter(tiendaAdapter);
        registrarTienda = view.findViewById(R.id.registrarTienda);
        registrarTienda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irRegistrarTienda();
            }
        });
        return view;
    }

    private void irRegistrarTienda() {
        Intent intent = new Intent(getActivity(), RegistrarTiendaActivity.class);
        startActivity(intent);
    }

    //objetos propios
   public ArrayList<Tienda> cargarDatos(){
        ArrayList<Tienda> tiendas = new ArrayList<>();
        tiendas.add(new Tienda("1","https://www.eluniversal.com.co/binrepository/1050x700/0c0/0d0/none/13704/TNTI/image_content_3023679_20200427114131.jpg","Don Pedro","Viveres","8:00-9:00","Av 10",0,0,"5698794"));
        tiendas.add(new Tienda("2","https://www.dominoprofesional.com/wp-content/uploads/2019/02/Los-nombres-de-tiendas-de-barrio-que-todos-en-Guatemala-han-visto-alguna-vez-885x500.jpg","Don Antonio","Viveres","8:00-9:00","Av 10",0,0,"5698794"));
        tiendas.add(new Tienda("3","https://blog.oxfamintermon.org/wp-content/uploads/2014/11/tienda-solidaria-comercio-justo-oxfam-intermon.jpg","Don Juan","Viveres","8:00-9:00","Av 10",0,0,"5698794"));
        return tiendas;
    }
// cargar de Firebase consulta
    public ArrayList<Tienda> cargarDatosFireBase(){
        final ArrayList<Tienda> tiendas = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.child("tiendas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if (dataSnapshot.exists()) {
                    tiendas.clear();
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Tienda tienda = data.getValue(Tienda.class);
                        tiendas.add(tienda);
                        tiendaAdapter.notifyDataSetChanged();
                    }
                    //Log.d(TAG, "Value is: ");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        return tiendas;
    }
}