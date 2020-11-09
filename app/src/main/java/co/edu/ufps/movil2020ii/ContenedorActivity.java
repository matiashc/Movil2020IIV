package co.edu.ufps.movil2020ii;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class ContenedorActivity extends AppCompatActivity {
    TabItem tienda, mapa, perfil;
    TabLayout menu;
    ViewPager contenedor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedor);
        tienda = findViewById(R.id.tienda);
        mapa = findViewById(R.id.mapa);
        perfil = findViewById(R.id.perfil);
        menu = findViewById(R.id.menu);
        contenedor = findViewById(R.id.contenedor);
        PageAdapter pagerAdapter = new PageAdapter(getSupportFragmentManager(), menu.getTabCount());
        contenedor.setAdapter(pagerAdapter);
        contenedor.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(menu));


    }
}