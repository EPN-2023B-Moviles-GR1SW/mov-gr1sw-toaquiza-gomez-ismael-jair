// RecyclerViewAdaptadorOutlook.kt
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.deber03.R
import com.example.deber03.model.Correo

class RecyclerViewAdaptadorOutlook(
    private val context: Context,
    private val lista: ArrayList<Correo>,
    private val recyclerView: RecyclerView
) :
    RecyclerView.Adapter<RecyclerViewAdaptadorOutlook.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val tituloTextView: TextView = itemView.findViewById(R.id.tituloTextView)
        val asuntoTextView: TextView = itemView.findViewById(R.id.asuntoTextView)
        val cuerpoTextView: TextView = itemView.findViewById(R.id.cuerpoTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_correo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val correo = lista[position]
        holder.imageView.setImageResource(correo.imagen)
        holder.tituloTextView.text = correo.titulo
        holder.asuntoTextView.text = correo.asunto
        holder.cuerpoTextView.text = correo.cuerpo
    }

    override fun getItemCount(): Int {
        return lista.size
    }
}

