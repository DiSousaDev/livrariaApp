package br.dev.diego.livrariaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdicionarActivity extends AppCompatActivity {

    EditText input_titulo, input_nome, input_paginas;
    Button btn_salvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar);

        input_titulo = findViewById(R.id.input_titulo);
        input_nome = findViewById(R.id.input_nome);
        input_paginas = findViewById(R.id.input_paginas);
        btn_salvar = findViewById(R.id.btn_salvar);

        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper banco = new DatabaseHelper(AdicionarActivity.this);
                banco.cadastrarLivro(
                        input_titulo.getText().toString().trim(),
                        input_nome.getText().toString().trim(),
                        Integer.valueOf(input_paginas.getText().toString().trim())
                );
            }
        });

    }
}