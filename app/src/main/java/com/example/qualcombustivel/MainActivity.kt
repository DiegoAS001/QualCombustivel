package com.example.qualcombustivel

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), OnClickListener {

    private  lateinit var gasolineEditText: EditText
    private  lateinit var ethanolEditText: EditText
    private  lateinit var mButton: Button
    private  lateinit var mTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findByID()
        setClick()

    }

    override fun onClick(v: View?) {
       if (v == mButton){
           calculate()
       }
    }

    private fun calculate(){
        if (gasolineEditText.text.toString().isEmpty() || ethanolEditText.text.toString().isEmpty()){
            Toast.makeText(this, getString(R.string.ToastInfomeOValor), Toast.LENGTH_SHORT).show();
            mTextView.text = ""
        }else{
            val gas = retriveValue(gasolineEditText);
            val etha = retriveValue(ethanolEditText);

            var result = etha / gas
            if (result < 0.7){
                mTextView.text = getString(R.string.answer_ethanol)
                mTextView.setTextColor(resources.getColor(R.color.ethanol, this.theme))
            }else{
                mTextView.text = getString(R.string.answer_gasoline)
                mTextView.setTextColor(resources.getColor(R.color.gasoline, this.theme))
            }
        }

    }

    private fun retriveValue(input: EditText) : Double {
        return try {
            input.text.toString().toDouble()
        }catch (e : NumberFormatException){
            Toast.makeText(this,getString(R.string.ToastValorInvalido), Toast.LENGTH_SHORT).show()
            0.0
        }
    }

    private fun findByID(){
        gasolineEditText = findViewById(R.id.edittext_gasoline)
        ethanolEditText = findViewById(R.id.edittext_ethanol)
        mButton = findViewById(R.id.button_calculate)
        mTextView = findViewById(R.id.textviwe_response)
    }

    private  fun setClick(){
        mButton.setOnClickListener(this)
    }
}