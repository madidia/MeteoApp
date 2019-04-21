package com.diandiallo.meteo.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.diandiallo.meteo.R;
import com.diandiallo.meteo.fragment.MeteoFragment;
import com.diandiallo.meteo.fragment.ParametresFragment;
import com.diandiallo.meteo.fragment.SearchFragment;

public class HomePage extends AppCompatActivity {

    private DrawerLayout m_drawerLayout;
    private NavigationView m_navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //Récupère les paramètres de navigation
        Intent intent = getIntent();
        String mylogin = intent.getStringExtra("userLogin");

        //Active le menu hamburger
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);

        // référencé le drawerLayout et navigationView
        m_drawerLayout =(DrawerLayout) findViewById(R.id.homePage_drawer_layout);
        m_navigationView=(NavigationView) findViewById(R.id.homePage_nav_view);
        m_navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                manageClick(menuItem);
                return true;
            }
        });

        configureNavigationViewHeader(mylogin);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new MeteoFragment()).commit();
    }

    /** *
     * ouvre le menu navigation view quand l'utilisateur clique sur le boutton menu
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                m_drawerLayout.openDrawer(GravityCompat.START);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    /** ajout on implémente cette méthode pour gérer les
     * redirections des clics sur le menu latéral
     */

    public boolean manageClick(MenuItem menuItem) {
        menuItem.setChecked(true);
        switch (menuItem.getItemId()){
            case R.id.nav_meteo:
                /** pour afficher le fragment */
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MeteoFragment()).commit();
                break;
            case R.id.nav_parametres:
                /** pour afficher le fragment */
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ParametresFragment()).commit();
                break;
            case R.id.nav_search:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SearchFragment()).commit();
                break;

        }
        m_drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * ajout
     * pour fermer le menu lateral quand on clique sur le retour
     */
    @Override
    public void onBackPressed() {
        if(m_drawerLayout.isDrawerOpen(GravityCompat.START)){
            m_drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    private void configureNavigationViewHeader(String mylogin) {
        View viewHeader = getLayoutInflater().inflate(R.layout.nav_header, null);
        //retrouve les références des vues
        ImageView avatar = (ImageView) viewHeader.findViewById(R.id.avatar);
        TextView userName = (TextView) viewHeader.findViewById(R.id.pseudo);
        // configure les vues en fonction de l'utilisateur actuellement logué
        avatar.setImageResource(R.drawable.photo_profil);
        userName.setText(mylogin);
        // configure le header de la navigationView
        m_navigationView.addHeaderView(viewHeader);
    }


}


