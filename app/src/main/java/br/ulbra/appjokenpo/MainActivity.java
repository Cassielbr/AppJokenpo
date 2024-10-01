package br.ulbra.appjokenpo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int pontuacaoJogador = 0;
    private int pontuacaoApp = 0;

    private boolean estiloAlternado = false;
    private int[] botoesOriginais = {R.drawable.pedra, R.drawable.papel, R.drawable.tesoura};
    private int[] botoesAlternativos = {R.drawable.pedramine, R.drawable.papeline, R.drawable.tesouramine};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configura o botão para trocar estilo
        findViewById(R.id.btnTrocarEstilo).setOnClickListener(this::trocarEstilo);
    }

    public void selecionadoPedra(View view) {
        this.opcaoSelecionado("pedra");
    }

    public void selecionadoPapel(View view) {
        this.opcaoSelecionado("papel");
    }

    public void selecionadoTesoura(View view) {
        this.opcaoSelecionado("tesoura");
    }

    public void opcaoSelecionado(String opcaoSelecionada) {
        ImageView imageResultado = findViewById(R.id.imgResultado);
        TextView txtResult = findViewById(R.id.txtResultado);
        String[] opcoes = {"pedra", "papel", "tesoura"};
        String opcaoApp = opcoes[new Random().nextInt(3)];

        switch (opcaoApp) {
            case "pedra":
                imageResultado.setImageResource(R.drawable.pedra);
                break;
            case "papel":
                imageResultado.setImageResource(R.drawable.papel);
                break;
            case "tesoura":
                imageResultado.setImageResource(R.drawable.tesoura);
                break;
        }

        if ((opcaoApp.equals("tesoura") && opcaoSelecionada.equals("papel")) ||
                (opcaoApp.equals("papel") && opcaoSelecionada.equals("pedra")) ||
                (opcaoApp.equals("pedra") && opcaoSelecionada.equals("tesoura"))) {
            txtResult.setText("Resultado: Você PERDEU... :(");
            pontuacaoApp++;
        } else if ((opcaoSelecionada.equals("tesoura") && opcaoApp.equals("papel")) ||
                (opcaoSelecionada.equals("papel") && opcaoApp.equals("pedra")) ||
                (opcaoSelecionada.equals("pedra") && opcaoApp.equals("tesoura"))) {
            txtResult.setText("Resultado: Você GANHOU... ;D");
            pontuacaoJogador++;
        } else {
            txtResult.setText("Resultado: Vocês EMPATARAM... :|");
        }
        atualizarPlacar();
    }

    public void atualizarPlacar() {
        TextView txtPlacar = findViewById(R.id.txtPlacar);
        txtPlacar.setText("Jogador: " + pontuacaoJogador + " - App: " + pontuacaoApp);
    }

    public void reiniciarJogo(View view) {
        pontuacaoJogador = 0;
        pontuacaoApp = 0;
        atualizarPlacar();
        ImageView imageResultado = findViewById(R.id.imgResultado);
        imageResultado.setImageResource(android.R.color.transparent); // Limpa a imagem
    }

    public void trocarEstilo(View view) {
        ImageButton btnPedra = findViewById(R.id.btnPedra);
        ImageButton btnPapel = findViewById(R.id.btnPapel);
        ImageButton btnTesoura = findViewById(R.id.btnTesoura);

        if (estiloAlternado) {
            btnPedra.setImageResource(botoesOriginais[0]);
            btnPapel.setImageResource(botoesOriginais[1]);
            btnTesoura.setImageResource(botoesOriginais[2]);
        } else {
            btnPedra.setImageResource(botoesAlternativos[0]);
            btnPapel.setImageResource(botoesAlternativos[1]);
            btnTesoura.setImageResource(botoesAlternativos[2]);
        }

        estiloAlternado = !estiloAlternado; // Alterna o estado
    }
}
