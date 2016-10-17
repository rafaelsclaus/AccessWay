package br.com.accessway.accessway.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import br.com.accessway.accessway.model.LocaisAcessiveis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AccessWay.
 * Classe que irá persistir no banco de dados.
 */

public class LocaisAcessiveisDAO extends SQLiteOpenHelper{


    public LocaisAcessiveisDAO(Context context) {
        super(context, "AccessWay", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE LocaisAcessiveis (id INTEGER PRIMARY KEY, local TEXT, descricao TEXT, nota REAL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS LocaisAcessiveis";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(LocaisAcessiveis locaisAcessiveis) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = pegaDadosLocaisAcessiveis(locaisAcessiveis);

        db.insert("LocaisAcessiveis", null, dados);

        String sql = "INSERE INTO LocaisAcessiveis (id, local, descricao, nota);";
    }

    @NonNull
    private ContentValues pegaDadosLocaisAcessiveis(LocaisAcessiveis locaisAcessiveis) {
        ContentValues dados = new ContentValues();

        dados.put("local", locaisAcessiveis.getLocal());
        dados.put("descricao", locaisAcessiveis.getDescricao());
        dados.put("nota", locaisAcessiveis.getNota());
        return dados;
    }

    public List<LocaisAcessiveis> buscaLocaisAcessiveis() {
        String sql = "SELECT * FROM LocaisAcessiveis;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<LocaisAcessiveis> locaisAcessiveis = new ArrayList<>();
        while (c.moveToNext()){
            LocaisAcessiveis locais = new LocaisAcessiveis();

            locais.setId(c.getLong(c.getColumnIndex("id")));
            locais.setLocal(c.getString(c.getColumnIndex("local")));
            locais.setDescricao(c.getString(c.getColumnIndex("descricao")));
            locais.setNota(c.getDouble(c.getColumnIndex("nota")));

            locaisAcessiveis.add(locais);
        }
        c.close();
        return locaisAcessiveis;
    }

    public void deleta(LocaisAcessiveis locaisAcessiveis) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();

        String[] params = {locaisAcessiveis.getId().toString()};

        db.delete("LocaisAcessiveis","id = ?", params );

    }

    public void altera(LocaisAcessiveis locaisAcessiveis) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosLocaisAcessiveis(locaisAcessiveis);
        String[] params = {locaisAcessiveis.getId().toString()};
        db.update("LocaisAcessiveis", dados, "id=?", params );
    }

    public List<LocaisAcessiveis> buscaLocais() {

        String sql = "SELECT * FROM LocaisAcessiveis;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<LocaisAcessiveis> listaLocais = new ArrayList<LocaisAcessiveis>();
        while (c.moveToNext()) {
            LocaisAcessiveis locais = new LocaisAcessiveis();
            locais.setId(c.getLong(c.getColumnIndex("id")));
            locais.setLocal(c.getString(c.getColumnIndex("local")));
            locais.setNota(c.getDouble(c.getColumnIndex("nota")));

            listaLocais.add(locais);
        }
        c.close();


        return listaLocais;
    }
}
