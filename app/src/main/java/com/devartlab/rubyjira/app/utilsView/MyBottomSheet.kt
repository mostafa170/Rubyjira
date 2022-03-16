package com.aldamina.aldaminadelivery.app.utilsView

import android.content.Context
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialog

class MyBottomSheet (private val context: Context){

    fun getDialogInstance(root: View): BottomSheetDialog {
        val bottomSheetDialog = BottomSheetDialog(context)
        bottomSheetDialog.setContentView(root)

        return bottomSheetDialog
    }
}