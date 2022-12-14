package com.example.expense_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.User
import com.google.firebase.database.FirebaseDatabase
import kotlin.coroutines.coroutineContext

class MyAdapter (private val userList :ArrayList<User>) :RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private lateinit var database : FirebaseDatabase

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_item,
            parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        database=FirebaseDatabase.getInstance()
        val currentitem = userList[position]
        holder.firstName.text=currentitem.firstName
        holder.lastName.text =currentitem.lastName
        holder.age.text=currentitem.tot
        holder.number.text=currentitem.number
        holder.amt.text=currentitem.age
        holder.time.text=currentitem.time
        holder.btn.setOnClickListener {
//            val bundle=Bundle()
//            val intent = Intent(context, bridge:: class.java)
//                startActivity(context,intent,bundle)
            val databaseRef = database.reference.child("items")
            databaseRef.get().addOnSuccessListener { result->
                for (document in result.children){
                    val item = document.getValue(User::class.java)
                    if(item?.time==currentitem.time)
                    {
                        document.ref.removeValue()
//                        Toast.makeText(context,"Enter valid details", Toast.LENGTH_SHORT).show()
                    }
                }
            }

//                    val intent = Intent(this, UseListActivity3:: class.java)
//                    intent.putExtra("email",ss)
//                    startActivity(intent)



        }

    }

    override fun getItemCount(): Int {
        return  userList.size

    }
    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val firstName: TextView = itemView.findViewById(R.id.Name)
        val lastName : TextView = itemView.findViewById(R.id.lflf)
        val age : TextView =itemView.findViewById(R.id.locationx)
        val btn: Button =itemView.findViewById(R.id.delete)
        val number : TextView =itemView.findViewById(R.id.number)
        val amt : TextView =itemView.findViewById(R.id.amt)
        val time : TextView =itemView.findViewById(R.id.time)

    }

}