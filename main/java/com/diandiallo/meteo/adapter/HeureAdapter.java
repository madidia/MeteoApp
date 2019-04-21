package com.diandiallo.meteo.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.diandiallo.meteo.R;
import com.diandiallo.meteo.classesMeteo.DonneesHoraires;
import com.diandiallo.meteo.classesMeteo.Ville;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HeureAdapter extends RecyclerView.Adapter<HeureAdapter.ViewHolder> {

    private ArrayList<DonneesHoraires> donneesHoraires = new ArrayList<>();
    private Context mContext;

    public HeureAdapter(Context context,ArrayList<DonneesHoraires> mesDonneesHoraires) {
        this.donneesHoraires=mesDonneesHoraires;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.heure_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        DonneesHoraires donneesHoraires=this.donneesHoraires.get(position);
        holder.textHeure.setText(donneesHoraires.getHeure());
        holder.tmpHeure.setText(donneesHoraires.getTMP2m().toString()+"°C");
        Picasso.get().load(donneesHoraires.getIcon()).into(holder.imageHeure);

    }

    @Override
    public int getItemCount() {
        return donneesHoraires.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textHeure;
        TextView tmpHeure;
        ImageView imageHeure;

        public ViewHolder(View itemView) {
            super(itemView);
            textHeure=(TextView) itemView.findViewById(R.id.txtHeure);
            tmpHeure=(TextView) itemView.findViewById(R.id.tmpHeure);
            imageHeure=(ImageView) itemView.findViewById(R.id.imageHeure);
        }
    }
    /*private LayoutInflater layoutInflater;
    private final Context m_context;

    private ArrayList<DonneesHoraires> donneesHoraires=new ArrayList<>();
    int count;


    //constructor method
    public HeureAdapter(Context context, ArrayList<DonneesHoraires> mesdonneesHoraires) {

        layoutInflater = LayoutInflater.from(context);
        m_context=context;

        this.donneesHoraires=mesdonneesHoraires;
        this.count= donneesHoraires.size();

    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public DonneesHoraires getItem(int arg0) {
        return donneesHoraires.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater=(LayoutInflater) m_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =inflater.inflate(R.layout.heure_item,parent,false);
        TextView textHeure=(TextView) view.findViewById(R.id.txtHeure);
        TextView tmpHeure=(TextView) view.findViewById(R.id.tmpHeure);
        ImageView imageHeure=(ImageView) view.findViewById(R.id.imageHeure);
        DonneesHoraires donneesHoraires = getItem(position);

        textHeure.setText(donneesHoraires.getHeure());
        tmpHeure.setText(donneesHoraires.getTMP2m().toString());
        Picasso.get().load(donneesHoraires.getIcon()).into(imageHeure);

        return view;
    }*/
}
