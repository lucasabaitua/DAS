package com.example.myapplication.Adaptadores;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Entidades.Prenda;
import com.example.myapplication.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class prendasOverview extends BaseAdapter implements View.OnClickListener {

    private List<Prenda> p;
    private Context c;
    private View.OnClickListener listener;
    public prendasOverview(List<Prenda> lP, Context context){
        this.p=lP;
        this.c=context;
    }

    @Override
    public int getCount() {
        return p.size();
    }

    @Override
    public Object getItem(int pos) {
        return p.get(pos);
    }

    @Override
    public long getItemId(int pos){
        return pos;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        ViewHolder holder;

        if (view==null){
            LayoutInflater inflater = LayoutInflater.from(this.c);
            view = inflater.inflate(R.layout.lista_items, viewGroup, false);
            //a parte en constructora
            holder = new ViewHolder();
            holder.imageView = view.findViewById(R.id.imageView);
            holder.tituloTextView = view.findViewById(R.id.tituloTextView);
            holder.descripcionTextView = view.findViewById(R.id.descripcionTextView);
            holder.fechaTextView = view.findViewById(R.id.fechaTextView);
            view.setTag(holder);
        } else{
            holder = (ViewHolder) view.getTag();
        }
        Prenda prenda = p.get(pos);
        holder.imageView.setImageResource(prenda.getFoto());
        holder.tituloTextView.setText(prenda.getTituloPrenda());
        holder.descripcionTextView.setText(prenda.getDescripcion());
        holder.fechaTextView.setText(prenda.getFechaColgado());
        return view;
    }

    public void setOnClickListener(View.OnClickListener pListener) {
        this.listener = pListener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

    private static class ViewHolder{
        ImageView imageView;
        TextView tituloTextView;
        TextView descripcionTextView;
        TextView fechaTextView;
    }

}
