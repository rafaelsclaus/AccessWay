package br.com.accessway.accessway;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.accessway.accessway.dao.LocaisAcessiveisDAO;
import br.com.accessway.accessway.model.LocaisAcessiveis;

/**
 * Created by AccessWay.
 * Classe que vai listar todos os pontos acessíveis.
 */

public class ListaLocaisAcessiveisActivity extends AppCompatActivity {

    private ListView listaLocais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_locais_acessiveis);

        listaLocais = (ListView) findViewById(R.id.lista_locais_acessiveis);

        // Trabalhando o click unico
        listaLocais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LocaisAcessiveis locaisAcessiveis = (LocaisAcessiveis) listaLocais.getItemAtPosition(position);

                Intent intentVaiProFormulario = new Intent(ListaLocaisAcessiveisActivity.this, FormularioLocaisAcessiveisActivity.class);
                intentVaiProFormulario.putExtra("locaisAcessiveis", locaisAcessiveis);
                startActivity(intentVaiProFormulario);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button novoLocalAcessivel = (Button) findViewById(R.id.novo_local_acessivel);
        novoLocalAcessivel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVaiProFormulario = new Intent(ListaLocaisAcessiveisActivity.this, FormularioLocaisAcessiveisActivity.class);
                startActivity(intentVaiProFormulario);
            }
        });

        registerForContextMenu(listaLocais);
    }

    private void carregaListaLocaisAcessiveis() {
        LocaisAcessiveisDAO dao = new LocaisAcessiveisDAO(this);
        List<LocaisAcessiveis> locaisAcessiveis = dao.buscaLocaisAcessiveis();
        dao.close();

        ArrayAdapter<LocaisAcessiveis> adapter = new ArrayAdapter<LocaisAcessiveis>(this, android.R.layout.simple_list_item_1, locaisAcessiveis);
        listaLocais.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        carregaListaLocaisAcessiveis();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final LocaisAcessiveis locaisAcessiveis = (LocaisAcessiveis) listaLocais.getItemAtPosition(info.position);

        MenuItem itemMapa = menu.add("Visualizar no mapa");
        Intent intentMapa = new Intent(Intent.ACTION_VIEW);
        intentMapa.setData(Uri.parse("geo:0,0?q=" + locaisAcessiveis.getLocal()));
        itemMapa.setIntent(intentMapa);

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {


                LocaisAcessiveisDAO dao = new LocaisAcessiveisDAO(ListaLocaisAcessiveisActivity.this);
                dao.deleta(locaisAcessiveis);
                dao.close();

                carregaListaLocaisAcessiveis();

                Toast.makeText(ListaLocaisAcessiveisActivity.this, "O local " + locaisAcessiveis.getLocal() + " foi deletado", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_mapa:
                Intent vaiParaMapa = new Intent(this, MapsActivity.class);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
