package br.com.accessway.accessway;

import android.widget.EditText;
import android.widget.RatingBar;

import br.com.accessway.accessway.model.LocaisAcessiveis;

/**
 * Created by AccessWay.
 * Classe que será o apoio para pegar os pontos acessíveis digitados pelo usuário.
 */

public class FormularioHelper {

    private final EditText campoEndereco;
    private final EditText campoDescricao;
    private final RatingBar campoNota;

    private LocaisAcessiveis locaisAcessiveis;

    public FormularioHelper(FormularioLocaisAcessiveisActivity activity){
        campoEndereco = (EditText) activity.findViewById(R.id.formulario_local);
        campoDescricao = (EditText) activity.findViewById(R.id.formulario_Descricao);
        campoNota = (RatingBar) activity.findViewById(R.id.formulario_nota);
        locaisAcessiveis = new LocaisAcessiveis();

    }

    public LocaisAcessiveis pegaLocaisAcessiveis() {

        locaisAcessiveis.setLocal(campoEndereco.getText().toString());
        locaisAcessiveis.setDescricao(campoDescricao.getText().toString());
        locaisAcessiveis.setNota(Double.valueOf(campoNota.getProgress()));

        return locaisAcessiveis;
    }

    public void preencheFormulario(LocaisAcessiveis locaisAcessiveis) {
        campoEndereco.setText(locaisAcessiveis.getLocal());
        campoDescricao.setText(locaisAcessiveis.getDescricao());
        campoNota.setProgress(locaisAcessiveis.getNota().intValue());
        this.locaisAcessiveis = locaisAcessiveis;
    }

}
