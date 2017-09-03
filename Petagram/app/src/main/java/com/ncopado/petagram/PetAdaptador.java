package com.ncopado.petagram;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ncopado on 02/09/17.
 */

public class PetAdaptador extends RecyclerView.Adapter<PetAdaptador.PetViewHolder> {


    ArrayList<Pet> lstPet;
    Activity activity;

    public PetAdaptador(ArrayList<Pet> lstPet,Activity activity) {
        this.lstPet = lstPet;
        this.activity=activity;
    }

    @Override
    public PetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_list,parent,false);


        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PetViewHolder holder, int position) {

        final  Pet pet=lstPet.get(position);

        holder.petPhoto.setImageResource(pet.getPhoto());

        holder.tvName.setText(pet.getName());



        holder.tvRating.setText( Integer.toString(  pet.getReiting()));


        holder.btnLike.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {



                Toast.makeText(activity,"Diste like",Toast.LENGTH_SHORT).show();
            }


        });

    }

    @Override
    public int getItemCount() {
        return lstPet.size();
    }

    public  static  class PetViewHolder extends ViewHolder {

        private ImageView petPhoto;
        private TextView tvName;
        private TextView tvRating;
        private ImageButton btnLike;

        public PetViewHolder(View itemView) {
            super(itemView);


            petPhoto=(ImageView) itemView.findViewById(R.id.petphoto);
            tvName=(TextView) itemView.findViewById(R.id.tvNamePet);
            tvRating=(TextView) itemView.findViewById(R.id.tvReting);
            btnLike=(ImageButton)itemView.findViewById(R.id.btnLike);
        }
    }
}
