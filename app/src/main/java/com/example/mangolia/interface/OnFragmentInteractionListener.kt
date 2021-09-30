package com.example.mangolia.`interface`

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavDirections

interface OnFragmentInteractionListener {
    fun gotoFragment(@IdRes action: Int)
    fun gotoFragment(@IdRes action: Int, data: Bundle)
    fun gotoFragment(navDirections: NavDirections)
    fun goBack()
}