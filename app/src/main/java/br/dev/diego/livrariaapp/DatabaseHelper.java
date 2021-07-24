package br.dev.diego.livrariaapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String NOME_BANCO = "livraria.db";
    private static final int VERSAO_BANCO = 1;

    private static final String NOME_TABELA = "meus_livros";
    private static final String COLUNA_ID = "id";
    private static final String COLUNA_TITULO = "nome_livro";
    private static final String COLUNA_AUTOR = "nome_autor";
    private static final String COLUNA_PAGINAS = "paginas_livro";

    public DatabaseHelper(@Nullable Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + NOME_TABELA +
                " (" + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUNA_TITULO + " TEXT, " +
                COLUNA_AUTOR + " TEXT, " +
                COLUNA_PAGINAS + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + NOME_TABELA;
        db.execSQL(query);
        onCreate(db);
    }
}
