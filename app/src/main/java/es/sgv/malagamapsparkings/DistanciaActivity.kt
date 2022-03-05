package es.sgv.malagamapsparkings

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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

        val btnComprobarCoordenadasManuales = findViewById<Button>(R.id.btnComprobarDistanciaCoordenadasManuales)
        btnComprobarCoordenadasManuales.setOnClickListener {
            compararCoordenadasManuales()
        }

        val btnListar = findViewById<Button>(R.id.btnListarParkings)
        btnListar.setOnClickListener {
            val intent = Intent(this, ListadoActivity::class.java)
            startActivity(intent)
        }

        var miLatitud = intent.getStringExtra("miLatitud")?.toDouble()
        var miLongitud = intent.getStringExtra("miLongitud")?.toDouble()

        comprobarParkingMasCercano(miLatitud, miLongitud)

    }

    private fun comprobarParkingMasCercano(miLatitud: Double?, miLongitud: Double?) {
        if (miLatitud !=null && miLongitud!=null){

            var distanciaMenor: Double
            var res = FloatArray(1)
            var i2=0

            Location.distanceBetween(miLatitud, miLongitud, MainActivity.getMisMarkers().get(0).position.latitude, MainActivity.getMisMarkers().get(0).position.longitude, res)
            distanciaMenor = res[0].toDouble()

            try {
                for (i in 1..MainActivity.getMisMarkers().size)
                {

                    Location.distanceBetween(miLatitud, miLongitud, MainActivity.getMisMarkers().get(i).position.latitude, MainActivity.getMisMarkers().get(i).position.longitude, res)

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

            txtLatitud.text = "Lat: " + miLatitud.toString()
            txtLongitud.text = "Long: " + miLongitud.toString()
            tvParkingMasCercano.text = MainActivity.getMisMarkers().get(i2).title.toString()
            tvDistancia.text = "" +distanciaMenor /1000 + " kilómetros"


        }

    }

    private fun compararCoordenadasManuales()
    {
        var menorDistancia:Double = 9999999999999999999999999.9 //TODO: dejar bonito //arreglarlo con el null para que esté mejor
        var res = FloatArray(1)
        var i2 = 0

        var etManualLatitud: EditText
        etManualLatitud = findViewById(R.id.etIntroducirLatitud)

        var etManualLongitud: EditText
        etManualLongitud = findViewById(R.id.etIntroducirLongitud)

        var tvDistanciaManual:TextView
        tvDistanciaManual = findViewById(R.id.tvDistanciaParkingMasCercanoManual)

        //

        try {
            var manualLat:Double = etManualLatitud.text.toString().trim().toDouble()
            var manualLong:Double = etManualLongitud.text.toString().trim().toDouble()

            if (manualLat != 0.0 && manualLong != 0.0)
            {
                Location.distanceBetween(manualLat, manualLong, MainActivity.getMisMarkers().get(0).position.latitude, MainActivity.getMisMarkers().get(0).position.longitude, res)
                menorDistancia = res[0].toDouble()

                for (i in 1..MainActivity.getMisMarkers().size)
                {

                    Location.distanceBetween(manualLat, manualLong, MainActivity.getMisMarkers().get(i).position.latitude, MainActivity.getMisMarkers().get(i).position.longitude, res)

                    if (res[0].toDouble() < menorDistancia){
                        menorDistancia = res[0].toDouble()
                        i2=i
                    }

                }
            }

            var s = "El parking más cercano a tu localización es el de " + MainActivity.getMisMarkers().get(i2).title.toString() + "\n Se encuentra a " + menorDistancia /1000 + " kilómetros de distancia."
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
            tvDistanciaManual.text = s.toString()

        }catch (e:Exception){
            Toast.makeText(this, "Coordenadas introducidas de forma errónea" , Toast.LENGTH_LONG).show()
        }



    }


}