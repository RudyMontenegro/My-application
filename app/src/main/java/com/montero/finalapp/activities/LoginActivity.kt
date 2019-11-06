package com.montero.finalapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.montero.finalapp.R
import com.montero.finalapp.goToActivity
import com.montero.finalapp.toast
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance(); }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        /*if (mAuth.currentUser == null){
            toast("nope")
        }else{
            toast("Yep")
            mAuth.signOut()
        }*/

        buttonLogIn.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if(isValiEmailAndPassword(email, password)){
                logInByEmail(email, password)
            }else{
                toast("Please fill all the date is correct")

            }
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
                toast("user is now loged in")
            } else{
                toast("An unexpected error ocurred, please try again")
            }
        }
    }
    private fun isValiEmailAndPassword(email: String,password: String): Boolean {
        return !email.isNullOrEmpty() &&
                !password.isNullOrEmpty()


    }
}
