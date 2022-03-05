package es.sgv.malagamapsparkings

import android.location.Location
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.lang.Exception

class DistanciaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_distancia)

        var miLatitud = intent.getStringExtra("miLatitud")?.toDouble()
        var miLongitud = intent.getStringExtra("miLongitud")?.toDouble()

        comprobarParkingMasCercano(miLatitud, miLongitud)

    }

    private fun comprobarParkingMasCercano(miLatitud: Double?, miLongitud: Double?) {
        if (miLatitud !=null && miLongitud!=null){

            var distanciaMenor: Double
            var res = FloatArray(15)
            var i2=0
            var l = getMisMarkers()

            Location.distanceBetween(miLatitud, miLongitud, l.get(0).position.latitude, getMisMarkers().get(0).position.longitude, res)
            distanciaMenor = res[0].toDouble()

            try {
                for (i in 1..getMisMarkers().size)
                {

                    Location.distanceBetween(miLatitud, miLongitud, l.get(i).position.latitude, getMisMarkers().get(i).position.longitude, res)

                    if (res[0].toDouble() < distanciaMenor){
                        distanciaMenor = res[0].toDouble()
                        i2=i
                    }

                }
            } catch (e: Exception) {

            }

            //inicializo las variables de la interfaz
            var txtLatitud:TextView
            txtLatitud = findViewById(R.id.txtLatitud)

            var txtLongitud:TextView
            txtLongitud = findViewById(R.id.txtLongitud)

            //

            var respuesta:String = "Tus coordenadas son: Latitud - " + miLatitud + ", Longitud - " + miLongitud + "." + "\nEl parking más cercano a tu localización es el de " + getMisMarkers().get(i2).title.toString() + ", a " + distanciaMenor /1000 + " kilómetros de distancia."
            //Toast.makeText(this, respuesta , Toast.LENGTH_LONG).show()

            txtLatitud.text = miLatitud.toString()
            txtLongitud.text = miLongitud.toString()

        }

    }


    private fun getMisMarkers():ArrayList<MarkerOptions>{
        var listaMarkers: ArrayList<MarkerOptions>

        listaMarkers = ArrayList()
        listaMarkers.add(MarkerOptions().position(LatLng(36.717945709280535, -4.4209885597229)).title("Parking Central"))
        listaMarkers.add(MarkerOptions().position(LatLng(36.715757646413046, -4.424395271514863)).title("Garaje Málaga"))
        listaMarkers.add(MarkerOptions().position(LatLng(36.71436751762987, -4.424648157988138)).title("Parking Alemania"))
        listaMarkers.add(MarkerOptions().position(LatLng(36.7083624215518, -4.41416648632701)).title("Málaga Cruise terminal"))
        listaMarkers.add(MarkerOptions().position(LatLng(36.719128808752735, -4.414080196160326)).title("Parking Muelle Uno"))
        listaMarkers.add(MarkerOptions().position(LatLng(36.72000723055114, -4.4100939844815015)).title("Parking Cervantes"))
        listaMarkers.add(MarkerOptions().position(LatLng(36.72023889429903, -4.408781367656602)).title("Parking de la Malagueta"))
        listaMarkers.add(MarkerOptions().position(LatLng(36.7230797651669, -4.416673675450237)).title("Aparcamiento Alcazaba"))
        listaMarkers.add(MarkerOptions().position(LatLng(36.723002711688146, -4.419805636637146)).title("Parking Granados"))
        listaMarkers.add(MarkerOptions().position(LatLng(36.72381768457455, -4.421638774824797)).title("Parking Tejon y Rodriguez"))
        listaMarkers.add(MarkerOptions().position(LatLng(36.72610202150593, -4.419189843500244)).title("Parking Atlántida"))

        return  listaMarkers
    }
}