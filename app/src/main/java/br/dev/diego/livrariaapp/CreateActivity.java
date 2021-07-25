package br.dev.diego.livrariaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import br.dev.diego.livrariaapp.db.DatabaseHelper;

public class CreateActivity extends AppCompatActivity {

    EditText input_titulo, input_nome, input_paginas;
    Button btn_salvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        input_titulo = findViewById(R.id.input_titulo);
        input_nome = findViewById(R.id.input_nome);
        input_paginas = findViewById(R.id.input_paginas);
        btn_salvar = findViewById(R.id.btn_salvar);

        btn_salvar.setOnClickListener(v -> {
            DatabaseHelper banco = new DatabaseHelper(CreateActivity.this);
            banco.cadastrarLivro(input_titulo.getText().toString().trim(), input_nome.getText().toString().trim(), Integer.valueOf(input_paginas.getText().toString().trim()));
            // Atualizando activity
            Intent intent = new Intent(CreateActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

    }
}