package com.schiavone.honorsmobileapps.userinterfacelab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    lateinit var tipSeekBar: SeekBar
    lateinit var button1: RadioButton
    lateinit var button2: RadioButton
    lateinit var button3: RadioButton
    lateinit var button4: RadioButton

    var subtotal = 100
    var tipPercent = 0
    var numOfGuests = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1 = findViewById<RadioButton>(R.id.button1)
        button2 = findViewById<RadioButton>(R.id.button2)
        button3 = findViewById<RadioButton>(R.id.button3)
        button4 = findViewById<RadioButton>(R.id.button4)
        setUpRadioButtons()
        setUpTipSeekBar()
        setUpNumOfGuestsSpinner()

    }

    fun setUpTipSeekBar() {
        tipSeekBar = findViewById(R.id.seekbar)
        tipSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar,
                newProgressValue: Int,
                fromUser: Boolean
            ) {

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                tipPercent=tipSeekBar.progress
                setRadioButtonAsChecked()
                setTipAndTotalTextViews()
            }
        })

    }

    fun setUpRadioButtons() {
        val buttonList: List<View> = listOf(button1, button2, button3, button4)
        for (button in buttonList) {
            button.setOnClickListener { view ->
                when (view.id) {
                    R.id.button1 -> tipPercent = 10
                    R.id.button2 -> tipPercent = 15
                    R.id.button3 -> tipPercent = 18
                    R.id.button4 -> tipPercent = 25
                }
                tipSeekBar.progress = tipPercent
                setTipAndTotalTextViews()
            }
        }
    }

    fun setTipAndTotalTextViews() {
        val tipAmount: TextView = findViewById(R.id.tip_amount)
        tipAmount.text = " $tipPercent%"
        val finalTotal: TextView = findViewById(R.id.total)
        finalTotal.text = " $${subtotal + (subtotal * tipPercent)/ 100}"

    }

    fun setRadioButtonAsChecked() {
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
        radioGroup.clearCheck()
        if (tipPercent == 10) {
            button1.isChecked = true
        }
        else if(tipPercent==15){
            button2.isChecked=true
        }
        else if(tipPercent==18){
            button3.isChecked=true
        }
        else if(tipPercent==25){
            button4.isChecked=true
        }
    }
    fun setUpNumOfGuestsSpinner(){
    val spinner : Spinner=findViewById(R.id.spinner)
        val numOfGuestsAdapter = ArrayAdapter.createFromResource(this,
            R.array.num_of_guests,
            android.R.layout.simple_spinner_item)
        numOfGuestsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = numOfGuestsAdapter
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {


            override fun onItemSelected(adapterView: AdapterView<*>, childView: View?, position: Int, itemId: Long) {
                numOfGuests = position+1
            }
            override fun onNothingSelected(adapterView: AdapterView<*>?) {  }
        }


    }
}