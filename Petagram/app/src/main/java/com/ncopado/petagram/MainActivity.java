package com.ncopado.petagram;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ncopado.petagram.adapter.PageAdapter;
import com.ncopado.petagram.fragment.FragmentLista;
import com.ncopado.petagram.fragment.FragmentProfile;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {




    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        tabLayout=(TabLayout) findViewById(R.id.tabLayout);

        viewPager=(ViewPager) findViewById(R.id.viewPager);

        setUpViewPager();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_favourites,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.mFavourite:
                Intent intent=new Intent(this,FavouritePEt.class);
                startActivity(intent);
                break;
            case R.id.mContact:
                Intent intentMcontact=new Intent(this,Contact.class);
                startActivity(intentMcontact);
                break;

            case R.id.mAbout:
                Intent intentMaboutUs=new Intent(this,AboutUs.class);
                startActivity(intentMaboutUs);
                break;

        }


        return super.onOptionsItemSelected(item);
    }


    private  ArrayList<Fragment> agregarFragment(){
        ArrayList<Fragment> fragments=new ArrayList<>();

        fragments.add(new FragmentLista());
        fragments.add(new FragmentProfile());


        return  fragments;
    }


    private void  setUpViewPager(){

        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),agregarFragment()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_action_home);

        tabLayout.getTabAt(1).setIcon(R.drawable.ic_action_profile);


    }


}
