package com.example.sqlitedemo

import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlitedemo.adapter.StudentAdapter
import com.example.sqlitedemo.db.StudentDAO
import com.example.sqlitedemo.model.Student

class MainActivity : AppCompatActivity() {

    private lateinit var studentDAO: StudentDAO
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studentDAO = StudentDAO(this)

        val etNama = findViewById<EditText>(R.id.etNama)
        val etUsia = findViewById<EditText>(R.id.etUsia)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StudentAdapter(
            students = studentDAO.getAllStudents().toMutableList(),
            onEdit = { student -> showEditDialog(student) },
            onDelete = { student -> confirmDelete(student) }
        )
        recyclerView.adapter = adapter

        btnSimpan.setOnClickListener {
            val nama = etNama.text.toString().trim()
            val usia = etUsia.text.toString().trim().toIntOrNull() ?: 0

            if (nama.isNotEmpty()) {
                studentDAO.insertStudent(nama, usia)
                etNama.text.clear()
                etUsia.text.clear()
                adapter.updateData(studentDAO.getAllStudents())
                Toast.makeText(this, getString(R.string.msg_saved), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, getString(R.string.msg_name_required), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showEditDialog(student: Student) {
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(50, 40, 50, 10)
        }

        val etNama = EditText(this).apply {
            setText(student.name)
            hint = getString(R.string.hint_name)
        }

        val etUsia = EditText(this).apply {
            setText(student.age.toString())
            hint = getString(R.string.hint_age)
            inputType = InputType.TYPE_CLASS_NUMBER
        }

        layout.addView(etNama)
        layout.addView(etUsia)

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.title_edit_student))
            .setView(layout)
            .setPositiveButton(getString(R.string.action_save)) { _, _ ->
                val namaBaru = etNama.text.toString().trim()
                val usiaBaru = etUsia.text.toString().trim().toIntOrNull() ?: 0

                if (namaBaru.isNotEmpty()) {
                    studentDAO.updateStudent(student.id, namaBaru, usiaBaru)
                    adapter.updateData(studentDAO.getAllStudents())
                    Toast.makeText(this, getString(R.string.msg_updated), Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, getString(R.string.msg_name_required), Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton(getString(R.string.action_cancel), null)
            .show()
    }

    private fun confirmDelete(student: Student) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.title_delete_student))
            .setMessage(getString(R.string.msg_confirm_delete, student.name))
            .setPositiveButton(getString(R.string.action_yes)) { _, _ ->
                studentDAO.deleteStudent(student.id)
                adapter.updateData(studentDAO.getAllStudents())
                Toast.makeText(this, getString(R.string.msg_deleted), Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton(getString(R.string.action_cancel), null)
            .show()
    }
}
