package com.example.todoapp

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var rvMain: RecyclerView
    private lateinit var rvAdapter: ToDoRecyclerViewAdapter
    private lateinit var fabAddNewDo: FloatingActionButton
    private var toDoObjects = ArrayList<ToDoList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMain = findViewById(R.id.rvMain)
        fabAddNewDo = findViewById(R.id.fabAddNewDo)

        //AlminPiricDojo code
        //https://github.com/AlminPiricDojo/ToDoApp/blob/master/app/src/main/java/com/example/todoapp/MainActivity.kt
        rvAdapter = ToDoRecyclerViewAdapter(toDoObjects)
        rvMain.adapter = rvAdapter
        rvMain.layoutManager = LinearLayoutManager(this)
        //

        fabAddNewDo.setOnClickListener{
            alartForAddNewTask()
            rvMain.adapter!!.notifyDataSetChanged()
        }
    }

    private fun alartForAddNewTask(){
        val dialogBuilder = AlertDialog.Builder(this)
        // then we set up the input
        val newTask = EditText(this)
        dialogBuilder.setMessage("")
            .setPositiveButton("Add") { _, _ ->
                if(newTask.text.isNotEmpty()) addNewTaskToList(newTask.text.toString())
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }

        val alart = dialogBuilder.create()
        alart.setTitle("New Task")
        alart.setView(newTask)
        alart.show()
    }


    private fun addNewTaskToList(text: String){
        toDoObjects.add(ToDoList(text, false))
        rvMain.adapter!!.notifyDataSetChanged()
    }


    // menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //AlminPiricDojo code
        //https://github.com/AlminPiricDojo/ToDoApp/blob/master/app/src/main/java/com/example/todoapp/MainActivity.kt
        // if there is a one item in menu we don't need to use "when" block
        var itemsDeleted = 0
        for(i in toDoObjects){
            if(i.check){itemsDeleted++}
        }

        if(itemsDeleted > 0){
            Toast.makeText(this, "$itemsDeleted items deleted", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "No items selected", Toast.LENGTH_LONG).show()
        }
        rvAdapter.deleteItems()
        return super.onOptionsItemSelected(item)
    }

}