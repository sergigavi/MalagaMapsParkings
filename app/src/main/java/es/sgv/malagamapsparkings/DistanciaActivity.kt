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
            var l = MainActivity.getMisMarkers()

            Location.distanceBetween(miLatitud, miLongitud, l.get(0).position.latitude, MainActivity.getMisMarkers().get(0).position.longitude, res)
            distanciaMenor = res[0].toDouble()

            try {
                for (i in 1..MainActivity.getMisMarkers().size)
                {

                    Location.distanceBetween(miLatitud, miLongitud, l.get(i).position.latitude, MainActivity.getMisMarkers().get(i).position.longitude, res)

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

            var tvParkingMasCercano:TextView
            tvParkingMasCercano = findViewById(R.id.tvParkingMasCercano)

            var tvDistancia:TextView
            tvDistancia = findViewById(R.id.tvDistanciaParkingMasCercano)

            //

            var respuesta:String = "Tus coordenadas son: Latitud - " + miLatitud + ", Longitud - " + miLongitud + "." + "\nEl parking más cercano a tu localización es el de " + MainActivity.getMisMarkers().get(i2).title.toString() + ", a " + distanciaMenor /1000 + " kilómetros de distancia."
            //Toast.makeText(this, respuesta , Toast.LENGTH_LONG).show()

            txtLatitud.text = miLatitud.toString()
            txtLongitud.text = miLongitud.toString()
            tvParkingMasCercano.text = MainActivity.getMisMarkers().get(i2).title.toString()
            tvDistancia.text = "" +distanciaMenor /1000 + "kilómetros"

        }

    }


}