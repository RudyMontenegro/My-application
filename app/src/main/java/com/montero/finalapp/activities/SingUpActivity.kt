package com.montero.finalapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Toast

import com.google.firebase.auth.FirebaseAuth
import com.montero.finalapp.*
import kotlinx.android.synthetic.main.activity_sing_up.*
import kotlinx.android.synthetic.main.activity_sing_up.buttonSignUp
import java.util.regex.Pattern

class SingUpActivity : AppCompatActivity() {
    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance(); }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)
        buttonSignUp.setOnClickListener {
            //    Toast.makeText(this, "Please fill all the date and confirm password is correct", Toast.LENGTH_SHORT).show()
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            if (isValiEmailAndPassword(email, password)) {
                signUpByEmail(email, password)
            } else {
                Toast.makeText(
                    this,
                    "Please fill all the date and confirm password is correct",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        /*editTextEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (isValidEmail(editTextEmail.text.toString())){
                    editTextEmail.error = "The email is not valid"
                }else{
                    editTextEmail.error = ""
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })*/
        buttonGoBack.setOnClickListener {
            goToActivity<LoginActivity> {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            //overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
        }
        editTextEmail.validate {
            editTextEmail.error = if (isValidEmail(it)) null else "Email is not valid"
        }
        editTextPassword.validate {
            editTextPassword.error =
                if (isValidPassword(it)) null else "Password should contain 1 lowercase, 1 number and 4 characters length"
        }
        editTextConfirmPassword.validate {
            editTextConfirmPassword.error = if (isValidConfirmPassword(editTextPassword.text.toString(),it)) null else "Confirm password is not some"
        }
    }
    private fun signUpByEmail(email: String, password: String){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this)
        {task ->
            if (task.isSuccessful){
                toast("Confirm your email before sign in.")
            } else{
                toast("An unexpected error ocurred, please try again.")
            }
        }
    }
    private fun isValiEmailAndPassword(email: String,password: String): Boolean {
        return !email.isNullOrEmpty() &&
                !password.isNullOrEmpty() &&
                password == editTextPassword.text.toString()
    }

}