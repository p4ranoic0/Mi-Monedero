package com.hgarcia.controlgastos.Adapter;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.hgarcia.controlgastos.Datos;
import com.hgarcia.controlgastos.Fragment.GastoFragment;
import com.hgarcia.controlgastos.Fragment.IngresoFragment;
import com.hgarcia.controlgastos.Fragment.ListasFragment;
import com.hgarcia.controlgastos.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(MainActivity fa) {
        super(fa);
    }

    @Override
    public int getItemCount() {
        return 3;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ListasFragment();
            case 1:
                return new GastoFragment();
            case 2:
                return new IngresoFragment();
            default:
                return null;
        }

    }



}
