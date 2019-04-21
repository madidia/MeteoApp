package com.diandiallo.meteo.fragment;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.diandiallo.meteo.R;
import com.diandiallo.meteo.adapter.SearchResultsAdapter;
import com.diandiallo.meteo.classesMeteo.FcstDay;
import com.diandiallo.meteo.classesMeteo.Ville;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.getSystemService;

public class SearchFragment extends Fragment {

    View view;
    ProgressBar progressBar;
    TextView nonConnecte,villeIntrouvable;
    SearchView search;
    ListView searchResults;
    ArrayList<Ville> villeResults = new ArrayList<Ville>();
    ArrayList<Ville> filteredVilles = new ArrayList<Ville>();
    String textSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_search, container, false);
        progressBar =(ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        search = (SearchView) view.findViewById(R.id.searchView);
        searchResults = (ListView) view.findViewById(R.id.listview_search);
        search.setQueryHint("entrez une ville...");
        search.setIconified(false);
        search.clearFocus();
        nonConnecte=(TextView) view.findViewById(R.id.textWarningRecherche);
        villeIntrouvable=(TextView) view.findViewById(R.id.notFound);

        search.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
            }
        });
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                // TODO Auto-generated method stub

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText){
                nonConnecte.setVisibility(View.GONE);
                villeIntrouvable.setVisibility(View.GONE);
                if (newText.length() > 2) {
                    progressBar.setVisibility(View.VISIBLE);
                    textSearch=newText;
                    searchResults.setVisibility(view.VISIBLE);
                    villeResults=getVilles();

                } else {
                    searchResults.setVisibility(view.INVISIBLE);
                }

                return false;
            }
        });

        // pour intercepter le clic de l'utilisateur sur un item
        searchResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // on recupere la ville qui a ete clique
                Ville villeClicked=filteredVilles.get(position);
                // on donne au bundle l'url pour la ville qui a été cliqué
                // pour concatener dans l'url du json
                Bundle bundle = new Bundle();
                bundle.putString("url",villeClicked.getUrl());
                // on ouvre l'activité meteoFragment
                MeteoFragment meteoFragment = new MeteoFragment();
                meteoFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container,meteoFragment).commit();
                search.setVisibility(View.GONE);
            }
        });
        return view;
    }

    public ArrayList<Ville> getVilles() {

        String URL = "https://www.prevision-meteo.ch/services/json/list-cities";

        //instancie une file d'attente Volley
        RequestQueue queue = Volley.newRequestQueue(view.getContext());

        final ArrayList<Ville> villes = new ArrayList<Ville>();

        StringRequest request = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonMeteo = new JSONObject(response);
                           // Log.d("stringgggggggg",response);
                            // on recupere les donnees des villes
                            for (int i = 0; i < 45353; i++) {
                                villes.add(new Ville(jsonMeteo.getJSONObject("" + i)));
                            }
                            filterProductArray(textSearch);
                            searchResults.setAdapter(new SearchResultsAdapter(getActivity(),filteredVilles));

                            progressBar.setVisibility(View.GONE);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        // on attend 5 secondes pour etre sur qu'on a toutes les villes avant
                        // de dire qu'elle est est introuvable
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(filteredVilles.size()==0){
                                    villeIntrouvable.setVisibility(View.VISIBLE);
                                }
                            }
                        },7000);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        villeIntrouvable.setVisibility(View.GONE);
                        nonConnecte.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    }
                }
        );
        //file d'attente
        queue.add(request);

        return villes;
    }

    public void filterProductArray(String newText) {
        String pName;
        filteredVilles.clear();
        for (int i = 0; i < villeResults.size(); i++){
            pName = villeResults.get(i).getName().toLowerCase();
            if ( pName.contains(newText.toLowerCase())) {
                filteredVilles.add(villeResults.get(i));
            }
        }
    }



}
