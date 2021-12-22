package com.schiavone.honorsmobileapps.tipcalculator_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import com.schiavone.honorsmobileapps.tipcalculator_fragments.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding?=null
    private val binding get() = _binding!!

    var subtotal = 100
    var tipPercent = 0
    var numOfGuests = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentMainBinding.inflate(inflater,container,false)
        val rootview= binding.root
        setUpRadioButtons()
        setUpTipSeekBar()
        setUpNumOfGuestsSpinner()
        return rootview
    }
    override fun onDestroyView(){
        super.onDestroyView()
        _binding=null
    }

    fun setUpTipSeekBar() {
        binding.seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar,
                newProgressValue: Int,
                fromUser: Boolean
            ) {

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                tipPercent=binding.seekbar.progress
                setRadioButtonAsChecked()
                setTipAndTotalTextViews()
            }
        })

    }

    fun setUpRadioButtons() {
        val buttonList: List<View> = listOf(binding.button1, binding.button2, binding.button3, binding.button4)
        for (button in buttonList) {
            button.setOnClickListener { view ->
                when (view.id) {
                    R.id.button1 -> tipPercent = 10
                    R.id.button2 -> tipPercent = 15
                    R.id.button3 -> tipPercent = 18
                    R.id.button4 -> tipPercent = 25
                }
                binding.seekbar.progress = tipPercent
                setTipAndTotalTextViews()
            }
        }
    }

    fun setTipAndTotalTextViews() {
        binding.tipAmount.text = " $tipPercent%"
        binding.total.text = " $${subtotal + (subtotal * tipPercent)/ 100}"

    }

    fun setRadioButtonAsChecked() {
        binding.radioGroup.clearCheck()
        if (tipPercent == 10) {
            binding.button1.isChecked = true
        }
        else if(tipPercent==15){
            binding.button2.isChecked=true
        }
        else if(tipPercent==18){
            binding.button3.isChecked=true
        }
        else if(tipPercent==25){
            binding.button4.isChecked=true
        }
    }
    fun setUpNumOfGuestsSpinner(){
        val numOfGuestsAdapter = ArrayAdapter.createFromResource(requireActivity(),
            R.array.num_of_guests,
            android.R.layout.simple_spinner_item)
        numOfGuestsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = numOfGuestsAdapter
        binding.spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {


            override fun onItemSelected(adapterView: AdapterView<*>, childView: View?, position: Int, itemId: Long) {
                numOfGuests = position+1
            }
            override fun onNothingSelected(adapterView: AdapterView<*>?) {  }
        }


    }

}