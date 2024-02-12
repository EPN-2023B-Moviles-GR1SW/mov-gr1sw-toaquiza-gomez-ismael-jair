package com.example.b2023_gr1sw_ijtg

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    val callbackContenidoIntentExplicito =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode === Activity.RESULT_OK) {
                if (result.data != null) {
                    // Logica Negocio
                    val data = result.data
                    mostrarSnackbar("${data?.getStringExtra("nombreModificado")}")
                }
            }
        }

    val callbackIntentPickUri =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode === RESULT_OK) {
                if (result.data != null) {
                    if (result.data!!.data != null) {
                        val uri: Uri = result.data!!.data!!
                        val cursor = contentResolver.query(
                            uri, null, null, null, null, null
                        )
                        cursor?.moveToFirst()
                        val indiceTelefono = cursor?.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                        )
                        val telefono = cursor?.getString(indiceTelefono!!)
                        cursor?.close()
                        mostrarSnackbar("Telefono ${telefono}")
                    }
                }
            }
        }

    fun mostrarSnackbar(texto: String) {
        val snack = Snackbar.make(
            findViewById(R.id.id_layout_main),
            texto, Snackbar.LENGTH_LONG
        )
        snack.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Instanciar base de datos
        EBaseDeDatos.tablaEntrenador = ESqliteHelperEntrenador(this)

        val botonCicloVida = findViewById<Button>(R.id.btn_ciclo_vida)
        botonCicloVida.setOnClickListener {
            irActividad(ACicloVida::class.java)
        }

        val botonListView = findViewById<Button>(R.id.btn_ir_list_view)
        botonListView.setOnClickListener {
            irActividad(BListView::class.java)
        }

        val botonIntentImplicito = findViewById<Button>(
            R.id.btn_ir_intent_implicito
        )
        botonIntentImplicito.setOnClickListener {
            val intentConRespuesta = Intent(
                Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            )
            callbackIntentPickUri.launch(intentConRespuesta)
        }

        val botonIntentExplicito = findViewById<Button>(
            R.id.btn_ir_intent_explicito
        )
        botonIntentExplicito.setOnClickListener {
            abrirActividadConParametros(
                CIntentExplicitoParametros::class.java
            )
        }
        val botonSqlite = findViewById<Button>(R.id.btn_sqlite)
        botonSqlite.setOnClickListener() {
            irActividad(ECrudEntrenador::class.java)
        }

        val botonRView = findViewById<Button>(R.id.btn_revcycler_view)
        botonRView
            .setOnClickListener {
                irActividad(FRecyclerView::class.java)
            }
        val botonGoogleMaps = findViewById<Button>(R.id.btn_google_maps)
        botonGoogleMaps
            .setOnClickListener {
                irActividad(GGoogleMapsActivity::class.java)
            }
        val botonFirebaseUI = findViewById<Button>(R.id.btn_intent_firebase_ui)
        botonFirebaseUI
            .setOnClickListener {
                irActividad(HFirebaseUIAuth::class.java)
            }
        val botonFirestore = findViewById<Button>(R.id.btn_intent_firestore)
        botonFirestore
            .setOnClickListener {
                irActividad(IFirestore::class.java)
            }
    }

    private fun abrirActividadConParametros(clase: Class<*>) {
        val intentExplicito = Intent(this, clase)
        // Enviar parametros (solamente variables primitivas)
        intentExplicito.putExtra("nombre", "Ismael")
        intentExplicito.putExtra("apellido", "Toaquiza")
        intentExplicito.putExtra("edad", 22)
        intentExplicito.putExtra(
            "objetoEntrenador",
            BEntrenador(1,"Ismael","Descripcion")
        )


        callbackContenidoIntentExplicito.launch(intentExplicito)
    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}