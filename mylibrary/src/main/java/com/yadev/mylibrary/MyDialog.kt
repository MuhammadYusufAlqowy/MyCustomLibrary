package com.yadev.mylibrary

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.annotation.DrawableRes
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yadev.mylibrary.databinding.LayoutCustomDialogBinding

class MyDialog {
    class Build(val context: Context, title: String? = null, message: String, @DrawableRes image: Int) {
        private var dialog = MaterialAlertDialogBuilder(context).create()
        var layout = LayoutCustomDialogBinding.inflate(LayoutInflater.from(context))

        init {
            dialog.setView(layout.root)
//            dialog?.setCancelable(false)
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val param = dialog.window?.attributes
            param?.dimAmount = 0.5f
            dialog.window?.setAttributes(param)
            dialog.window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            layout.apply {
                if (title.isNullOrEmpty()) {
                    tvTitle.visibility = View.GONE
                }
                tvTitle.text = title
                tvMessage.text = message
                imgIcon.setImageResource(image)
            }
        }

        fun addOnNegativeButton(button: String, listener: View.OnClickListener): Build {
            layout.apply {
                btnNegative.text = button
                btnNegative.setOnClickListener {
                    if (dialog.isShowing){
                        dialog.dismiss()
                    }
                    listener.onClick(it)
                }
            }
            return this
        }

        fun addOnPositiveButton(button: String, listener: View.OnClickListener): Build {
            layout.apply {
                btnPositive.text = button
                btnPositive.setOnClickListener {
                    if (dialog.isShowing){
                        dialog.dismiss()
                    }
                    listener.onClick(it)
                }
            }
            return this
        }

        fun show():Build{
            dialog.show()
            return this
        }


    }
}