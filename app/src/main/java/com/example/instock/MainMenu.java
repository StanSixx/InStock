package com.example.instock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainMenu extends AppCompatActivity {

    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Fragment fAgregarProductos, fInicio;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //Instancia del DrawerLayout
        drawerLayout = (DrawerLayout)findViewById(R.id.dl_main_menu);

        //Enlazamos el ActionBar con el DrawerLayout
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.email, R.string.email);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //Habilitamos el ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Instancia de los fragments
        fAgregarProductos = new AgregarProductosFragment();
        fInicio = new InicioFragment();

        //Agregamos el Fragment que se presentará en la pantalla principal
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_view, fInicio).commit();
        //Instanciamos el NavigationView
        navigationView = (NavigationView)findViewById(R.id.nav_view);
        //Invocamos el método que enlaza la navegación de los fragments con los items del menú
        navegacionDeFragments();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Agregar los Fragments restantes
    public void navegacionDeFragments(){
        navigationView.setNavigationItemSelectedListener(item -> {

            transaction = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()){

                case R.id.opc_inicio:
                    transaction.replace(R.id.fragment_container_view, fInicio);
                    transaction.addToBackStack(null);
                    drawerLayout.closeDrawers();
                    transaction.commit();
                    break;

                case R.id.opc_agg_productos:
                    transaction.replace(R.id.fragment_container_view, fAgregarProductos);
                    transaction.addToBackStack(null);
                    drawerLayout.closeDrawers();
                    transaction.commit();
                    break;

                case R.id.opc_cerrar_sesion:
                    drawerLayout.closeDrawers();
                    finish();
                    break;
            }
            return true;
        });
    }
}