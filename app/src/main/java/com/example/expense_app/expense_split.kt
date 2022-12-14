package com.example.expense_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class expense_split : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_split)
        var its =intent.getStringExtra("itemsList")
        var it =intent.getStringExtra("itemsList1")
        var str = intent.getStringExtra("desc")
        var str1 = intent.getStringExtra("itemsList3")
        findViewById<TextView>(R.id.amont).text = its+" amount must be paid by each member among "+it+" members of the group. For "+str+"that took place on "+str1
      //  findViewById<TextView>(R.id.stri).text = it+"

//        findViewById<TextView>(R.id.stri).text = str.toString()
    }
}