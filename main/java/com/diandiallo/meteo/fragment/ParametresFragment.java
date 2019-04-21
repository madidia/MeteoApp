package com.diandiallo.meteo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import com.diandiallo.meteo.classesMeteo.Ville;
import com.diandiallo.meteo.database.DataBaseHelper;

import org.json.JSONObject;

import java.util.ArrayList;

public class ParametresFragment extends Fragment {

    final SearchFragment searchFragment = new SearchFragment();

    View view;
    DataBaseHelper mDatabaseHelper;
    Button btnViewData;
    ProgressBar progressBar;
    TextView searchVilleText,nonConnecte,villeIntrouvable;
    SearchView searchView;
    ListView searchResults;
    ArrayList<Ville> villeResults = new ArrayList<Ville>();
    ArrayList<Ville> filteredVilles = new ArrayList<Ville>();
    String textSearch;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_parametres,container,false);

        progressBar =(ProgressBar) view.findViewById(R.id.progressBarAdd);
        progressBar.setVisibility(View.GONE);
        searchView = (SearchView) view.findViewById(R.id.searchViewVille);
        searchResults = (ListView) view.findViewById(R.id.listViewSearchAddVille);
        searchVilleText = (TextView) view.findViewById(R.id.searchAddVille);
        searchView.setQueryHint("entrez une ville...");
        searchView.setIconified(false);
        searchView.clearFocus();

        nonConnecte=(TextView) view.findViewById(R.id.textWarningRechercheAdd);
        villeIntrouvable=(TextView) view.findViewById(R.id.notFoundAdd);

        btnViewData = (Button) view.findViewById(R.id.btnView);
        mDatabaseHelper = new DataBaseHelper(view.getContext());

        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setVisibility(View.GONE);
                VillesFavorisFragment villesFavorisFragment = new VillesFavorisFragment();
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container,villesFavorisFragment).commit();
            }
        });

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
                //on ajoute la ville a la BD
                addData(villeClicked);
                searchView.setVisibility(View.GONE);
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container,new VillesFavorisFragment()).commit();
            }
        });
        return view;
    }

    public void addData(Ville ville) {
        boolean insertData = mDatabaseHelper.addData(ville);

        if (insertData) {
            toastMessage("Ville ajoutée !");
        } else {
            toastMessage("La ville est déja ajoutée !");
        }
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(view.getContext(),message, Toast.LENGTH_SHORT).show();
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


