package com.hgarcia.controlgastos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ConsultarActivity extends AppCompatActivity implements View.OnClickListener {
Button mbtModificar,mbtEliminar,mbtnSave;
String idMovi,fecha,descripcion,monto;
Integer movimiento;
EditText metDescripcion, metmonto;
Spinner mspmovimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        Bundle bundle = getIntent().getExtras();
        idMovi = bundle.getString("idmovimiento");
        descripcion = bundle.getString("descripcion");
        fecha = bundle.getString("fecha");
        monto = bundle.getString("monto");
        movimiento = Integer.valueOf(bundle.getString("movimiento"));

        TextView mtvid = findViewById(R.id.tvIdConsulta);
        mtvid.setText(idMovi);
        TextView mtvmonto = findViewById(R.id.tvMonto);
        TextView mtvmovimiento = findViewById(R.id.tvmovimiento);

        mtvmonto.setText("S/ "+monto);
        if (movimiento< 0){
            mtvmovimiento.setText("Gasto");
            //mtvmovimiento.setTextColor(Color.MAGENTA);
        }else {
            mtvmovimiento.setText("Ingreso");
            //mtvmovimiento.setTextColor(Color.CYAN);
        }

        this.setTitle("Detalle Movimiento");
        TextView mtvDescripcion = findViewById(R.id.tvDescripcion);
        mtvDescripcion.setText(descripcion);

        TextView mtvFecha = findViewById(R.id.tvFecha);
        mtvFecha.setText(fecha);
        mbtModificar = findViewById(R.id.btnModificar1);
        mbtEliminar = findViewById(R.id.btnEliminar1);

        mbtModificar.setOnClickListener(this);
        mbtEliminar.setOnClickListener(this);


        metDescripcion = findViewById(R.id.etDescripcion);
        metmonto = findViewById(R.id.etMonto);
        mspmovimiento = findViewById(R.id.spinnerElegir);
        mbtnSave = findViewById(R.id.btnSave);
        mbtnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnModificar1:
                modificar();
                break;

            case R.id.btnEliminar1:
                eliminarRegistro();
                break;

            case R.id.btnSave:
                grabar();
                break;

        }


    }

    private void grabar() {
         Datos datos = new Datos(this);
        String id = idMovi;
        String seleccion = mspmovimiento.getSelectedItem().toString();
        int movi = 0;

        if(seleccion.equals("Ingresos")){
            movi = 1;
        }else if(seleccion.equals("Gastos")){
            movi = -1;
        }
        String desc = metDescripcion.getText().toString();
        float montos = Float.parseFloat(metmonto.getText().toString());
        long autonumerico = datos.modificarDatos(datos,id,desc,montos,movi);
        Toast.makeText(getApplicationContext(),"Registro Actualizado",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void eliminarRegistro() {
        Datos datos = new Datos(this);
        long autonumerico = datos.eliminarDatos(datos,idMovi);
        Toast.makeText(getApplicationContext(),"Registro N° "+ idMovi+" Eliminado",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    private void modificar() {

        metDescripcion.setVisibility(View.VISIBLE);
        metmonto.setVisibility(View.VISIBLE);
        mspmovimiento.setVisibility(View.VISIBLE);
        mbtnSave.setVisibility(View.VISIBLE);

        metDescripcion.setText(descripcion);
        metmonto.setText(monto);

        if (movimiento == 1){
            String [] movi = {"Ingresos","Gastos"};
            ArrayAdapter<String> adapter = new ArrayAdapter <String>(this,android.R.layout.simple_spinner_item,movi);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mspmovimiento.setAdapter(adapter);
        }else{
            String [] movi = {"Gastos","Ingresos"};
            ArrayAdapter<String> adapter = new ArrayAdapter <String>(this,android.R.layout.simple_spinner_item,movi);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mspmovimiento.setAdapter(adapter);
        }
    }
}