package com.hgarcia.controlgastos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class RegistrarActivity extends AppCompatActivity {
    EditText metDescripcion, metMonto;
    Switch mswGastoIngreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        metDescripcion = findViewById(R.id.etDescripcion);
        metMonto = findViewById(R.id.etMonto);
        mswGastoIngreso = findViewById(R.id.swGastoIngrso);

        mswGastoIngreso.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mswGastoIngreso.setText("Gasto");
                } else {
                    mswGastoIngreso.setText("Ingreso");
                }
            }
        });

    }


    public void Grabar(View view) {
        Integer movimiento;

        if (mswGastoIngreso.isChecked()) {
            movimiento = -1;
        } else {
            movimiento = 1;
        }

        String descripcion = metDescripcion.getText().toString();
        float monto = Float.parseFloat(metMonto.getText().toString());
        Datos datos = new Datos(this);
        long autonumerico = datos.registrarMovimiento(datos, descripcion, monto, movimiento);
        //Toast.makeText(this,String.valueOf(autonumerico),Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "Id Registro: " + autonumerico, Toast.LENGTH_LONG).show();
        metDescripcion.setText("");
        metMonto.setText("");
        metDescripcion.requestFocus();

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
