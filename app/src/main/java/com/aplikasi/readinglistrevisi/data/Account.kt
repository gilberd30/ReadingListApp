package com.aplikasi.readinglist.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Account
( var namaDepan: String = "",
  var namaBelakang: String = "",
  var Email : String = "",
  var password: String = "",
  var passwordConfirm: String = "") : Parcelable {
}