package br.dev.diego.livrariaapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import br.dev.diego.livrariaapp.db.CustomAdapter;
import br.dev.diego.livrariaapp.db.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton btn_adicionar;

    DatabaseHelper banco;
    ArrayList<String> id_livro, titulo_livro, nome_autor, paginas;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        btn_adicionar = findViewById(R.id.btn_adicionar);

        btn_adicionar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateActivity.class);
            startActivity(intent);
        });

        banco = new DatabaseHelper(MainActivity.this);
        id_livro = new ArrayList<>();
        titulo_livro = new ArrayList<>();
        nome_autor = new ArrayList<>();
        paginas = new ArrayList<>();

        buscarRegistros();

        customAdapter = new CustomAdapter(MainActivity.this, this, id_livro, titulo_livro, nome_autor, paginas);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            recreate();
        }
    }

    public void buscarRegistros() {
        Cursor cursor = banco.getInfo();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "Nenhum livro cadastrado.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                id_livro.add(cursor.getString(0));
                titulo_livro.add(cursor.getString(1));
                nome_autor.add(cursor.getString(2));
                paginas.add(cursor.getString(3));
            }
        }

    }

}