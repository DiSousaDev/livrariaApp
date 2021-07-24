package br.dev.diego.livrariaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList id_livro, titulo_livro, nome_autor, paginas;

    public CustomAdapter(Context context, ArrayList id_livro, ArrayList titulo_livro, ArrayList nome_autor, ArrayList paginas) {
        this.context = context;
        this.id_livro = id_livro;
        this.titulo_livro = titulo_livro;
        this.nome_autor = nome_autor;
        this.paginas = paginas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.id_livro_txt.setText(String.valueOf(this.id_livro.get(position)));
        holder.titulo_livro_txt.setText(String.valueOf(this.titulo_livro.get(position)));
        holder.nome_autor_txt.setText(String.valueOf(this.nome_autor.get(position)));
        holder.paginas_txt.setText(String.valueOf(this.paginas.get(position)));
    }

    @Override
    public int getItemCount() {
        return id_livro.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id_livro_txt, titulo_livro_txt, nome_autor_txt, paginas_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_livro_txt = itemView.findViewById(R.id.id_livro_txt);
            titulo_livro_txt = itemView.findViewById(R.id.titulo_livro_txt);
            nome_autor_txt = itemView.findViewById(R.id.nome_autor_txt);
            paginas_txt = itemView.findViewById(R.id.paginas_txt);
        }
    }
}
