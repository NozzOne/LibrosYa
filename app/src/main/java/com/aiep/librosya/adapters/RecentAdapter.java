package com.aiep.librosya.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.aiep.librosya.R;
import com.aiep.librosya.entidades.PersonaLibros;
import com.bumptech.glide.Glide;

import java.util.List;

public class RecentAdapter extends RecyclerView.Adapter<RecentAdapter.ViewHolder>{

    private List<PersonaLibros> mData;
    private LayoutInflater mInflater;
    private Context context;
    final RecentAdapter.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(PersonaLibros item);
    }


    public RecentAdapter(List<PersonaLibros> itemList, Context context, RecentAdapter.OnItemClickListener listener){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.listener = listener;
    }

    @Override
    public int getItemCount(){ return mData == null ? 0 : mData.size(); }

    @Override
    public RecentAdapter.ViewHolder onCreateViewHolder(ViewGroup paren, int viewType){
        View view = mInflater.inflate(R.layout.recent_books, null);
        return new RecentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecentAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    }

    public void setItems(List<PersonaLibros> items) { mData = items; }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imagen;
        TextView porcentaje;



        ViewHolder(View itemView){
            super(itemView);


            imagen = itemView.findViewById(R.id.recent_book_imagen);
            porcentaje = itemView.findViewById(R.id.recent_book_porcentaje);



        }

        void bindData(final PersonaLibros item){
            porcentaje.setText(item.getPorcentaje()+"% Completado");

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
