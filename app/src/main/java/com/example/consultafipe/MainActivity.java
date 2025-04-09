package com.example.consultafipe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.consultafipe.service.AutomovelCallback;
import com.example.consultafipe.model.Automovel;
import com.example.consultafipe.model.Historico;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    String token = BuildConfig.API_TOKEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btnBuscar = findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editCodigo = findViewById(R.id.editTextCodigo);
                EditText editAno = findViewById(R.id.editTextAno);
                TextView txtMarca = findViewById(R.id.txtMarca);
                TextView txtModelo = findViewById(R.id.txtModelo);
                TextView txtCombustivel = findViewById(R.id.txtCombustivel);
                TextView txtAno = findViewById(R.id.txtAno);
                TextView txtUltimoValor = findViewById(R.id.txtUltimoValor);
                ProgressBar progressBar = findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE); // mostra o circulozinho

                String codigo = editCodigo.getText().toString();
                String ano = editAno.getText().toString();
                RetornaObj retorno = new RetornaObj();
                AutomovelCallback AutomovelCallback;
                retorno.consultaAutomovel(codigo, ano+"-1", token, new AutomovelCallback() {
                    @Override
                    public void onSuccess(Automovel automovel) {
                        runOnUiThread(() -> {
                            txtMarca.setText("Marca: " + automovel.getBrand());
                            txtModelo.setText("Modelo: " + automovel.getModel());
                            txtCombustivel.setText("Combustível: " + automovel.getFuel());
                            txtAno.setText("Ano: " + automovel.getModelYear());
                            NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

                            List<Historico> history = automovel.getHistory();
                            if (history != null && !history.isEmpty()) {
                                Historico ultimo = history.get(0); // o mais recente geralmente vem primeiro
                                String valorFormatado = format.format(ultimo.getPrice());
                                txtUltimoValor.setText("Último valor: R$ " + valorFormatado);
                            } else {
                                txtUltimoValor.setText("Último valor: não encontrado");
                            }
                        });
                        progressBar.setVisibility(View.GONE); // escond
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(MainActivity.this, "ERRO", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE); // esconde

                    }
                });

            }
        });
    }
}