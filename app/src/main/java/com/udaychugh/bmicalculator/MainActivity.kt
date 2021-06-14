package com.udaychugh.bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

abstract class MainActivity : AppCompatActivity() {

    abstract var mcalculatebmi:android.widget.Button

    lateinit var mcurrentHeight : TextView
    lateinit var mcurrentage : TextView
    lateinit var mcurrentweight : TextView
    lateinit var mincreamentage : ImageView
    lateinit var mincreamentweight : ImageView
    lateinit var mdcreamentweight : ImageView
    lateinit var mdcreamentage : ImageView
    lateinit var mseekbarforheight : SeekBar
    lateinit var mmale : RelativeLayout
    lateinit var mfemale : RelativeLayout

    var intweight : Int = 55
    var intage : Int = 22
    var currentprogress : Int = 0
    var mintprogress : String = "170"
    var typeofuser : String = "0"
    var weight2 : String = "55"
    var age2 : String = "22"





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        mcalculatebmi = findViewById(R.id.calculatebmi)
        mcurrentage = findViewById(R.id.currentAge)
        mcurrentweight = findViewById(R.id.currentweight)
        mcurrentHeight = findViewById(R.id.currentheight)
        mincreamentage = findViewById(R.id.increamentage)
        mdcreamentage = findViewById(R.id.decrementage)
        mincreamentweight = findViewById(R.id.increamentweight)
        mdcreamentweight = findViewById(R.id.decrementweight)
        mseekbarforheight = findViewById(R.id.seekbarforheight)
        mmale = findViewById(R.id.male)
        mfemale = findViewById(R.id.female)

        mmale.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v:View) {
                mmale.background = ContextCompat.getDrawable(applicationContext, R.drawable.malefemalefocus)
                mfemale.background = ContextCompat.getDrawable(applicationContext, R.drawable.malefemalenotfocus)
                typeofuser = "Male"
            }
        })

        mfemale.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v:View) {
                mfemale.background = ContextCompat.getDrawable(applicationContext, R.drawable.malefemalefocus)
                mmale.background = ContextCompat.getDrawable(applicationContext, R.drawable.malefemalenotfocus)
                typeofuser = "Female"
            }
        })

        mseekbarforheight.max = 300
        mseekbarforheight.progress = 170
        mseekbarforheight.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                currentprogress = progress
                mintprogress = currentprogress.toString()
                mcurrentHeight.text = mintprogress

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                TODO("Not yet implemented")
            }

        })

        mincreamentage.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                intage = intage + 1
                age2 = intage.toString()
                mcurrentage.text = age2
            }
        })

        mdcreamentage.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                intage = intage - 1
                age2 = intage.toString()
                mcurrentage.text = age2
            }
        })

        mincreamentweight.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                intweight = intweight + 1
                weight2 = intweight.toString()
                mcurrentweight.text = weight2
            }
        })

        mdcreamentweight.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                intweight = intweight - 1
                weight2 = intweight.toString()
                mcurrentweight.text = weight2
            }
        })

        mcalculatebmi.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v:View) {

                if (typeofuser.equals("0"))
                {
                    Toast.makeText(applicationContext, "Select your gender first", Toast.LENGTH_SHORT).show()
                }
                else if (mintprogress.equals("0"))
                {
                    Toast.makeText(applicationContext, "Select your height first", Toast.LENGTH_SHORT).show()
                }
                else if (intage == 0 || intage<0)
                {
                    Toast.makeText(applicationContext, "Incorrect Age", Toast.LENGTH_SHORT).show()
                }
                else if (intweight == 0 || intweight<0)
                {
                    Toast.makeText(applicationContext, "Incorrect Weight", Toast.LENGTH_SHORT).show()
                }
                else{
                    val intent = Intent(this@MainActivity, bmiactivity::class.java)
                    intent.putExtra("gender", typeofuser)
                    intent.putExtra("height", mintprogress)
                    intent.putExtra("weight", weight2)
                    intent.putExtra("age", age2)

                    startActivity(intent)
                }




            }
        })
    }
}