package com.example.a2024a_swgr2_dacl

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    fun mostrarSnackbar(texto: String){
        val snack = Snackbar.make(
            findViewById(R.id.id_layout_main),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }

    val callbackContenidoIntentExplicito =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            result ->
            if(result.resultCode == Activity.RESULT_OK){
                if(result.data != null){
                    //logica de negocio, ya entramos al intent
                    val data = result.data
                    mostrarSnackbar(
                        "${data?.getStringArrayExtra("nombreModificado")}"
                    )
                }
            }
        }

    val callbackContenidoIntentImplicito =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            result ->
            if(result.resultCode == Activity.RESULT_OK){
                if(result.data != null){
                    //logica de negocio, ya entramos al intent
                    if (result.data!!.data != null){

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
                        mostrarSnackbar("Telefono $telefono")

                    }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.id_layout_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val botonACicloVda = findViewById<Button>(R.id.btn_ciclo_vida)

        botonACicloVda.setOnClickListener{
            irActividad(ACicloVda::class.java)
        }

        val botonBListView = findViewById<Button>(R.id.btn_ir_list_view)

        botonBListView.setOnClickListener {
            irActividad(BListView::class.java)
        }

        val botonIntentImplicito =findViewById<Button>(
            R.id.btn_ir_intent_implicito
        )

        botonIntentImplicito.setOnClickListener {
            val intentConRespuesta = Intent(
                Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            )
            callbackContenidoIntentImplicito.launch(intentConRespuesta)
        }

        val botonIntentExplicito = findViewById<Button>(
            R.id.btn_ir_intent_explicito
        )
        botonIntentExplicito.setOnClickListener {
            val intentExplicito = Intent(
                this,
                CIntentExplicitoParametros::class.java
            )
            intentExplicito.putExtra("nombre", "Adrian")
            intentExplicito.putExtra("apellido", "Eguez")
            intentExplicito.putExtra("edad", 34)
            intentExplicito.putExtra(
                "entrenador" ,
                BEntrenador(10 ,"Adrian", "Eguez")
            )
            callbackContenidoIntentExplicito.launch(intentExplicito)
        }

        //Inicializar base de datos
        EBaseDeDatos.tablaEntrenador = ESqliteHelperEntrenador(
            this
        )

        val btnSQLite = findViewById<Button>(R.id.btn_sqlite)
        btnSQLite.setOnClickListener {
            irActividad(ECrudEntrenador::class.java)
        }

        val botonRView = findViewById<Button>(R.id.btn_recycler_view)
        botonRView.setOnClickListener {
            irActividad(FRecyclerView::class.java)
        }

        val botonGMaps = findViewById<Button>(R.id.btn_google_maps)
        botonGMaps.setOnClickListener{
            irActividad(GoogleMapsActivity::class.java)
        }

        val botonFirebaseAuth = findViewById<Button>(R.id.btn_firebase)
        botonFirebaseAuth.setOnClickListener {
            irActividad(HFirebaseUIAuth::class.java)
        }
    }

    fun irActividad( clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}