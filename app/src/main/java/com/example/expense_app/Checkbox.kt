package com.example.expense_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.User
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDateTime

class Checkbox : AppCompatActivity() {

    private lateinit var database : FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkbox)
        database = FirebaseDatabase.getInstance()
        val per1 = findViewById<CheckBox>(R.id.person1)
        val per2 = findViewById<CheckBox>(R.id.person2)
        val per3 = findViewById<CheckBox>(R.id.person3)
        val per4 = findViewById<CheckBox>(R.id.person4)
        val per5 = findViewById<CheckBox>(R.id.person5)
        val per6 =findViewById<CheckBox>(R.id.person6)
        val per7 =findViewById<CheckBox>(R.id.person7)
        val per8 =findViewById<CheckBox>(R.id.person8)

        val frm: EditText= findViewById(R.id.from)
        val amt: EditText= findViewById(R.id.amt)
        val prosp: EditText=findViewById(R.id.desc)
        val buttn =  findViewById<Button>(R.id.btn)

        val ar = arrayOf<CheckBox>(per1,per2,per3,per4,per5,per6,per7)
//        val arr = arrayOf<String>("shreya","amisha","rahul","kriti")
        //val  ax= arrayOf<String()
        var arrayl =ArrayList<String>()



        val i:Int =0



        //println(c)

        buttn.setOnClickListener ()

        {
            var st: String = ""
            val from = frm.getText().toString()
            val amt_ck = amt.getText().toString()
            val pro = prosp.getText().toString()
            val k = amt_ck.toDouble()
            var amt_n: Double
            var c: Int
            if (from.isEmpty() || pro.isEmpty() || amt_ck.isEmpty()) {
                if (from.isEmpty()) {
                    frm.error = "Enter whom to give"


                }

                if (pro.isEmpty()) {
                    prosp.error = "Enter purpose"
                }

                if (amt_ck.isEmpty()) {
                    amt.error = "Enter amt "
                }
            }
            else{
            c = 1;
            for (i in ar.indices) {
                if (ar[i].isChecked) {
                    c++

                }


            }
            amt_n = k / c

            // Toast.makeText(applicationContext,"output "+c.toString(),Toast.LENGTH_SHORT).show()
            val item = User(
                from,
                pro,
                amt_n.toString(),
                LocalDateTime.now().toString(),
                c.toString(),
                amt_ck.toString()
            )
            val databaseRef = database.reference.child("items")
            databaseRef.push().setValue(item).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Added successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, expense_split::class.java)
                    intent.putExtra("itemsList", amt_n.toString())
                    intent.putExtra("itemsList1", c.toString())
                    intent.putExtra("itemsList3", LocalDateTime.now().toString())
                    intent.putExtra("desc",pro)



                    startActivity(intent)

                } else {
                    Toast.makeText(this, "Cri aarha hai", Toast.LENGTH_SHORT).show()
                }

            }

        }

    }}
}