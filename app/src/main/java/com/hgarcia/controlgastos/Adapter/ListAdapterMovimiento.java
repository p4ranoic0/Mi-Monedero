package com.hgarcia.controlgastos.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hgarcia.controlgastos.Fragment.ListasFragment;
import com.hgarcia.controlgastos.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ListAdapterMovimiento extends ArrayAdapter {
    private final ArrayList<HashMap<String, String>> arraylist;
    Activity activity;
    public ListAdapterMovimiento(Activity activity, ArrayList<HashMap<String,String>> arrayList) {
        super(activity, arrayList.size());
        this.activity =activity;
        this.arraylist = arrayList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rootView;
        LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rootView =layoutInflater.inflate(R.layout.item_movimiento,null);
        TextView mtvIdMovimiento = rootView.findViewById(R.id.tvIdMovimiento);
        TextView mtvDesrcipcion = rootView.findViewById(R.id.tvDescripcion);
        TextView mtvFecha = rootView.findViewById(R.id.tvFecha);
        TextView mtvMonto = rootView.findViewById(R.id.tvMonto);

        LinearLayout mllItemproducto = rootView.findViewById(R.id.llMovimiento);

        HashMap<String,String> map = arraylist.get(position);

        mtvIdMovimiento.setText(map.get("idmovimiento"));
        mtvDesrcipcion.setText(map.get("descripcion"));
        mtvFecha.setText(map.get("fecha"));
        mtvMonto.setText(map.get("monto"));


        float movimiento =Float.parseFloat(map.get("movimiento"));
        if(movimiento<0){
            mtvMonto.setTextColor(Color.RED);
            //mllItemproducto.setBackgroundColor(Color.RED);
        }
        return rootView;
    }

    @Override
    public int getCount(){
        return arraylist.size();
    }

}
