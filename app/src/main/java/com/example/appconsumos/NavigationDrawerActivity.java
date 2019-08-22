package com.example.appconsumos;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
Fragmento1.OnFragmentInteractionListener,
Fragmento2.OnFragmentInteractionListener {

    //Implementando RecyclerView, es una version mejorada del ListView!
    private List<Categorias> categoriasList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CategoriasAdapter mAdapter;

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
            Log.i("====>","Click en ...HOMEE!!");

            /*setContentView(R.layout.fragment_fragmento1);

            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

            mAdapter = new CategoriasAdapter(categoriasList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

            recyclerView.setAdapter(mAdapter);
            prepareCategoriaData();
*/
            LayoutInflater inflater = getLayoutInflater();
            View rootViewFrag1 = inflater.inflate(R.layout.fragment_fragmento1, null, false);

            recyclerView = (RecyclerView) rootViewFrag1.findViewById(R.id.recycler_view_alimentos);
                //findViewById(R.id.recycler_view_alimentos);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(rootViewFrag1.getContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

            recyclerView.setAdapter(mAdapter);
            prepareCategoriaData();

            // añadir context de frag1 a contenedor principal
            fragmentManager.beginTransaction().replace(R.id.contenedor,
                    new Fragmento1()).commit();

        } else if (id == R.id.nav_gallery) {
            // Handle the camera action
            fragmentManager.beginTransaction().replace(R.id.contenedor,
                    new Fragmento2()).commit();

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        Log.i("====>","Click en ...SALIRR HOMEE!!");
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void prepareCategoriaData() {
        Categorias categoria = new Categorias("1","Carnes");
        categoriasList.add(categoria);

        categoria = new Categorias("1","Frutas");
        categoriasList.add(categoria);

        mAdapter.notifyDataSetChanged();
    }

}
