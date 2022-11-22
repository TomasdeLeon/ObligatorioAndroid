package com.example.obligatorioandroid;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class LibroRecyclerViewAdapter extends RecyclerView.Adapter<LibroRecyclerViewAdapter.ViewHolderLibros>{

    private List<Libro> listaItems;

    public LibroRecyclerViewAdapter(List<Libro> libros){
        listaItems = libros;
    }

    @NonNull
    @Override
    public ViewHolderLibros onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_libro, parent, false);
        return new ViewHolderLibros(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLibros holder, int position) {
        Libro currentItem = listaItems.get(position);
        holder.txtLibroTitulo.setText(currentItem.getTxtLibroTitulo());
        holder.txtLibroAutor.setText(currentItem.getTxtLibroAutor());
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext().getApplicationContext(), VerLibro.class);
                Bundle bundle = new Bundle();

                bundle.putString("Titulo", currentItem.getTxtLibroTitulo());
                bundle.putString("Autor", currentItem.getTxtLibroAutor());
                bundle.putString("Editorial", currentItem.getTxtLibroEditorial());
                bundle.putString("Descripcion", currentItem.getTxtLibroDescripcion());
                bundle.putString("Pagina", Integer.toString(currentItem.getLibroPagina()));
                bundle.putInt("ID", currentItem.getLibroId());

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtras(bundle);

                view.getContext().getApplicationContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaItems.size();
    }

    public class ViewHolderLibros extends RecyclerView.ViewHolder{

        private TextView txtLibroTitulo;
        private TextView txtLibroAutor;
        private ConstraintLayout layoutItem;

        public ViewHolderLibros(@NonNull View itemView){

            super(itemView);
            txtLibroTitulo = itemView.findViewById(R.id.txtitemTitulo);
            txtLibroAutor = itemView.findViewById(R.id.txtitemAutor);
            layoutItem = itemView.findViewById(R.id.item_recyclerview_libro);
        }
    }

}