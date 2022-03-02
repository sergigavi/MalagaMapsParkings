package es.sgv.malagamapsparkings

import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.jar.Manifest

class MainActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener  {

    private lateinit var mapa:GoogleMap

    companion object{
        const val REQUEST_CODE_LOCATION = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createFragment()
    }

    private fun createFragment() {
        val mapFragment:SupportMapFragment = supportFragmentManager.findFragmentById(R.id.mapa) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    //este metodo se llama cuando el map haya sido cargado
    override fun onMapReady(mapaCreado: GoogleMap) {
        mapa = mapaCreado
        createMarker(36.718926, -4.418061, "Málaga")
        mapa.animateCamera(
            CameraUpdateFactory.newLatLngZoom(LatLng(36.718926, -4.418061),10f),
            2500,
            null
        )
    }

    private fun createMarker(latitude:Double, longitude:Double, titulo:String) {
        val coordenadas = LatLng(latitude, longitude)
        val marker:MarkerOptions = MarkerOptions().position(coordenadas).title(titulo)
        mapa.addMarker(marker)
    }

    private fun isLocationPermissionGranted(): Boolean {
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true
        }
        else{
            return false
        }
    }

    private fun enableLocation(){
        if(!::mapa.isInitialized) return //si el mapa no ha sido inicializado
        if (isLocationPermissionGranted()){
            //si
            mapa.isMyLocationEnabled = true
        }else{
            //no
            requestLocationPermission()
        }
    }

    private fun requestLocationPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)){
            Toast.makeText(this, "Acepta los permisos en ajustes", Toast.LENGTH_SHORT).show()
        }
        else{
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE_LOCATION)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            REQUEST_CODE_LOCATION -> if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                mapa.isMyLocationEnabled = true
            }else{
                Toast.makeText(this, "Acepta los permisos en ajustes para activar la localizacion", Toast.LENGTH_SHORT).show()
            }
            else -> {}//por si acaso ha aceptado el permiso que no es (no va a pasar)
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        if(!::mapa.isInitialized) return //si el mapa no ha sido inicializado

        if(!isLocationPermissionGranted()) {
            mapa.isMyLocationEnabled = false
            Toast.makeText(this, "Acepta los permisos en ajustes para activar la localizacion", Toast.LENGTH_SHORT).show()
        }
    }

    //click en el boton de ir a mi localizacion
    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(this, "Boton pulsado", Toast.LENGTH_SHORT).show()
        return false //asi es por defecto, si le pones true no te lleva a tu localizaconi
    }

    //a este metodo se le llama cada vez que el usuario pulsa literalmente en su posicion (el simbolito azul con la flecha)
    override fun onMyLocationClick(p0: Location) {
        Toast.makeText(this, "Estás en  ${p0.latitude}, ${p0.longitude} ", Toast.LENGTH_SHORT).show()
    }

}