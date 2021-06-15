package com.aplikasi.readinglistrevisi.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.aplikasi.readinglist.data.Account
import com.aplikasi.readinglist.data.EXTRA_ACCOUNT_REGISTER
import com.aplikasi.readinglistrevisi.R
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val akun = intent.getParcelableExtra<Account>(EXTRA_ACCOUNT_REGISTER)
        et_email.setText(akun?.Email ?: "")
        et_password.setText(akun?.password ?: "")

        tv_register.setOnClickListener {
            val i = Intent(this, RegisterActivity::class.java)
            startActivity(i)
        }
        btn_login.setOnClickListener {loginUser()}
    }
    private fun validateLoginDetails(): Boolean {
        return when {
            TextUtils.isEmpty(et_email.text.toString().trim() { it <= ' '}) ->{
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email),true)
                false
            }
            TextUtils.isEmpty(et_password.text.toString().trim(){ it <= ' '})->{
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password),true)
                false
            }
            else -> {
                true
            }
        }
    }
    private fun loginUser(){
        if(validateLoginDetails()){
            showProgressDialog(resources.getString(R.string.please_wait))
            hideProgressDialog()
            val i = Intent(this,MainActivity::class.java)
            var akun = Account("","",et_email.text.toString(), et_password.text.toString(),"")
            i.putExtra(EXTRA_ACCOUNT_REGISTER, akun)
            startActivity(i)
        }
    }

}