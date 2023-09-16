package com.patronus.common.navigation

import android.content.Context
import android.content.Intent
import android.os.Parcelable

interface Navigation<T: Arguments> {
    fun newIntent(context: Context, args: T): Intent
}

interface Arguments: Parcelable