package com.diandiallo.meteo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.diandiallo.meteo.R;
import com.diandiallo.meteo.database.DataBaseHelper;
import com.diandiallo.meteo.fragment.MeteoFragment;

public class DeleteVilleFragment extends Fragment {

    View view;
    private Button btnDelete,btnVoirMeteo;
    private EditText editable_item;

    DataBaseHelper mDatabaseHelper;

    private String selectedName;
    private String selectedUrl;
    private int selectedID;

    Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.delete_ville,container,false);

        btnDelete = (Button) view.findViewById(R.id.btnDeleteVille);
        btnVoirMeteo=(Button) view.findViewById(R.id.btnVoirMeteo);
        editable_item = (EditText) view.findViewById(R.id.editable_item);
        mDatabaseHelper = new DataBaseHelper(view.getContext());

        // on recupere le bundle qui a été passé dans SearchFragment
        bundle = this.getArguments();

        //on prend l'ID qu'on a passé dans le bundle

        selectedID = bundle.getInt("id",-1);

        //on prend le nom qu'on a passé dans le bundle
        selectedName = bundle.getString("name");

        //on prend l'url qu'on a passé dans le bundle
        selectedUrl = bundle.getString("url");

        editable_item.setText(selectedName);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteVille(selectedUrl);
                editable_item.setText("");
		toastMessage("Ville supprimée !");
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container,new VillesFavorisFragment()).commit();
            }
        });

        btnVoirMeteo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putString("id",String.valueOf(selectedID));
                bundle.putString("name",String.valueOf(selectedName));
                bundle.putString("url",String.valueOf(selectedUrl));
                MeteoFragment meteoFragment = new MeteoFragment();
                meteoFragment.setUrl(selectedUrl);
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container,meteoFragment).commit();
            }
        });

        return view;
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(view.getContext(),message, Toast.LENGTH_SHORT).show();
    }

}
