package br.dev.diego.livrariaapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

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

    public void cadastrarLivro(String titulo, String autor, Integer paginas) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUNA_TITULO, titulo);
        cv.put(COLUNA_AUTOR, autor);
        cv.put(COLUNA_PAGINAS, paginas);

        long resultado = db.insert(NOME_TABELA, null, cv);

        if(resultado == -1) {
            Toast.makeText(context, "Erro ao salvar.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Livro adicionado!", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor getInfo() {
        String query = "SELECT * FROM " + NOME_TABELA;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void atualizarDados(String row_id, String titulo, String autor, String paginas) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUNA_TITULO, titulo);
        cv.put(COLUNA_AUTOR, autor);
        cv.put(COLUNA_PAGINAS, paginas);

        long resultado = db.update(NOME_TABELA, cv, "id=?", new String[]{row_id});

        if(resultado == -1) {
            Toast.makeText(context, "Erro ao atualizar.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Livro atualizado!", Toast.LENGTH_SHORT).show();
        }

    }

    public void removerRegistro(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        long resultado = db.delete(NOME_TABELA,"id=?", new String[]{row_id});

        if(resultado == -1) {
            Toast.makeText(context, "Erro ao excluir.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Registro Excluido!", Toast.LENGTH_SHORT).show();
        }

    }

    public void removerTodosRegistros() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + NOME_TABELA);
    }

}
