package app.pmsoft.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import app.pmsoft.demo.model.AppDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val allFoos = AppDatabase.getDatabase(applicationContext).fooDao().getAll()

        val mainTextView = findViewById<TextView>(R.id.main_text_view)
        mainTextView.text = allFoos.joinToString(", ") { it.letter }
    }
}