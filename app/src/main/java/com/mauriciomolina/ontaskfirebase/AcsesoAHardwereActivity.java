package com.mauriciomolina.ontaskfirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AcsesoAHardwereActivity extends AppCompatActivity {



    Button btnGPS;
    TextView tvUbicacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acseso_a_hardwere);
            tvUbicacion = (TextView)findViewById(R.id.tvGps);
            btnGPS= (Button)findViewById(R.id.button);

            btnGPS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LocationManager locationManager = (LocationManager)AcsesoAHardwereActivity.this.getSystemService(Context.LOCATION_SERVICE);
                    LocationListener locationListener = new LocationListener() {

                        public void onLocationChanged(Location location) {
                        tvUbicacion.setText(""+location.getLatitude()+""+location.getLongitude());
                        }
                        public void onStatusChanged(String provider, int status, Bundle extras) {
                        }
                        public void onProviderEnabled(String provider) {
                        }
                        public void onProviderDisabled(String provider) {
                        }
                    };
                    int permissionCheck = ContextCompat.checkSelfPermission(AcsesoAHardwereActivity.this,
                            Manifest.permission.ACCESS_FINE_LOCATION);
                    LocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0 , 0,locationListener);
                }
            });

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if(permissionCheck==PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)){

            }
            else{
                ActivityCompat.requestPermissions(this,
                        new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                        1);
            }
    }
}
}
