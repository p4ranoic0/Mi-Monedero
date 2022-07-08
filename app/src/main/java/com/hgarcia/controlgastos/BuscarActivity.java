package com.hgarcia.controlgastos;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class BuscarActivity extends AppCompatActivity implements View.OnClickListener {
    Button mbtBuscar,mbtModificar,mbtEliminar;
    EditText metBuscar,metNombre,metPrecio;
    Spinner mspElegir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        mbtBuscar = findViewById(R.id.btnBuscar);
        mbtModificar = findViewById(R.id.btnModificar);
        mbtEliminar = findViewById(R.id.btnEliminar);
        mspElegir =findViewById(R.id.spinnerElegir);
        metBuscar =findViewById(R.id.etBuscar);
        metNombre = findViewById(R.id.etNombre);
        metPrecio =findViewById(R.id.etPrecio);
        mbtBuscar.setOnClickListener(this);
        mbtModificar.setOnClickListener(this);
        mbtEliminar.setOnClickListener(this);



         }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnBuscar:
                consultar();
                break;
            case R.id.btnModificar:
                modificar();
                break;

            case R.id.btnEliminar:
                eliminar();
                break;
        }
    }

    private void eliminar() {
        String id = metBuscar.getText().toString();
        Datos datos = new Datos(this);
        long autonumerico = datos.eliminarDatos(datos,id);
        Toast.makeText(getApplicationContext(),"Registro Eliminado",Toast.LENGTH_LONG).show();

        limpiar();
    }

    private void modificar() {
        String id = metBuscar.getText().toString();
        String seleccion = mspElegir.getSelectedItem().toString();
        int movimiento = 0;

        if(seleccion.equals("Ingresos")){
            movimiento = 1;
        }else if(seleccion.equals("Gastos")){
            movimiento = -1;
        }
        String descripcion = metNombre.getText().toString();
        float monto = Float.parseFloat(metPrecio.getText().toString());
        Datos datos = new Datos(this);
        long autonumerico = datos.modificarDatos(datos,id,descripcion,monto,movimiento);
        Toast.makeText(getApplicationContext(),"Registro Actualizado",Toast.LENGTH_LONG).show();
        limpiar();

    }

    private void consultar() {

        String id = metBuscar.getText().toString();

        try {
            Datos datos = new Datos(this);
            Cursor cursor = datos.consultarDatos(datos,id);
            cursor.moveToFirst();
            metNombre.setText(cursor.getString(2));
            metPrecio.setText(cursor.getString(3));
            final Integer  sw = cursor.getInt(4);

            if (sw ==-1){
                Toast.makeText(getApplicationContext(), "Gasto",Toast.LENGTH_SHORT).show();
                String [] movi = {"Gastos","Ingresos"};
                ArrayAdapter<String> adapter = new ArrayAdapter <String>(this,android.R.layout.simple_spinner_item,movi);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mspElegir.setAdapter(adapter);
            }
            else if (sw ==1){
                Toast.makeText(getApplicationContext(), "Ingreso",Toast.LENGTH_SHORT).show();
                String [] movi = {"Ingresos","Gastos"};
                ArrayAdapter<String> adapter = new ArrayAdapter <String>(this,android.R.layout.simple_spinner_item,movi);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mspElegir.setAdapter(adapter);
            }

            cursor.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El movimiento no existe",Toast.LENGTH_SHORT).show();
            limpiar();
        }

    }

    private void limpiar() {
        metBuscar.setText("");
        metNombre.setText("");
        metPrecio.setText("");
        metBuscar.requestFocus();
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        // Añade más funciones si fuese necesario
        super.onBackPressed();  // Invoca al método
    }
}