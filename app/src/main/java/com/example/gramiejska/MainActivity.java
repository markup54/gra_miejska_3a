package com.example.gramiejska;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Location ostatniaLokalizacja;
    private FusedLocationProviderClient fusedLocationClient;
    private TextView textView;
    private int nrLokalizacji=0;
    private Miejsca miejsca;
    private ArrayList<Lokalizacja> szukaneMiejsca ;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        miejsca = new Miejsca();
        szukaneMiejsca = miejsca.lokalizacje;
        textView = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(szukaneMiejsca.get(nrLokalizacji).getIdZdjecia());

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getLocation();
            }
        });
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


    }
    private void getLocation(){
        int REQUEST_LOCATION_PERMISSION = 0 ;
        if(ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]
            {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION_PERMISSION);
        }
        else{
            Log.d("Uprawnienia","udzielona zgoda");
            fusedLocationClient.getLastLocation().addOnSuccessListener(
                    new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                ostatniaLokalizacja = location;
                                double dl_geograficzna = ostatniaLokalizacja.getLongitude();
                                double dl_szukana = szukaneMiejsca.get(nrLokalizacji).getDlugosc();
                                double szer_geograficzna = ostatniaLokalizacja.getLatitude();
                                double szer_szukana=szukaneMiejsca.get(nrLokalizacji).getSzerokosc();
                                float dystans[]=new float[1];
                                Location.distanceBetween(szer_szukana,dl_szukana,szer_geograficzna,dl_geograficzna,dystans);
                                if(dystans[0]<100){
                                    nrLokalizacji++;
                                    //TODO nowa lokalizacja do wyświetlenia
                                    Toast.makeText(MainActivity.this, "Jest", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(MainActivity.this,
                                            "Jesteś oddalony o "+dystans[0],
                                            Toast.LENGTH_SHORT)
                                            .show();
                                }
                                double czas = ostatniaLokalizacja.getTime();
                                textView.setText("Długość geograficzna :"+dl_geograficzna
                                        +", szerokość geograficzna: "+szer_geograficzna+
                                        ", czas: "+czas);
                            }
                        }
                    }
            );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            getLocation();
        }
        else{
            Toast
                    .makeText(this,
                            "nie udzielono zgody na lokalizację," +
                                    " aplikacja nie będzie działała poprawnie",
                            Toast.LENGTH_SHORT)
                    .show();
        }
    }
}