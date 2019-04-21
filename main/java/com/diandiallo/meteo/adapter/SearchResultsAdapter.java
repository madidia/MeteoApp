package com.diandiallo.meteo.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.diandiallo.meteo.R;
import com.diandiallo.meteo.classesMeteo.Ville;

import java.util.ArrayList;

public class SearchResultsAdapter extends BaseAdapter{
    private LayoutInflater layoutInflater;
    private final Context m_context;

    private ArrayList<Ville> villeDetails=new ArrayList<>();
    int count;


    //constructor method
    public SearchResultsAdapter(Context context, ArrayList<Ville> ville_Details) {

        layoutInflater = LayoutInflater.from(context);
        m_context=context;

        this.villeDetails=ville_Details;
        this.count= villeDetails.size();

    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Ville getItem(int arg0) {
        return villeDetails.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater=(LayoutInflater) m_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =inflater.inflate(R.layout.ville,parent,false);
        TextView textName=(TextView) view.findViewById(R.id.nameVille);
        TextView textCountry=(TextView) view.findViewById(R.id.nameCountry);
        Ville ville = getItem(position);

        textName.setText(ville.getName());
        textCountry.setText(ville.getCountry());

        return view;
    }

    public ArrayList<Ville> getVilleDetails() {
        return villeDetails;
    }

    public void setVilleDetails(ArrayList<Ville> villeDetails) {
        this.villeDetails = villeDetails;
    }


}
