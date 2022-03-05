package es.sgv.malagamapsparkings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.model.MarkerOptions

class ListadoActivity : AppCompatActivity() {

    //
    private lateinit var listaMarkers: ArrayList<MarkerOptions>
    private lateinit var rvMarkers: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado)

        listaMarkers = MainActivity.getMisMarkers()
        rvMarkers = findViewById(R.id.rvParkings)
        rellenarRV()
    }

    //Rellenar el RecycleViewer
    private fun rellenarRV() {
        rvMarkers.layoutManager = LinearLayoutManager(this)
        var adapter = AdapterParkings(listaMarkers)

        adapter.setOnItemClickListener(object : AdapterParkings.OnItemClickListener{
            override fun onItemClick(position: Int) {
                accionRV(position)
            }
        })

        rvMarkers.adapter = adapter
    }

    //Accion al pulsar un campo del RecycleViewer
    private fun accionRV(position: Int) {
        var estudios = listaMarkers[position]

        Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
    }

}