package com.udaychugh.bmicalculator

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Color.parseColor
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

abstract class bmiactivity : AppCompatActivity() {

    abstract var mrecalculatebmi:android.widget.Button

    lateinit var mbmidisplay : TextView
    lateinit var mbmicategory : TextView
    lateinit var mgender : TextView

    lateinit var intent1 : Intent
    lateinit var mimageview : ImageView
    lateinit var mbmi : String
    var intbmi : Float = 0.0f

    lateinit var height : String
    lateinit var weight : String
    var intheight : Float = 0.0f
    var intweight : Float = 0.0f

    lateinit var mbackground :RelativeLayout




    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmiactivity)
        supportActionBar?.hide()

        supportActionBar?.elevation = 0f
        supportActionBar?.title = Html.fromHtml("<font color=\"white\"></font>")
        supportActionBar?.title = "Result"
        val colorDrawable : ColorDrawable = ColorDrawable(Color.parseColor("#1E1D1D"))
        supportActionBar?.setBackgroundDrawable(colorDrawable)

        intent1 = intent

        mbmidisplay = findViewById(R.id.bmidisplay)
        mbmicategory = findViewById(R.id.category)
        mgender = findViewById(R.id.genderdisplay)
        mbackground = findViewById(R.id.contentlayout)
        mimageview = findViewById(R.id.imageview)
        mrecalculatebmi = findViewById(R.id.recalculatebmi)

        height = intent1.getStringExtra("height").toString()
        weight = intent1.getStringExtra("weight").toString()

        intheight = height.toFloat()
        intweight = weight.toFloat()

        intheight = intheight/100;

        intbmi = intweight/(intheight * intheight)

        mbmi = intbmi.toString()

        if (intbmi<16)
        {
            mbmicategory.text = "Severe Thinness"
            mbackground.setBackgroundColor(Color.RED)
            mimageview.setImageResource(R.drawable.crosss)
        }else if (intbmi<16.9 && intbmi>16)
        {
            mbmicategory.text = "Moderate Thinness"
            mbackground.setBackgroundColor(Color.RED)
            mimageview.setImageResource(R.drawable.warning)
        }else if (intbmi<18.4 && intbmi>17)
        {
            mbmicategory.text = "Mild Thinness"
            mbackground.setBackgroundColor(Color.RED)
            mimageview.setImageResource(R.drawable.warning)
        }else if (intbmi<25 && intbmi>18.4)
        {
            mbmicategory.text = "Normal"
            mbackground.setBackgroundColor(Color.YELLOW)
            mimageview.setImageResource(R.drawable.ok)
        }else if (intbmi<29.4 && intbmi>25)
        {
            mbmicategory.text = "Overweight"
            mbackground.setBackgroundColor(Color.RED)
            mimageview.setImageResource(R.drawable.warning)
        }else
        {
            mbmicategory.text = "Obese"
            mbackground.setBackgroundColor(Color.RED)
            mimageview.setImageResource(R.drawable.crosss)
        }

        mgender.text = intent1.getStringExtra("gender")
        mbmidisplay.text = mbmi
        mrecalculatebmi = findViewById(R.id.recalculatebmi)

        mrecalculatebmi.setOnClickListener(object: View.OnClickListener {

            override fun onClick(v:View) {
                val intent = Intent(this@bmiactivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        })

    }
}

