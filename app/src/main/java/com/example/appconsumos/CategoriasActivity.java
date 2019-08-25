package com.example.appconsumos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class CategoriasActivity extends AppCompatActivity {

    //agregar menu
    @Override public boolean onCreateOptionsMenu(Menu menu)
    { // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);

        //personalizar detalles en menu
        SearchView searchView = (SearchView) menu.findItem(R.id.id_bar_search_alimento).getActionView();
        int searchSrcTextId = getResources().getIdentifier("android:id/search_src_text", null, null);
        EditText searchEditText = (EditText) searchView.findViewById(searchSrcTextId);
        //searchEditText.setTextColor(Color.BLUE); // set the text color
        //searchEditText.setHintTextColor(Color.BLUE); // set the hint color
        searchEditText.setHint("Buscar Alimentos");

        return true; }



    //Implementando RecyclerView, es una version mejorada del ListView!
    private List<Categorias> categoriasList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CategoriasAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new CategoriasAdapter(categoriasList,null);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


        recyclerView.setAdapter(mAdapter);

        prepareCategoriaData();
    }

    private void prepareCategoriaData() {
        Categorias categoria = new Categorias("1","Carnes");
        categoriasList.add(categoria);

        categoria = new Categorias("1","Frutas");
        categoriasList.add(categoria);

        mAdapter.notifyDataSetChanged();
    }
}
