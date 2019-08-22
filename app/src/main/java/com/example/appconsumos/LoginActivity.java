package com.example.appconsumos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void cargarCategorias(View v){
        //Intent k = new Intent(this, CategoriasActivity.class);
        Intent k = new Intent(this, NavigationDrawerActivity.class);
        startActivity(k);
    }
}
