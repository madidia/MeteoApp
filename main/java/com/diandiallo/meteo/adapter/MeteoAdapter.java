package com.diandiallo.meteo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.diandiallo.meteo.R;
import com.diandiallo.meteo.classesMeteo.FcstDay;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;

public class MeteoAdapter extends ArrayAdapter<FcstDay> {

    private final Context m_context;
    private final ArrayList<FcstDay> m_lsItems;

    public MeteoAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        m_context=context;
        m_lsItems=new ArrayList<FcstDay>();
    }

    @Override
    public int getPosition(FcstDay item){ return m_lsItems.indexOf(item);}
    @Override
    public int getCount(){return m_lsItems.size();}
    @Override
    public FcstDay getItem(int position){ return m_lsItems.get(position);}
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater=(LayoutInflater) m_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =inflater.inflate(R.layout.listview_item,parent,false);

        TextView textViewDay=(TextView) view.findViewById(R.id.textViewDay);
        TextView textViewDate=(TextView) view.findViewById(R.id.textViewDate);
        ImageView imageViewMeteo=(ImageView) view.findViewById(R.id.imageViewMeteo);

        TextView tempMin=(TextView) view.findViewById(R.id.tmpMinValue);
        TextView tempMax=(TextView) view.findViewById(R.id.tmpMaxValue);
        TextView condition=(TextView) view.findViewById(R.id.txtConditionItem);

        FcstDay dayToshow = getItem(position);

        textViewDay.setText(dayToshow.getDayLong());
        textViewDate.setText(dayToshow.getDate());
        Picasso.get().load(dayToshow.getIconBig()).into(imageViewMeteo);

        tempMin.setText(String.valueOf(dayToshow.getTmin())+"°C");
        tempMax.setText(String.valueOf(dayToshow.getTmax())+"°C");
        condition.setText(dayToshow.getCondition());

        return  view;
    }
    @Override
    public void addAll(@NonNull Collection<? extends FcstDay> collection){
        m_lsItems.clear();
        m_lsItems.addAll(collection);
        notifyDataSetChanged();
    }


}
