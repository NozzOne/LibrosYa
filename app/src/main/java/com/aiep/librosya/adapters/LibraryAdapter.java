package com.aiep.librosya.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.aiep.librosya.DBHelper;
import com.aiep.librosya.R;
import com.aiep.librosya.entidades.Libro;
import com.aiep.librosya.entidades.PersonaLibros;
import com.bumptech.glide.Glide;

import java.util.List;

public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.ViewHolder> {

    private List<PersonaLibros> mData;
    private LayoutInflater mInflater;
    private Context context;
    final LibraryAdapter.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(PersonaLibros item);
    }


    public LibraryAdapter(List<PersonaLibros> itemList, Context context, LibraryAdapter.OnItemClickListener listener){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.listener = listener;
    }

    @Override
    public int getItemCount(){ return mData == null ? 0 : mData.size(); }

    @Override
    public LibraryAdapter.ViewHolder onCreateViewHolder(ViewGroup paren, int viewType){
        View view = mInflater.inflate(R.layout.list_library, null);
        return new LibraryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final LibraryAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    }

    public void setItems(List<PersonaLibros> items) { mData = items; }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imagen;
        TextView titulo, author, porcentaje;



        ViewHolder(View itemView){
            super(itemView);

            titulo = itemView.findViewById(R.id.list_library_titulo);
            author = itemView.findViewById(R.id.list_library_author);
            porcentaje = itemView.findViewById(R.id.list_library_porcentaje);
            imagen = itemView.findViewById(R.id.list_library_imagen);

        }

        void bindData(final PersonaLibros item){

            titulo.setText(item.getNombre());
            author.setText(item.getAuthor());
            porcentaje.setText(item.getPorcentaje()+"%");

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
