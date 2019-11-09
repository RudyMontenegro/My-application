package com.montero.finalapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.montero.finalapp.*
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance(); }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonLogIn.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if((isValidEmail(email) && isValidPassword(password))){
                logInByEmail(email, password)
            }else{
                toast("Please fill all the date is correct")

            }
        }
        editTextEmail.validate {
            editTextEmail.error = if (isValidEmail(it)) null else "Email is not valid"
        }

        textViewForgotPassword.setOnClickListener {goToActivity<ForgotPasswordActivity>()
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)}
        buttonCreateAccount.setOnClickListener {goToActivity<SingUpActivity>()
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }
    }
    private fun logInByEmail(email: String, password: String){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this)
        {task ->
            if (task.isSuccessful){
                if(mAuth.currentUser!!.isEmailVerified){
                    toast("User logged in")
                }else {
                    toast("Confirm email first")
                }
            } else{
                toast("An unexpected error ocurred, please try again")
            }
        }
    }
}
