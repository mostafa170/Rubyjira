package com.devartlab.rubyjira.app.utilsView

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.View
import android.view.Window
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.devartlab.rubyjira.R

class MyDialog(private val context: Context) {
    private val dialogLoading: Dialog
    private val dialogError: Dialog
    private val dialogSuccess: Dialog

    init {
        dialogLoading = getDialogInstance(R.layout._dialog_loading, Gravity.CENTER, false)
        dialogSuccess = getDialogInstance(R.layout._dialog_success, Gravity.CENTER, true)
        dialogError = getDialogInstance(R.layout._dialog_error, Gravity.CENTER, true)
    }

    fun showLoadingDialog(){
        dialogLoading.show()
    }
    fun hideLoadingDialog(){
        dialogLoading.dismiss()
    }

    fun showSuccessMessageDialog(message: String){
        dialogSuccess.findViewById<TextView>(R.id.tv_msg).text = message
        dialogSuccess.show()
    }

    fun showErrorMessageDialog(message: String){
        dialogError.findViewById<TextView>(R.id.tv_msg).text = message
        dialogError.show()
    }

    fun getDialogInstance(layout: Int, gravity: Int, isCancelable: Boolean) = Dialog(context).apply {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(isCancelable)
        setContentView(layout)
        window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
            setGravity(gravity)
        }
    }

    fun getDialogInstance(root: View, gravity: Int, isCancelable: Boolean): Dialog {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(isCancelable)
        dialog.setContentView(root)
        val window = dialog.window
        if (window != null) {
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window.setLayout(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
            window.setGravity(gravity)
        }
        return dialog
    }
}