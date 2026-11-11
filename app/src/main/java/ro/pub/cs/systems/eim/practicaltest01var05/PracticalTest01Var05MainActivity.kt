package ro.pub.cs.systems.eim.practicaltest01var05

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PracticalTest01Var05MainActivity : AppCompatActivity() {


    // ===== BUSOLA =====
    private lateinit var compassDirectionsText: TextView
    private lateinit var compassClicksText: TextView
    private var compassClicks = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_var05_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ===== NAVIGATE =====
        val navigateButton = findViewById<Button>(R.id.navigate_button)
        navigateButton.setOnClickListener {
            val intent = Intent(this, PracticalTest01Var05SecondaryActivity::class.java)
            startActivity(intent)
       }


        // butoanele
        compassDirectionsText = findViewById(R.id.compass_directions_text)
        compassClicksText = findViewById(R.id.compass_clicks_text)

        val compassListener = View.OnClickListener { view ->
            val direction = (view as Button).text.toString()
            compassDirectionsText.text = compassDirectionsText.text.toString() + " ," + direction
            compassClicks++
            compassClicksText.text = compassClicks.toString()
        }
        findViewById<Button>(R.id.top_left_button).setOnClickListener(compassListener)
        findViewById<Button>(R.id.top_right_button).setOnClickListener(compassListener)
        findViewById<Button>(R.id.center_button).setOnClickListener(compassListener)
        findViewById<Button>(R.id.bottom_left_button).setOnClickListener(compassListener)
        findViewById<Button>(R.id.bottom_right_button).setOnClickListener(compassListener)

        // ===== B2: RESTAURARE =====
        if (savedInstanceState != null) {

            compassClicks = savedInstanceState.getInt("compassClicks", 0)
            compassClicksText.text = compassClicks.toString()
            compassDirectionsText.text = savedInstanceState.getString("compassDirections", "")
        }
    }

    // ===== B2: SALVARE =====
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("compassClicks", compassClicks)
        outState.putString("compassDirections", compassDirectionsText.text.toString())
    }
}
