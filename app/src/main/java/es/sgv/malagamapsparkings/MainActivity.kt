package es.sgv.malagamapsparkings


import androidx.fragment.app.FragmentManager
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import java.util.jar.Manifest

class MainActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener  {

    private lateinit var mapa:GoogleMap

    companion object{
        const val REQUEST_CODE_LOCATION = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createMapFragment()
    }

    private fun createMapFragment() {
        val mapFragment:SupportMapFragment = supportFragmentManager.findFragmentById(R.id.mapa) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    //este metodo se llama cuando el map haya sido cargado
    override fun onMapReady(mapaCreado: GoogleMap) {
        mapa = mapaCreado
        //createMarker(36.718926, -4.418061, "Málaga")
        mapa.animateCamera(
            CameraUpdateFactory.newLatLngZoom(LatLng(36.718926, -4.418061),12f),
            2500,
            null
        )
        mapa.setOnMyLocationButtonClickListener(this)
        mapa.setOnMyLocationClickListener(this)
        enableLocation()

        createMarkers()
        createPolylines()
    }

    //

    private fun createMarker(latitude:Double, longitude:Double, titulo:String) {
        val coordenadas = LatLng(latitude, longitude)
        val marker:MarkerOptions = MarkerOptions().position(coordenadas).title(titulo)
        mapa.addMarker(marker)
    }

    private fun createMarkers() {

        mapa.addMarker(MarkerOptions().position(LatLng(36.717945709280535, -4.4209885597229)).title("Parking Central"))
        mapa.addMarker(MarkerOptions().position(LatLng(36.715757646413046, -4.424395271514863)).title("Garaje Málaga"))
        mapa.addMarker(MarkerOptions().position(LatLng(36.71436751762987, -4.424648157988138)).title("Parking Alemania"))
        mapa.addMarker(MarkerOptions().position(LatLng(36.7083624215518, -4.41416648632701)).title("Málaga Cruise terminal"))
        mapa.addMarker(MarkerOptions().position(LatLng(36.719128808752735, -4.414080196160326)).title("Parking Muelle Uno"))
        mapa.addMarker(MarkerOptions().position(LatLng(36.72000723055114, -4.4100939844815015)).title("Parking Cervantes"))
        mapa.addMarker(MarkerOptions().position(LatLng(36.72023889429903, -4.408781367656602)).title("Parking de la Malagueta"))
        mapa.addMarker(MarkerOptions().position(LatLng(36.7230797651669, -4.416673675450237)).title("Aparcamiento Alcazaba"))
        mapa.addMarker(MarkerOptions().position(LatLng(36.723002711688146, -4.419805636637146)).title("Parking Granados"))
        mapa.addMarker(MarkerOptions().position(LatLng(36.72381768457455, -4.421638774824797)).title("Parking Tejon y Rodriguez"))
        mapa.addMarker(MarkerOptions().position(LatLng(36.72610202150593, -4.419189843500244)).title("Parking Atlántida"))

    }

    private fun createPolylines(){
        //parking central
        var polylineOptions = PolylineOptions()
            .add(LatLng(36.717954309390805, -4.422565698623657))
            .add(LatLng(36.71708569339006,-4.42225456237793))
            .add(LatLng(36.717765106742185,-4.419722557067871))
            .add(LatLng(36.71866811518787,-4.41975474357605))
            .add(LatLng(36.717954309390805,-4.422565698623657 ))

        var polyLine: Polyline = mapa.addPolyline(polylineOptions)

        //garaje malaga
        polylineOptions = PolylineOptions()
            .add(LatLng(36.71547042044409, -4.424337967914071))
            .add(LatLng(36.71567314423374, -4.423645513072429))
            .add(LatLng(36.715909657169554, -4.423735831877776))
            .add(LatLng(36.7157889882203, -4.424428288831428))
            .add(LatLng(36.71547042044409, -4.424337967914071))

        polyLine= mapa.addPolyline(polylineOptions)

        //Parking Alemania
        polylineOptions = PolylineOptions()
            .add(LatLng(36.71465951173844, -4.4245186083219386))
            .add(LatLng(36.714490566430015, -4.425289338381117))
            .add(LatLng(36.71324524321061, -4.4246330037680766))
            .add(LatLng(36.71332247445023, -4.424211516989545))
            .add(LatLng(36.71352520265581, -4.424067007117632))
            .add(LatLng(36.71465951173844, -4.4245186083219386))

        polyLine= mapa.addPolyline(polylineOptions)

        //Málaga Cruise terminal
        polylineOptions = PolylineOptions()
            .add(LatLng(36.70869454029994, -4.413431078864889))
            .add(LatLng(36.7071561176001, -4.414767821442734))
            .add(LatLng(36.7082845784525, -4.416555159195147))
            .add(LatLng(36.70941709763557, -4.414929823949872))
            .add(LatLng(36.70869454029994, -4.413431078864889))

        polyLine= mapa.addPolyline(polylineOptions)

        //Parking Muelle Uno
        polylineOptions = PolylineOptions()
            .add(LatLng(36.72002660167358, -4.412838096509206))
            .add(LatLng(36.71977495731775, -4.412493789437807))
            .add(LatLng(36.71936910521472, -4.413901426934574))
            .add(LatLng(36.718581719970345, -4.413820421582032))
            .add(LatLng(36.717867385072054, -4.4161900524273765))
            .add(LatLng(36.71925544579016, -4.416828067621036))
            .add(LatLng(36.72002660167358, -4.412838096509206))

        polyLine= mapa.addPolyline(polylineOptions)

        //Parking Cervantes
        polylineOptions = PolylineOptions()
            .add(LatLng(36.720055971845525, -4.41197737003281))
            .add(LatLng(36.71976778828588, -4.4091874808456994))
            .add(LatLng(36.72087172484813, -4.40903048071999))
            .add(LatLng(36.72093668724014, -4.411825473403575))
            .add(LatLng(36.720055971845525, -4.41197737003281))

        polyLine= mapa.addPolyline(polylineOptions)

        //Parking de la Malagueta
        polylineOptions = PolylineOptions()
            .add(LatLng(36.72020518223683, -4.4090810619582586))
            .add(LatLng(36.71972735278452, -4.409123219180571))
            .add(LatLng(36.719510104348075, -4.406485849095536))
            .add(LatLng(36.72020510313347, -4.405612694656505))
            .add(LatLng(36.72020518223683, -4.4090810619582586))

        polyLine= mapa.addPolyline(polylineOptions)

        //Aparcamiento Alcazaba
        polylineOptions = PolylineOptions()
            .add(LatLng(36.722692217434144, -4.416954925620978))
            .add(LatLng(36.72290934576316, -4.416810622109712))
            .add(LatLng(36.722519734339805, -4.4158561829997955))
            .add(LatLng(36.72223767151929, -4.415906817430685))
            .add(LatLng(36.722692217434144, -4.416954925620978))

        polyLine= mapa.addPolyline(polylineOptions)

        //Parking Granados
        polylineOptions = PolylineOptions()
            .add(LatLng(36.7233059476188, -4.420031184794837))
            .add(LatLng(36.72283328107311, -4.4204441827117495))
            .add(LatLng(36.72252443058331, -4.42007588587949))
            .add(LatLng(36.72303292958978, -4.41959263474707))
            .add(LatLng(36.7233059476188, -4.420031184794837))

        polyLine= mapa.addPolyline(polylineOptions)

        //Parking Tejon y Rodriguez
        polylineOptions = PolylineOptions()
            .add(LatLng(36.723296488074766, -4.421127800362509))
            .add(LatLng(36.723081015135186, -4.421301045384556))
            .add(LatLng(36.72368242568276, -4.422041813924919))
            .add(LatLng(36.72378298059989, -4.421653506406788))
            .add(LatLng(36.723296488074766, -4.421127800362509))

        polyLine= mapa.addPolyline(polylineOptions)

        //Parking Atlántida
        polylineOptions = PolylineOptions()
            .add(LatLng(36.72596908686705, -4.419179855665685))
            .add(LatLng(36.72645554388215, -4.418520270821624))
            .add(LatLng(36.72593157207714, -4.418059591612865))
            .add(LatLng(36.72564673405249, -4.418654825451107))
            .add(LatLng(36.72596908686705, -4.419179855665685))

        polyLine= mapa.addPolyline(polylineOptions)
    }

    //


    //devuelve si el permiso está
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

    //en el caso de que no estén aceptados
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