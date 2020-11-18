package co.edu.ufps.movil2020ii;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import co.edu.ufps.movil2020ii.modelo.Tienda;

public class RegistrarTiendaActivity extends AppCompatActivity {
    TextInputEditText imagen, nombre, descripcion, horario,telefono;
    Button guardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_tienda);
        imagen = findViewById(R.id.textimagen);
        nombre = findViewById(R.id.textnombre);
        descripcion = findViewById(R.id.textdescripcion);
        horario = findViewById(R.id.texthorario);
        telefono = findViewById(R.id.texthorario);
        guardar = findViewById(R.id.btguardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarTienda();
            }
        });
    }

    private void guardarTienda() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        Tienda tienda= new Tienda(myRef.push().getKey(),imagen.getText().toString(),nombre.getText().toString(),descripcion.getText().toString(),horario.getText().toString(),null,0,0,telefono.getText().toString());
        myRef.child("tiendas").child(tienda.getId()).setValue(tienda);
        finish();
    }
}