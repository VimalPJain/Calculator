package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener{

    //holds the Activity Main binding
    private lateinit var binding: ActivityMainBinding
    private var calculateWhich = true//Checks if its the first or second number
    private var calculateOperator = ""//Checks the operator type
    private var calculateBefore = 0//Save the first number
    private var calculateAfter = 0//Save the second number

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Creating the view of the app
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val calculationText: TextView = findViewById(R.id.Calculate)

        val button1: Button = findViewById(R.id.Number1)
        button1.setOnClickListener(this)
        val button2: Button = findViewById(R.id.Number2)
        button2.setOnClickListener(this)
        val button3: Button = findViewById(R.id.Number3)
        button3.setOnClickListener(this)
        val button4: Button = findViewById(R.id.Number4)
        button4.setOnClickListener(this)
        val button5: Button = findViewById(R.id.Number5)
        button5.setOnClickListener(this)
        val button6: Button = findViewById(R.id.Number6)
        button6.setOnClickListener(this)
        val button7: Button = findViewById(R.id.Number7)
        button7.setOnClickListener(this)
        val button8: Button = findViewById(R.id.Number8)
        button8.setOnClickListener(this)
        val button9: Button = findViewById(R.id.Number9)
        button9.setOnClickListener(this)
        val button0: Button = findViewById(R.id.NumberZero)
        button0.setOnClickListener(this)
        val buttonAddition: Button = findViewById(R.id.Addition)
        buttonAddition.setOnClickListener(this)
        val buttonMinus: Button = findViewById(R.id.Minus)
        buttonMinus.setOnClickListener(this)
        val buttonMultiply: Button = findViewById(R.id.Multiply)
        buttonMultiply.setOnClickListener(this)
        val buttonDivide: Button = findViewById(R.id.Divide)
        buttonDivide.setOnClickListener(this)
        val buttonClear: Button = findViewById(R.id.Clear)
        buttonClear.setOnClickListener(this)
        val buttonEqual: Button = findViewById(R.id.Equals)
        buttonEqual.setOnClickListener(this)
    }

    //This is where we will be using our code for amending the Text View
    override fun onClick(v:View) {
        val calculationText: TextView = findViewById(R.id.Calculate)//Get the calculate Text box
        val calculationTotal: TextView = findViewById(R.id.TotalView)
        val clickedButton: Button = findViewById(v.id)
        var total: Float = 0F

        if(v.id == R.id.Addition || v.id == R.id.Multiply || v.id == R.id.Minus ||v.id == R.id.Divide)//This will only display the operation on Text View
        {
            calculationText.text = clickedButton.text
            calculateOperator = calculationText.text.toString()
            calculateWhich = false
        }
        else if(v.id==R.id.Clear)//This will clear the Text box
        {
            calculationText.text = "0"
            calculateBefore = 0
            calculateAfter = 0
            calculateWhich = true
            calculationTotal.text = "0"
        }
        else if(v.id == R.id.Equals)
        {
            when (calculateOperator)
            {
                "+" -> total = calculateBefore.toFloat() + calculateAfter.toFloat()
                "-" -> total = calculateBefore.toFloat() - calculateAfter.toFloat()
                "/" -> total = calculateBefore.toFloat() / calculateAfter.toFloat()
                "x" -> total = calculateBefore.toFloat() * calculateAfter.toFloat()
            }
            calculationTotal.text = total.toString()
            calculateWhich = true
        }
        else {
            if (calculationText.text.toString() == "x" || calculationText.text.toString() == "-" || calculationText.text.toString() == "+" || calculationText.text.toString() == "/") {
                calculationText.text = ""
            }
            if(calculateWhich) {//checks if first number
                //This will append the number on the end of the text
                calculationText.append(clickedButton.text)
                calculateBefore = calculationText.text.toString().toInt()
            }

            else{
                calculationText.append(clickedButton.text)
                calculateAfter = calculationText.text.toString().toInt()
            }
        }
    }
}

