package com.ncopado.petagram.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ncopado.petagram.R;
import com.ncopado.petagram.adapter.PetAdaptador;
import com.ncopado.petagram.pojo.Pet;

import java.util.ArrayList;

/**
 * Created by ncopado on 04/09/17.
 */

public class FragmentProfile extends Fragment {

    ArrayList<Pet> lstPet;
    private RecyclerView listPet;


    public FragmentProfile() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.fragment_profile,container,false);

        listPet=(RecyclerView) view.findViewById(R.id.rvPetProfile);

        LinearLayoutManager llm=new LinearLayoutManager(getActivity());


        GridLayoutManager glm=new GridLayoutManager(getContext(),3);

        //llm.setOrientation(LinearLayoutManager.VERTICAL);
        listPet.setLayoutManager(glm);

        AddPet();
        InicializarAdaptador();


        return  view;
    }


    private void AddPet() {
        lstPet=new ArrayList<Pet>();

        lstPet.add(new Pet("Lola",5,R.drawable.pet1));
        lstPet.add(new Pet("Lola",5,R.drawable.pet1));
        lstPet.add(new Pet("Lola",5,R.drawable.pet1));
        lstPet.add(new Pet("Lola",5,R.drawable.pet1));
        lstPet.add(new Pet("Lola",5,R.drawable.pet1));
        lstPet.add(new Pet("Lola",5,R.drawable.pet1));


    }

    public  void InicializarAdaptador(){
        PetAdaptador adaptador=new PetAdaptador(lstPet,getActivity(),2);
        listPet.setAdapter(adaptador);
    }
}
