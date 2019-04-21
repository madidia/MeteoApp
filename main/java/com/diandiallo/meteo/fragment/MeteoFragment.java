package com.diandiallo.meteo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.diandiallo.meteo.R;
import com.diandiallo.meteo.activity.DetailItem;
import com.diandiallo.meteo.adapter.MeteoAdapter;
import com.diandiallo.meteo.classesMeteo.DonneesMeteo;
import com.diandiallo.meteo.classesMeteo.FcstDay;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MeteoFragment extends Fragment {

    View view;
    ListView m_Listview;
    SwipeRefreshLayout m_refresher;
    TextView txtCity,txtCondition,txtHumidity,txtDay,txtTmp;
    ImageView imageMeteo;
    Button afficherPrevisions,masquerPrevisions;

    private String url;

    //liste des jours à afficher
    List<FcstDay> m_lsDaysToShow = new ArrayList<FcstDay>();

    ArrayAdapter<FcstDay> m_adapter;
    Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_meteo,container,false);
        // on recupere le bundle qui a été passé dans SearchFragment
        bundle = this.getArguments();

        m_Listview = (ListView) view.findViewById(R.id.listview);
        txtDay= (TextView) view.findViewById(R.id.txtDay);
        txtCity= (TextView) view.findViewById(R.id.txtCity);
        txtCondition= (TextView) view.findViewById(R.id.txtCondition);
        txtHumidity= (TextView) view.findViewById(R.id.txtHumidity);
        txtTmp= (TextView) view.findViewById(R.id.txtTmp);
        imageMeteo= (ImageView) view.findViewById(R.id.imageMeteo);
        afficherPrevisions = (Button) view.findViewById(R.id.btnAfficherPrevisions);
        masquerPrevisions = (Button) view.findViewById(R.id.btnMasquerPrevisions);
        m_refresher = (SwipeRefreshLayout) view.findViewById(R.id.swiperefreshlayout);

        m_adapter=new MeteoAdapter(view.getContext(),0);

        // change la couleur du loader
        m_refresher.setColorScheme(R.color.colorPrimary);

        // on cache les prochaines previsions en attendant le clic sur le boutton pour voir
        m_Listview.setVisibility(View.GONE);

        m_Listview.setAdapter(m_adapter);

        m_refresher.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                callWebService();
            }

        });

        m_refresher.postDelayed(new Runnable() {
            @Override
            public void run() {
                callWebService();
            }
        },0);

        // pour intercepter le clic de l'utilisateur sur un item
        m_Listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // on recupere l'objet qui a ete clique
                FcstDay itemClicked=m_lsDaysToShow.get(position);
                // affiche un toast
                Toast.makeText(view.getContext(),itemClicked.getDayLong(),Toast.LENGTH_SHORT);
                //ouvre la page detail
                Intent intent = new Intent(view.getContext(), DetailItem.class);
               // intent.putExtra("jourAMontrer", itemClicked.toJsonString());
                intent.putExtra("jourAMontrer",itemClicked.jSonToString());
                intent.putExtra("txtCity",txtCity.getText().toString());
                startActivity(intent);
            }
        });

        //si on clique sur le boutton voir previsions horaires, afficher les vues qu'il faut
        afficherPrevisions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afficherPrevisions.setVisibility(View.GONE);
                m_Listview.setVisibility(View.VISIBLE);
                masquerPrevisions.setVisibility(View.VISIBLE);
            }
        });

        // masquer les vues qu'il faut lors u clic sur le boutton
        masquerPrevisions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afficherPrevisions.setVisibility(View.VISIBLE);
                m_Listview.setVisibility(View.INVISIBLE);
                masquerPrevisions.setVisibility(View.INVISIBLE);
            }
        });
        return view;
    }

    private  void callWebService(){
        String URL;
        if(bundle!=null){
            URL= "https://www.prevision-meteo.ch/services/json/"+bundle.getString("url");
        }else{
            if (this.url!=null){
                URL= "https://www.prevision-meteo.ch/services/json/"+this.url;
            }else{
                URL= "https://www.prevision-meteo.ch/services/json/grenoble";
            }
        }
        //instancie une file d'attente Volley
        RequestQueue queue = Volley.newRequestQueue(view.getContext());
        StringRequest request=new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonMeteo = new JSONObject(response);
                            DonneesMeteo donneesMeteo=new DonneesMeteo(jsonMeteo);
                            m_lsDaysToShow.clear();
                            //on recupere le jourActuel actuel
                            //m_lsDaysToShow.add(donneesMeteo.getFcstDay0());
                            m_lsDaysToShow.add(donneesMeteo.getFcstDay1());
                            m_lsDaysToShow.add(donneesMeteo.getFcstDay2());
                            m_lsDaysToShow.add(donneesMeteo.getFcstDay3());
                            m_lsDaysToShow.add(donneesMeteo.getFcstDay4());
                            m_adapter.addAll(m_lsDaysToShow);
                            txtCity.setText(donneesMeteo.getCityInfo().getName());
                            txtDay.setText(donneesMeteo.getFcstDay0().getDayLong()+" "+
                                    donneesMeteo.getCurrentCondition().getDate());
                            txtTmp.setText(String.valueOf(donneesMeteo.getCurrentCondition().getTmp())+"°C");
                            txtCondition.setText(donneesMeteo.getCurrentCondition().getCondition());
                            txtHumidity.setText("Humidité "+String.valueOf(donneesMeteo.getCurrentCondition().getHumidity())+"%");
                            Picasso.get().load(donneesMeteo.getCurrentCondition().getIconBig()).into(imageMeteo);

                            m_refresher.setRefreshing(false);
                            afficherPrevisions.setVisibility(View.VISIBLE);

                        }catch (Exception e){}
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );
        //file d'attente
        queue.add(request);
    }

    public void setUrl(String url) {
        this.url = url;
    }

}


