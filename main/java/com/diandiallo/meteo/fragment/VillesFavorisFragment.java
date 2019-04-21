package com.diandiallo.meteo.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.diandiallo.meteo.R;
import com.diandiallo.meteo.adapter.SearchResultsAdapter;
import com.diandiallo.meteo.classesMeteo.Ville;
import com.diandiallo.meteo.database.DataBaseHelper;

import java.util.ArrayList;

public class VillesFavorisFragment extends Fragment {

    View view;
    DataBaseHelper mDatabaseHelper;
    private ListView mListView;
    ArrayList<Ville> listDataVilles = new ArrayList<>();
    TextView noVilles;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.liste_ville_favoris, container, false);
        mListView = (ListView) view.findViewById(R.id.listViewFavoris);
        mDatabaseHelper = new DataBaseHelper(view.getContext());
        noVilles = (TextView) view.findViewById(R.id.textNoVille);

        populateListView();

        //si on clique sur la listView qui contient les listes favoris
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Ville ville = (Ville) adapterView.getItemAtPosition(i);
                //prend l'id qui est associé à l'url de la ville
                Cursor data = mDatabaseHelper.getItemID(ville);
                int itemID = -1;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }
                if(itemID > -1){
                    Bundle bundle=new Bundle();
                    bundle.putString("id",String.valueOf(itemID));
                    bundle.putString("name",String.valueOf(ville.getName()));
                    bundle.putString("url",String.valueOf(ville.getUrl()));
                    // on ouvre l'activité meteoFragment
                    DeleteVilleFragment deleteVilleFragment = new DeleteVilleFragment();
                    deleteVilleFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().
                            replace(R.id.fragment_container,deleteVilleFragment).commit();
                }
            }
        });
        return view;
    }

    private void populateListView() {
        //recupere les donnes et les ajoute à la liste
        Cursor data = mDatabaseHelper.getData();
        while(data.moveToNext()){
            //ajoute dans le tableau la ville qu'on récupère
            listDataVilles.add(new Ville(data.getString(1),
                    data.getString(2),data.getString(3)));
        }
        // s'il n'ya pas de villes on affiche pas de villes
        if(listDataVilles.size()==0){
            noVilles.setText("Vous n'avez aucune ville enregistrée !");
            noVilles.setVisibility(View.VISIBLE);
        }else{
            //create the list adapter and set the adapter
            noVilles.setVisibility(View.INVISIBLE);
            SearchResultsAdapter adapter = new SearchResultsAdapter(getActivity(),listDataVilles);
            mListView.setAdapter(adapter);
        }
    }

}
