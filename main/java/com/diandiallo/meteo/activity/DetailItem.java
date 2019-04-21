package com.diandiallo.meteo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.diandiallo.meteo.R;
import com.diandiallo.meteo.adapter.HeureAdapter;
import com.diandiallo.meteo.classesMeteo.FcstDay;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailItem extends AppCompatActivity {

    TextView txtCityItem,txtConditionItem,txtDayItem,txtTmpMinItem,txtTmpMaxItem;
    ImageView imageMeteoItem;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Button btnVoirHoraires,masquerHoraires;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ville_detail_item);

        // on initialise les views
        txtDayItem= (TextView) findViewById(R.id.txtDayItem);
        txtCityItem= (TextView) findViewById(R.id.txtCityItem);
        txtConditionItem= (TextView) findViewById(R.id.txtConditionItem);
        txtTmpMinItem= (TextView) findViewById(R.id.txtTmpMinItem);
        txtTmpMaxItem= (TextView) findViewById(R.id.txtTmpMaxItem);
        imageMeteoItem= (ImageView) findViewById(R.id.imageMeteoItem);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewHeure);
        btnVoirHoraires = (Button) findViewById(R.id.btnVoirHoraires);
        masquerHoraires = (Button) findViewById(R.id.btnMasquerHoraires);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Récupère les paramètres de navigation
        Intent intent = getIntent();
        String choixJson= intent.getStringExtra("jourAMontrer");
        FcstDay choix = null;
        try {
            choix = new FcstDay(new JSONObject(choixJson));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String txtCity = intent.getStringExtra("txtCity");

        txtCityItem.setText(txtCity);
        txtDayItem.setText(choix.getDayLong()+" "+
                choix.getDate());
        txtTmpMinItem.setText("Min "+String.valueOf(choix.getTmin())+"°C");
        txtTmpMaxItem.setText("Max "+String.valueOf(choix.getTmax())+"°C");
        txtConditionItem.setText(choix.getCondition());
        Picasso.get().load(choix.getIconBig()).into(imageMeteoItem);
        // on cache les previsions horaires en attendant le clic sur le boutton pour voir
        recyclerView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(layoutManager);
        HeureAdapter adapter = new HeureAdapter(this, choix.getDonneesHoraires());
        recyclerView.setAdapter(adapter);
        //si on clique sur le boutton voir previsions horaires, afficher les vues qu'il faut
        btnVoirHoraires.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnVoirHoraires.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                masquerHoraires.setVisibility(View.VISIBLE);
            }
        });

        // masquer les vues qu'il faut lors u clic sur le boutton
        masquerHoraires.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnVoirHoraires.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.INVISIBLE);
                masquerHoraires.setVisibility(View.INVISIBLE);
            }
        });

    }

    /** *
     * revient à la page précendente quand on clique sur le buton "<--"
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
