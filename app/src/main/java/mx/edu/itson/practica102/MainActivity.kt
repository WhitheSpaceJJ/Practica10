package mx.edu.itson.practica102

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import mx.edu.itson.practica10.User

class MainActivity : AppCompatActivity() {
    private val userRef= FirebaseDatabase.getInstance().getReference("usuarios")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnSave: Button =findViewById(R.id.bt_guardar)
        btnSave.setOnClickListener{
            saveMarkFromForm()
        }
        userRef.addChildEventListener(object: ChildEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val usuario=snapshot.getValue(User::class.java)
                if(usuario!=null)writeMark(usuario)
            }
        })
    }
    private fun saveMarkFromForm(){
        val name: EditText =findViewById(R.id.et_nombre)
        val apellido: EditText =findViewById(R.id.et_apellido)
        val edad: EditText =findViewById(R.id.et_edad)
        val usuario= User(
            name.text.toString(),
            apellido.text.toString(),
            edad.text.toString()
        )
        userRef.push().setValue(usuario)
    }
    private fun writeMark(mar: User)
    {
        val list: TextView =findViewById(R.id.listViewText)
        val text=list.text.toString()+mar.toString()+"\n"
        list.text=text
    }
}