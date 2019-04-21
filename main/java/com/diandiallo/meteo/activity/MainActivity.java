package com.diandiallo.meteo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.diandiallo.meteo.R;

public class MainActivity extends AppCompatActivity {

    TextView m_welcomText;
    EditText m_logintext;
    EditText m_password;
    Button m_connexionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // spécifie que l'IHM construite dans activity_main.xml
        // doit etre implementee pour cette activité
        setContentView(R.layout.activity_main);
        // référence les vues déclarées dans activity_main.xml
        // dans des variables de classe
        m_welcomText = (TextView) findViewById(R.id.welcomeText);
        m_logintext = (EditText) findViewById(R.id.loginText);
        m_password = (EditText) findViewById(R.id.password);
        m_connexionButton = (Button) findViewById(R.id.connexionButton);
        //le code a executer si l'utilisateur clique sur connexion
        m_connexionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeBtnLoginClick();
            }
        });
    }

    /** fonction qui verifie si l'identifiant et le mot de passe sont corrects**/

    public int check(String s1, String s2){
        int res=0;
        if(!s1.equals("diallo")){
            res=-1;
        }
        if(!s2.equals("1234")){
            res=-2;
        }
        if((!s1.equals("diallo") )&& (!s2.equals("1234"))){
            res=-3;
        }
        if(s1.equals("diallo") && s2.equals("1234")){
            res=0;
        }
        return res;
    }

    private void executeBtnLoginClick() {
        String errorMsg;
        // identifiants erronés
        int res=check(m_logintext.getText().toString(),m_password.getText().toString());
        if(res==-1) {
            errorMsg = getResources().getString(R.string.errorBadLogin);
            m_logintext.setError(errorMsg);
            return;
        }else if(res==-2){
            errorMsg = getResources().getString(R.string.errorBadPassword);
            m_password.setError(errorMsg);
            return;
        }else if(res==-3){
            errorMsg = getResources().getString(R.string.errorBadLogin);
            m_logintext.setError(errorMsg);
            errorMsg = getResources().getString(R.string.errorBadPassword);
            m_password.setError(errorMsg);
            return;
        }

        Toast t=Toast.makeText(MainActivity.this, "Connexion...",Toast.LENGTH_SHORT);
        t.show();
        // si les bons identifiants sont saisis
        //startActivity permet de naviguer vers une nouvelle page
        Intent myIntent = new Intent(this, HomePage.class);
        myIntent.putExtra("userLogin",m_logintext.getText().toString());
        startActivity(myIntent);

        finish();


    }
}
