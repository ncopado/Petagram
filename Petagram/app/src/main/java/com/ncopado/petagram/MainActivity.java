package com.ncopado.petagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Pet> lstPet;
    private RecyclerView listPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      listPet=(RecyclerView) findViewById(R.id.rvPet);

        LinearLayoutManager llm=new LinearLayoutManager(this);

        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listPet.setLayoutManager(llm);

         AddPet();
         InicializarAdaptador();


    }

    private void AddPet() {
        lstPet=new ArrayList<Pet>();

        lstPet.add(new Pet("Lola",5,R.drawable.pet1));
        lstPet.add(new Pet("Lola",5,R.drawable.pet2));
        lstPet.add(new Pet("Lola",5,R.drawable.pet3));
        lstPet.add(new Pet("Lola",5,R.drawable.pet4));
        lstPet.add(new Pet("Lola",5,R.drawable.pet5));

    }

    public  void InicializarAdaptador(){
        PetAdaptador adaptador=new PetAdaptador(lstPet,this);
        listPet.setAdapter(adaptador);
    }
}
