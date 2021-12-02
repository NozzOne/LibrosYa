package com.aiep.librosya.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aiep.librosya.DBHelper;
import com.aiep.librosya.R;
import com.aiep.librosya.SelecionBiblioteca;
import com.aiep.librosya.entidades.Biblioteca;
import com.aiep.librosya.entidades.Libro;
import com.bumptech.glide.Glide;

import java.util.List;

public class BibliotecaAdapter extends RecyclerView.Adapter<BibliotecaAdapter.ViewHolder> {

    private List<Biblioteca> mData;
    private LayoutInflater mInflater;
    private Context context;
    final BibliotecaAdapter.OnItemClickListener listener;

    public BibliotecaAdapter(List<Biblioteca> mData, LayoutInflater mInflater, Context context, BibliotecaAdapter.OnItemClickListener listener) {
        this.mData = mData;
        this.mInflater = mInflater;
        this.context = context;
        this.listener = listener;
    }



    public interface OnItemClickListener{
        void onItemClick(Biblioteca item);
    }


    @Override
    public int getItemCount(){ return mData == null ? 0 : mData.size(); }

    @Override
    public BibliotecaAdapter.ViewHolder onCreateViewHolder(ViewGroup paren, int viewType){
        View view = mInflater.from(paren.getContext()).inflate(R.layout.list_bibiotecas, null, false);
        return new BibliotecaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BibliotecaAdapter.ViewHolder holder, final int position){
        holder.Rl.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in));
        holder.bindData(mData.get(position));
    }

    public void setItems(List<Biblioteca> items) { mData = items; }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imagen;
        TextView nombre, direccion;
        Button reservar;
        RelativeLayout Rl;



        ViewHolder(View itemView){
            super(itemView);




            imagen = itemView.findViewById(R.id.Biblioteca_imagen);
            nombre = itemView.findViewById(R.id.biblioteca_nombre);
            direccion = itemView.findViewById(R.id.biblioteca_direccion);
            reservar = itemView.findViewById(R.id.btn_reservarFisica);
            Rl = itemView.findViewById(R.id.relativelayoutAdapterBibliotecas);



        }

        void bindData(final Biblioteca item){


            nombre.setText(item.getNombre());
            direccion.setText(item.getDireccion());



            Glide.with(context)
                    .asBitmap()
                    .load(item.getImagen())
                    .into(imagen);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });

        }


    }

}
