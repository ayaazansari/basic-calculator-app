package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.ArithmeticException

class MainActivity : AppCompatActivity() {

    var lastNumeric =false
    var lastDot =false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onDigit(view:View)
    {
        textView.append((view as Button).text)
        lastNumeric=true
    }

    fun onClear(view: View)
    {
        textView.text=""
        lastNumeric=true
    }

    fun onDecimalPointer(view: View)
    {
        if(lastNumeric && !lastDot)
        {
            textView.append(".")
            lastNumeric=false
            lastDot=true
        }
    }

    private fun removeZeroAfterDot(result:String) :String{
        var value =result
        if(result.contains(".0"))
            value =result.substring(0,result.length-2)
        return value
    }

    fun onEqual(view: View)
    {
        if(lastNumeric){
            var tvValue =textView.text.toString()
            var prefix = ""
            try {
                if(tvValue.startsWith("-")){
                    prefix="-"
                    tvValue =tvValue.substring(1)
                }

                else if(tvValue.contains("-")){
                    val splitValue =tvValue.split("-")

                    var one=splitValue[0]
                    var two =splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix +one
                    }
                    textView.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                  }

                else if(tvValue.contains("+")){
                    val splitValue =tvValue.split("+")

                    var one=splitValue[0]
                    var two =splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix +one
                    }
                    textView.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                }

                else if(tvValue.contains("*")){
                    val splitValue =tvValue.split("*")

                    var one=splitValue[0]
                    var two =splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix +one
                    }
                    textView.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }

                else if(tvValue.contains("/")){
                    val splitValue =tvValue.split("/")

                    var one=splitValue[0]
                    var two =splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix +one
                    }

                    textView.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }
            }
            catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private  fun isOperatorAdded(value: String ):Boolean {
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/")||value.contains("*")||value.contains("+")
                    ||value.contains("-")
        }
    }
    fun onOperator(view: View)
    {
        if(lastNumeric && !isOperatorAdded(textView.text.toString()))
            textView.append((view as Button).text)
        lastNumeric =false
        lastDot=false
    }

}