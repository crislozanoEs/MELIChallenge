package com.crisav2.challengemeli.common

import androidx.fragment.app.Fragment
import com.crisav2.challengemeli.CoreApplication
import com.crisav2.challengemeli.di.BaseApplicationComponent

val Fragment.applicationComponent: BaseApplicationComponent
    get() =(requireActivity().application as CoreApplication).applicationComponent

