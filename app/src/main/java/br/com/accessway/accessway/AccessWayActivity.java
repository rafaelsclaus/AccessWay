package br.com.accessway.accessway;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by AccessWay.
 * Classe que iniciará o sistema.
 */

public class AccessWayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_way);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_mapa:
                Intent vaiParaMapa = new Intent(this, MapsActivity.class);
                startActivity(vaiParaMapa);
                break;

            case R.id.menu_locais:
                Intent vaiParaLocais = new Intent(this, ListaLocaisAcessiveisActivity.class);
                startActivity(vaiParaLocais);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_access_way, menu);

        return true;
    }
}
