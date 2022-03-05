package es.sgv.malagamapsparkings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.model.MarkerOptions

class AdapterParkings (private val datos: ArrayList<MarkerOptions>) : RecyclerView.Adapter<AdapterParkings.ViewHolderDatos>()  {

    private lateinit var listener: OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

    //

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterParkings.ViewHolderDatos {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_parkings, parent, false)
        return ViewHolderDatos(view)
    }

    override fun onBindViewHolder(holder: ViewHolderDatos, position: Int) {
        holder.asignarDatos(datos[position])
    }

    override fun getItemCount(): Int {
        return datos.size
    }



    //

    class ViewHolderDatos(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var nombre = itemView.findViewById<TextView>(R.id.txtNombreAdapter)
        var lati = itemView.findViewById<TextView>(R.id.txtLatAdapter)
        var long = itemView.findViewById<TextView>(R.id.txtLongAdapter)

        fun asignarDatos(datos: MarkerOptions) {
            nombre.text = datos.title
            lati.text = datos.position.latitude.toString()
            long.text = datos.position.longitude.toString()
        }
    }
}