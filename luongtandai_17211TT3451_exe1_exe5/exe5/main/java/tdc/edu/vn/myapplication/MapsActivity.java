package tdc.edu.vn.myapplication;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.SearchView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    SearchView searchView;
    private RadioButton normal, layers, hybrid, terrain;

    public static final int REQUEST_ID_ACCESS_COURSE_FINE_LOCATION = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        searchView = findViewById( R.id.search );

        setControl();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        searchView.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = searchView.getQuery().toString();
                List<Address> addressList = null;

                if (location != null || !location.equals("")){
                    Geocoder geocoder = new Geocoder( MapsActivity.this );
                    try {
                        addressList = geocoder.getFromLocationName(location,1  );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = addressList.get( 0 );
                    LatLng latLng = new LatLng( address.getLatitude(), address.getLongitude() );
                    mMap.addMarker( new MarkerOptions().position( latLng ).title( location ) );
                    mMap.animateCamera( CameraUpdateFactory.newLatLngZoom( latLng, 10 ) );

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        } );
        createMap();
    }

    private void setControl() {
        normal = (RadioButton) findViewById(R.id.normal);
        hybrid = (RadioButton) findViewById(R.id.hybrid);
        layers = (RadioButton) findViewById(R.id.layers);
        terrain = (RadioButton) findViewById(R.id.terrain);
    }
    private void createMap(){
        SupportMapFragment smf = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        smf.getMapAsync(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        createMap();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        setEvent();
        // Add a marker in Sydney and move the camera
        LatLng hcm = new LatLng( 13.940764, 109.181971);
        mMap.addMarker(new MarkerOptions().position(hcm).title("Marker in HCM City"));
        CameraPosition cp = new CameraPosition.Builder().target(hcm).zoom(14).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp));
        UiSettings mapUiSettings = mMap.getUiSettings();
        mapUiSettings.setZoomControlsEnabled(true);

    }
    private void setEvent() {
        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });

        hybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        });

        layers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
        });

        terrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            }
        });

    }


}
