package com.example.appconsumos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 7117; //referencia
    //private static final String TAG = "gAuth";
    List<AuthUI.IdpConfig> providers;

    FragmentManager fragmentManager = getSupportFragmentManager();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Inicializar Provider
        providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(), //Email Buider
                new AuthUI.IdpConfig.PhoneBuilder().build(), //Phone Builder
                new AuthUI.IdpConfig.FacebookBuilder().build(), //Facebook Builder
                new AuthUI.IdpConfig.GoogleBuilder().build() //Google Builder
        );
        showSignInOptions();
    }

    private void showSignInOptions() {

        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                        .setLogo(R.drawable.logo)
                        .setAvailableProviders(providers)
                        .setTheme(R.style.MyTheme)
                        .build(),MY_REQUEST_CODE);
            }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_REQUEST_CODE){

            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK){

                //GetUser
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                //Show Email on toast
                Toast.makeText(this, ""+user.getEmail(), Toast.LENGTH_SHORT).show();
                //TextView txNombre = (TextView) findViewById(R.id.reg_name_et);
                //txNombre.setText(""+user.getDisplayName());

            }else {

                Toast.makeText(this,""+response.getError().getMessage(), Toast.LENGTH_SHORT).show();

            }

        }
    }

    public void cerrarSesion (View v){

        final Intent k = new Intent(this, LoginActivity.class);
        //startActivity(k);
        //final Intent k = new Intent(this, LoginActivity.class);
        //LoginFireBase
        AuthUI.getInstance()
                .signOut(LoginActivity.this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        startActivity(k);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    //Opciones Dashboard
    public void cargarCategorias(View v){
        Intent k = new Intent(this, NavigationDrawerActivity.class);
        startActivity(k);
        //fragmentManager.beginTransaction().replace(R.id.contenedor,
        //        new CategoriasFragment()).commit();
    }

    public void cargarAlimentos(View v){
        //fragmentManager.beginTransaction().replace(R.id.contenedor,
        //        new AlimentosFragment()).commit();
        Intent k = new Intent(this, NavigationDrawerActivity.class);
        startActivity(k);
    }

    public void cargarNotificaciones(View v){
        fragmentManager.beginTransaction().replace(R.id.contenedor,
                new NotificacionesFragment()).commit();
    }

    public void cargarSuperMercados(View v){
        fragmentManager.beginTransaction().replace(R.id.contenedor,
                new SupermercadosFragment()).commit();
    }

}
