package com.aplikasi.readinglistrevisi.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import com.aplikasi.readinglist.data.Account
import com.aplikasi.readinglist.data.EXTRA_ACCOUNT_REGISTER
import com.aplikasi.readinglistrevisi.R
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.et_email

class RegisterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        tv_login.setOnClickListener {
            onBackPressed()
        }

        btn_register.setOnClickListener {
            registerUser()
        }
    }

    private fun validateRegisterDetails(): Boolean {
        return when {
            TextUtils.isEmpty(et_nama_depan.text.toString().trim() { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_first_name), true)
                false
            }
            TextUtils.isEmpty(et_nama_belakang.text.toString().trim() { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_last_name), true)
                false
            }
            TextUtils.isEmpty(et_email.text.toString().trim() { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }
            TextUtils.isEmpty(et_kata_sandi.text.toString().trim() { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password), true)
                false
            }
            et_kata_sandi.text.toString().trim(){ it <= ' '} != et_konfirmasi_kata_sandi.text.toString().trim()
            { it <= ' '}->{
                showErrorSnackBar(resources.getString(R.string.err_msg_password_and_confirm_password_mismatch),true)
                false
            }
            !cb_terms_and_condition.isChecked -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_agree_terms_and_condition), true)
                false
            }
            else -> {
                //showErrorSnackBar(resources.getString(R.string.registry_success),false)
                true
            }
        }
    }

    private fun registerUser(){
        if(validateRegisterDetails()){
            showProgressDialog(resources.getString(R.string.please_wait))
            hideProgressDialog()
            showErrorSnackBar(resources.getString(R.string.register_success),false)
            val i = Intent(this, LoginActivity::class.java)
            var akun = Account(et_nama_depan.text.toString(), et_nama_belakang.text.toString(),
                    et_email.text.toString(), et_kata_sandi.text.toString(),
                    et_konfirmasi_kata_sandi.text.toString())
                    i.putExtra(EXTRA_ACCOUNT_REGISTER, akun)
            startActivity(i)
            finish()
    }
}

}
