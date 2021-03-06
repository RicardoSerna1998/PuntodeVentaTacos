package com.example.ricardosernam.puntodeventa;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ricardosernam.puntodeventa.Sincronizar.Sincronizar;
import com.example.ricardosernam.puntodeventa.Inventario.Inventario;
import com.example.ricardosernam.puntodeventa.Ventas.Ventas;
import com.example.ricardosernam.puntodeventa.sync.SyncAdapter;
import com.example.ricardosernam.puntodeventa.utils.Constantes;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Fragment myFragment;
    private Toolbar toolbar;
    private ContentValues values;

    private android.support.v4.app.FragmentManager manejador = getSupportFragmentManager();  //manejador que permite hacer el cambio de ventanas
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppBarLayout bar=findViewById(R.id.APLappBar);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            manejador.beginTransaction().replace(R.id.LOprincipal, new Ventas()).commit(); ///cambio de fragment
            }

        values=new ContentValues();
        ////////////////////////////////////////7
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);//editamos el navigationheader
        View hView = navigationView.getHeaderView(0);

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    public boolean isTagInBackStack(String tag){
        int x;
        boolean toReturn = false;
        int backStackCount = getSupportFragmentManager().getBackStackEntryCount();
        for (x = 0; x < backStackCount; x++){
            if (tag.equals(getSupportFragmentManager().getBackStackEntryAt(x).getName())){
                toReturn = true;
            }
        }
        return toReturn;
    }
    @SuppressWarnings("StatementWithEmptyBody")
    ///////////////////////método que maneja el item seleccionado
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        TextView fragAbierto = findViewById(R.id.TVFragabierto);  ///textview que va en la app bar e indica que item esta abierto
        int id = item.getItemId();
        fragAbierto.setText(item.getTitle());
        android.support.v4.app.FragmentTransaction transaction=manejador.beginTransaction();

        if (id == R.id.Ventas) {
            if(isTagInBackStack("Ventas")) {
                transaction.replace(R.id.LOprincipal, getSupportFragmentManager().findFragmentByTag("Ventas") ).addToBackStack("Ventas").commit();
            }
            else{
                transaction.replace(R.id.LOprincipal, new Ventas(), "Ventas").addToBackStack("Ventas").commit();
            }
        }
        else if (id == R.id.Inventario) {

            if(isTagInBackStack("Inventario")) {
                transaction.replace(R.id.LOprincipal, getSupportFragmentManager().findFragmentByTag("Inventario")).addToBackStack("Inventario").commit();
                }
            else{
                transaction.replace(R.id.LOprincipal, new Inventario(), "Inventario").addToBackStack("Inventario").commit();
            }
        }
        else if (id == R.id.Sincronizar) {
            if(isTagInBackStack("Sincronizar")) {
                transaction.replace(R.id.LOprincipal, getSupportFragmentManager().findFragmentByTag("Sincronizar")).addToBackStack("Sincronizar").commit();
                }
            else{
                transaction.replace(R.id.LOprincipal, new Sincronizar(), "Sincronizar").addToBackStack("Sincronizar").commit();
            }
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {  ///anulamos el onBackPressed

    }

}
