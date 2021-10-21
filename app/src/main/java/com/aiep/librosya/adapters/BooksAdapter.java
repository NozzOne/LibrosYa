package com.aiep.librosya.adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aiep.librosya.DBHelper;
import com.aiep.librosya.R;
import com.aiep.librosya.entidades.Libro;
import com.aiep.librosya.utils.Utils;
import com.bumptech.glide.Glide;

import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {

    private List<Libro> mData;
    private LayoutInflater mInflater;
    private Context context;
    final BooksAdapter.OnItemClickListener listener;
    DBHelper conn;

    public interface OnItemClickListener{
        void onItemClick(Libro item);
    }


    public BooksAdapter(List<Libro> itemList, Context context, BooksAdapter.OnItemClickListener listener){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.listener = listener;
    }

    @Override
    public int getItemCount(){ return mData == null ? 0 : mData.size(); }

    @Override
    public BooksAdapter.ViewHolder onCreateViewHolder(ViewGroup paren, int viewType){
        View view = mInflater.from(paren.getContext()).inflate(R.layout.list_books, null, false);
        return new BooksAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BooksAdapter.ViewHolder holder, final int position){
        holder.cv.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in));
        holder.bindData(mData.get(position));
    }

    public void setItems(List<Libro> items) { mData = items; }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imagen;
        TextView titulo, author, stars, paginas;
        //Button categoria1,categoria2, categoria3, categoria4;
        CardView cv;


        ViewHolder(View itemView){
            super(itemView);

            imagen = itemView.findViewById(R.id.thumbnail_ImageView);
            titulo = itemView.findViewById(R.id.bookTitle);
            author = itemView.findViewById(R.id.bookAuthor);
            stars = itemView.findViewById(R.id.stars_valor);
            paginas = itemView.findViewById(R.id.paginas_label);
            cv = itemView.findViewById(R.id.PopularBook_cv);




        }

        void bindData(final Libro item){
            titulo.setText(item.getNombre());
            author.setText(item.getAuthor());
            stars.setText(""+item.getValoracion());
            paginas.setText(item.getPaginas() + " Paginas");


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
