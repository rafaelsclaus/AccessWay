package br.com.accessway.accessway;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.accessway.accessway.dao.LocaisAcessiveisDAO;
import br.com.accessway.accessway.model.LocaisAcessiveis;

/**
 * Created by AccessWay.
 * Classe que irá coletar as informações de pontos acessíveis.
 */

public class FormularioLocaisAcessiveisActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        helper = new FormularioHelper(this);

        Intent intent = getIntent();
        LocaisAcessiveis locaisAcessiveis = (LocaisAcessiveis) intent.getSerializableExtra("locaisAcessiveis");

        if (locaisAcessiveis != null){
            helper.preencheFormulario(locaisAcessiveis);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:
                //Salva o local acessivel cadastrado.
                LocaisAcessiveis locaisAcessiveis = helper.pegaLocaisAcessiveis();

                LocaisAcessiveisDAO dao = new LocaisAcessiveisDAO(this);
                if(locaisAcessiveis.getId() != null){
                    dao.altera(locaisAcessiveis);
                }else {
                    dao.insere(locaisAcessiveis);
                }
                dao.close();

                Toast.makeText(FormularioLocaisAcessiveisActivity.this, "Local " + locaisAcessiveis.getLocal() + " salvo!", Toast.LENGTH_SHORT).show();




                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
