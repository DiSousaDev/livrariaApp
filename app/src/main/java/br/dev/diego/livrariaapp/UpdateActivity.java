package br.dev.diego.livrariaapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.dev.diego.livrariaapp.db.DatabaseHelper;

public class UpdateActivity extends AppCompatActivity {

    EditText input_titulo, input_nome, input_paginas;
    Button btn_alterar, btn_excluir;

    private String id, titulo, autor, paginas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        input_titulo = findViewById(R.id.input_titulo2);
        input_nome = findViewById(R.id.input_nome2);
        input_paginas = findViewById(R.id.input_paginas2);
        btn_alterar = findViewById(R.id.btn_alterar);
        btn_excluir = findViewById(R.id.btn_excluir);

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

        btn_excluir.setOnClickListener(v -> {
            confirmarExclusao();
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

    private void confirmarExclusao() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirma Exclusão?");
        builder.setMessage("Você tem certeza que deseja esxluir este registro " + titulo + "?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseHelper banco = new DatabaseHelper(UpdateActivity.this);
                banco.removerRegistro(id);
                finish();
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

}