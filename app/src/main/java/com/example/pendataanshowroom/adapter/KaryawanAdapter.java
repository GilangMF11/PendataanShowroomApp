package com.example.pendataanshowroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pendataanshowroom.R;
import com.example.pendataanshowroom.model.Karyawan;

import java.util.ArrayList;

import com.example.pendataanshowroom.R;
import com.example.pendataanshowroom.model.Karyawan;

public class KaryawanAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<Karyawan> karyawanList = new ArrayList<>();

    public void setKaryawanList(ArrayList<Karyawan> karyawanList) {
        this.karyawanList = karyawanList;
    }

    public KaryawanAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return karyawanList.size();
    }

    @Override
    public Object getItem(int i) {
        return karyawanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view;

        if (itemView == null) {
            itemView = LayoutInflater.from(context)
                    .inflate(R.layout.item_karyawan, viewGroup, false);
        }

        ViewHolder viewHolder = new ViewHolder(itemView);;

        Karyawan karyawan = (Karyawan) getItem(i);
        viewHolder.bind(karyawan);
        return itemView;
    }

    private class ViewHolder {
        private TextView txtNim, txtName;

        ViewHolder(View view) {
            txtName = view.findViewById(R.id.txt_nama);
            txtNim = view.findViewById(R.id.txt_nim);
        }

        void bind(Karyawan karyawan) {
            txtName.setText(karyawan.getNama());
            txtNim.setText(karyawan.getNim());
        }
    }
}
