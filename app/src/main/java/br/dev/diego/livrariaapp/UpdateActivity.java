package br.dev.diego.livrariaapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.dev.diego.livrariaapp.db.DatabaseHelper;

public class UpdateActivity extends AppCompatActivity {

    EditText input_titulo, input_nome, input_paginas;
    Button btn_alterar;

    private String id, titulo, autor, paginas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        input_titulo = findViewById(R.id.input_titulo2);
        input_nome = findViewById(R.id.input_nome2);
        input_paginas = findViewById(R.id.input_paginas2);
        btn_alterar = findViewById(R.id.btn_alterar);

        preEditar();

        ActionBar ab = getSupportActionBar();
        if(ab != null) {
            ab.setTitle("Editando " + titulo);
        }

        btn_alterar.setOnClickListener(v -> {
            DatabaseHelper banco = new DatabaseHelper(UpdateActivity.this);
            titulo = input_titulo.getText().toString().trim();
            autor = input_nome.getText().toString().trim();
            paginas = input_paginas.getText().toString().trim();
            banco.atualizarDados(id, titulo, autor, paginas);
        });

    }

    private void preEditar() {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("titulo") && getIntent().hasExtra("autor") && getIntent().hasExtra("paginas")) {
            // obtendo dados de Intent
            id = getIntent().getStringExtra("id");
            titulo = getIntent().getStringExtra("titulo");
            autor = getIntent().getStringExtra("autor");
            paginas = getIntent().getStringExtra("paginas");

            // alterando dados de Intent
            input_titulo.setText(titulo);
            input_nome.setText(autor);
            input_paginas.setText(paginas);

        } else {
            Toast.makeText(this, "Nenhum livro encontrado.", Toast.LENGTH_SHORT).show();
        }
    }

}