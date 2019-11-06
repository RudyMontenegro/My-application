package com.montero.finalapp

import android.app.Activity
import android.content.Intent
import android.content.LocusId
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import java.util.regex.Pattern

fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this,message, duration).show()
fun Activity.toast(resourceId: Int, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this,resourceId, duration).show()
fun ViewGroup.inflate(layout: Int) = LayoutInflater.from(context).inflate(layout,this,false)!!

inline fun <reified T : Activity> Activity.goToActivity(noinline init: Intent.() -> Unit={}){
    val intent = Intent(this, T::class.java)
    intent.init()
    startActivity(intent)
}
fun EditText.validate(validation: (String) -> Unit){
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable) {
                validation.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
}
fun Activity.isValidEmail(email: String): Boolean{
    val pattern = Patterns.EMAIL_ADDRESS
    return pattern.matcher(email).matches()
}
fun Activity.isValidPassword(password: String): Boolean{
    val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{4,}$"
    val pattern = Pattern.compile(passwordPattern)
    return pattern.matcher(password).matches()
}
fun Activity.isValidConfirmPassword(password: String, confirmPassword: String): Boolean{
    return true
}