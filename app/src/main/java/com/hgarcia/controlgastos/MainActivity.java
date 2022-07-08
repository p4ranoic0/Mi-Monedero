package com.hgarcia.controlgastos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.hgarcia.controlgastos.Adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
     Button mbtnRegistrar,mbtnBuscar;
    ImageButton mbtnSaldo;
     ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mbtnRegistrar = findViewById(R.id.btnRegistrar);
        mbtnBuscar = findViewById(R.id.btnBuscar);
        mbtnSaldo = findViewById(R.id.btnSaldo);

        ViewPager2 viewpager = findViewById(R.id.vpPager);
        adapter = new ViewPagerAdapter(this);
        viewpager.setAdapter(adapter);
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        mbtnRegistrar.setOnClickListener(this);
        mbtnBuscar.setOnClickListener(this);
        mbtnSaldo.setOnClickListener(this);

        new TabLayoutMediator(tabLayout, viewpager,
                (tab, position) -> {
                    //tab.setText("FRAGMENTO " + (position + 1));
                    switch (position){
                        case 0:
                            tab.setText("Todo");
                            tab.setIcon(R.drawable.ic_todo);

                            break;
                        case 1:
                            tab.setText("Gastos");
                            tab.setIcon(R.drawable.ic_gasto);
                            break;
                        case 2:
                            tab.setText("Ingresos");
                            tab.setIcon(R.drawable.ic_ingresos);
                            break;
                        default:
                            // singleChar is a consonant! Execute this code instead!
                            break;
                    }

                }
        ).attach();

    }


    @Override
    public void onClick(View view) {
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.btnBuscar:
                miIntent=new Intent(MainActivity.this,BuscarActivity.class);
                break;
            case R.id.btnRegistrar:
                miIntent=new Intent(MainActivity.this,RegistrarActivity.class);
                break;
            case R.id.btnSaldo:
                mostrarSaldo();
                break;
        }
        if (miIntent!=null){
            startActivity(miIntent);
        }
    }

    private void mostrarSaldo() {
        Datos datos = new Datos(this);
        Integer saldo = datos.mostrarSaldoTotal(datos);
        Toast.makeText(getApplicationContext(),"Su Saldo actual es: S/ "+ saldo,Toast.LENGTH_LONG).show();

    }

}