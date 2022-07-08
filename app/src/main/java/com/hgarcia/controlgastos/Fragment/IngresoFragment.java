package com.hgarcia.controlgastos.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hgarcia.controlgastos.Adapter.ListAdapterMovimiento;
import com.hgarcia.controlgastos.ConsultarActivity;
import com.hgarcia.controlgastos.Datos;
import com.hgarcia.controlgastos.R;

import java.util.ArrayList;
import java.util.HashMap;


public class IngresoFragment extends Fragment implements AdapterView.OnItemClickListener {
    ArrayList arrayList = new ArrayList<HashMap<String,String>>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingreso, container, false);

        ListView mlvMovi = (ListView) view.findViewById(R.id.lvMovimientos);
        Datos datos = new Datos(getContext());
        Cursor cursor = datos.mostrarIngresos(datos);

        if (cursor !=null) {
            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("idmovimiento",cursor.getString(cursor.getColumnIndex("idmovimiento")));
                    map.put("fecha",cursor.getString(cursor.getColumnIndex("fecha")));
                    map.put("descripcion",cursor.getString(cursor.getColumnIndex("descripcion")));
                    map.put("monto",cursor.getString(cursor.getColumnIndex("monto")));
                    map.put("movimiento",cursor.getString(cursor.getColumnIndex("movimiento")));
                    arrayList.add(map);
                    Log.d("Respuesta", String.valueOf(arrayList));
                }while (cursor.moveToNext());

                String [] origen = {"fecha","descripcion","monto"};
                int [] destino = {R.id.tvFecha,R.id.tvDescripcion,R.id.tvMonto};

                ListAdapterMovimiento productosAdapter = new ListAdapterMovimiento((Activity) getContext(),arrayList);
                mlvMovi.setAdapter(productosAdapter);
                mlvMovi.setOnItemClickListener(this);
            }
        }

        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        HashMap<String,String> map =(HashMap<String,String>) arrayList.get(i);

        String idmovimiento = map.get("idmovimiento").toString();
        String descripcion = map.get("descripcion").toString();
        String fecha = map.get("fecha").toString();
        String monto = map.get("monto").toString();
        String movimiento = map.get("movimiento").toString();
        //Toast.makeText(this, nombre,Toast.LENGTH_SHORT).show();
        Bundle bundle = new Bundle();
        bundle.putString("idmovimiento",idmovimiento);
        bundle.putString("descripcion",descripcion);
        bundle.putString("fecha",fecha);
        bundle.putString("monto",monto);
        bundle.putString("movimiento",movimiento);

        Intent intent = new Intent(getContext(), ConsultarActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}