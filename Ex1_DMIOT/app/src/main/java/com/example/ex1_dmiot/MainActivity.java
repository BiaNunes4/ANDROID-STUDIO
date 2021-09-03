package com.example.ex1_dmiot;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.database.Cursor;
import android.os.Bundle;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public DatabaseManager db;
    public ArrayList<Vacina>vacina = new ArrayList<>();
    public Integer idAtual = 0;
    public Integer totalVacinas = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.db = new DatabaseManager(this,"base",null,1);
        startDatabase();
        populaTela();
    }
    public void startDatabase(){
        this.db.inserirVacina(1,"CORONAVAC", "11/10/21", "MORUMBI", "2 DOSE");
        this.db.inserirVacina(2,"PFIZER","11/10/21", "CAMPO GRANDE","1 DOSE");
    }
    public void populaTela(){
        Cursor c = db.listaVacina();

        if(c.getCount()>0){
            c.moveToFirst();
            totalVacinas = c.getCount();
            idAtual = 0;
            vacina.clear();

            do{
                Vacina vac = new Vacina();
                vac.setData_vacina(c.getString(c.getColumnIndex("DATA_VACINA")));
                vac.setNome_vacina(c.getString(c.getColumnIndex("NOME_VACINA")));
                vac.setDose(c.getString(c.getColumnIndex("DOSE")));
                vac.setUnidade(c.getString(c.getColumnIndex("UNIDADE")));

                vacina.add(vac);
            }while(c.moveToNext());

            public void showProximo(View view){
                EditText id = findViewById(R.id.id);
                EditText data_vacina = findViewById(R.id.data_vacina);
                EditText nome = findViewById(R.id.nome);
                EditText unidade = findViewById(R.id.unidade);
                EditText dose = findViewById(R.id.dose);

                Vacina c = vacina.get(idAtual);

                id.setText((c.getId().toString()));
                data_vacina.setText(c.getData_vacina());
                nome.setText(c.getNome_vacina());
                unidade.setText(c.getUnidade());
                dose.setText(c.getDose());

                idAtual++;
                if(idAtual == totalVacinas) idAtual=0;
            }
            public void atualizar (View view){
                EditText data_vacina = findViewById(R.id.data_vacina);
                EditText nome = findViewById(R.id.nome);
                EditText unidade = findViewById(R.id.unidade);
                EditText dose = findViewById(R.id.dose);

                this.db.atualizarVacina(new Integer(id.getText().toString()), data_vacina.getText().toString(), nome.getText().toString(), unidade.getText().toString(),dose.getText().toString());
                populaTela();
            }
            public void apagar(View view){
                EditText nome = findViewById(R.id.nome);

                this.db.removeVacina((nome.getText().toString()));
                populaTela();
            }
            public void novo(View view){
                EditText data_vacina = findViewById(R.id.data_vacina);
                EditText nome = findViewById(R.id.nome);
                EditText unidade = findViewById(R.id.unidade);
                EditText dose = findViewById(R.id.dose);

                data_vacina.setText("");
                nome.setText("");
                unidade.setText("");
                dose.setText("");
            }
            public void inserir(View view){
                EditText data_vacina = findViewById(R.id.data_vacina);
                EditText nome = findViewById(R.id.nome);
                EditText unidade = findViewById(R.id.unidade);
                EditText dose = findViewById(R.id.dose);

                this.db.removeVacina((nome.getText().toString()));
                populaTela();
            }

        }
    }


}