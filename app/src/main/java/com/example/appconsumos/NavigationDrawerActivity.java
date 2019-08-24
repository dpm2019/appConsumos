package com.example.appconsumos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        CategoriasFragment.OnFragmentInteractionListener,
        AlimentosFragment.OnFragmentInteractionListener,
        NuevoAlimentoFragment.OnFragmentInteractionListener,
        NotificacionesFragment.OnFragmentInteractionListener,
        SupermercadosFragment.OnFragmentInteractionListener,
        RenovarAlimentoFragment.OnFragmentInteractionListener,
        EliminarAlimentoFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        Log.i("====>","Click en ...CATEGORIAS!!");
        // añadir context de fragment (CATEGORIAS) a contenedor principal
        fragmentManager.beginTransaction().replace(R.id.contenedor,
                new CategoriasFragment()).commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        //personalizar detalles en menu
        SearchView searchView = (SearchView) menu.findItem(R.id.id_bar_search_alimento).getActionView();
        int searchSrcTextId = getResources().getIdentifier("android:id/search_src_text", null, null);
        EditText searchEditText = (EditText) searchView.findViewById(searchSrcTextId);
        //searchEditText.setTextColor(Color.BLUE); // set the text color
        //searchEditText.setHintTextColor(Color.BLUE); // set the hint color
        searchEditText.setHint("Buscar Alimentos");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.id_bar_search_alimento) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        FragmentManager fragmentManager = getSupportFragmentManager();

        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Log.i("====>","Click en ...CATEGORIAS!!");
            // añadir context de fragment (CATEGORIAS) a contenedor principal
            fragmentManager.beginTransaction().replace(R.id.contenedor,
                    new CategoriasFragment()).commit();

        } else if (id == R.id.nav_gallery) {
            Log.i("====>","Click en ...ALIMENTOS!!");
            // añadir context de fragment (ALIMENTOS) a contenedor principal
            // Handle the camera action
            fragmentManager.beginTransaction().replace(R.id.contenedor,
                    new AlimentosFragment()).commit();

        } else if (id == R.id.nav_slideshow) {
            Log.i("====>","Click en ...NUEVO ALIMENTO!!");
            // añadir context de fragment (NUEVO ALIMENTO) a contenedor principal
            // Handle the camera action
            fragmentManager.beginTransaction().replace(R.id.contenedor,
                    new NuevoAlimentoFragment()).commit();

        } else if (id == R.id.nav_tools) {
            Log.i("====>","Click en ...NOTIFICACIONES!!");
            // añadir context de fragment (NOTIFICACIONES) a contenedor principal
            // Handle the camera action
            fragmentManager.beginTransaction().replace(R.id.contenedor,
                    new NotificacionesFragment()).commit();

        } else if (id == R.id.nav_share) {
            Log.i("====>","Click en ...CONTACTAR SUPERMERCADO!!");
            // añadir context de fragment (CONTACTAR SUPERMERCADO) a contenedor principal
            // Handle the camera action
            fragmentManager.beginTransaction().replace(R.id.contenedor,
                    new SupermercadosFragment()).commit();

        } else if (id == R.id.nav_send) {
            Log.i("====>","Click en ...CERRAR SESION!!");
            //Intent k = new Intent(this, CategoriasActivity.class);
            Intent k = new Intent(this, LoginActivity.class);
            startActivity(k);

            return true;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        Log.i("====>","Click en ...SALIR NAVIGATION!!");
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
