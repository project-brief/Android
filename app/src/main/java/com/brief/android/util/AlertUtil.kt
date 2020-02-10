package com.brief.android.util

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

/**
 * Created by JJH on 2020-02-08
 */
class AlertUtil {

    companion object {
        fun showMsgAlearDialog(
            context: Context, title: String? = "알림", msg: String, cancelable: Boolean? = false,
            dismissListener: DialogInterface.OnDismissListener? = null,
            positiveBtnLabel: String? = "예", okClickListener: DialogInterface.OnClickListener? = null,
            neutralBtnLabel: String? = "취소", neutralClickListener: DialogInterface.OnClickListener? = null,
            negativeBtnLabel: String? = "아니오", cancelClickListener: DialogInterface.OnClickListener? = null
        ) {

            val alearDialogBuilder = AlertDialog.Builder(context)
            with(alearDialogBuilder) {
                setTitle(title)
                setMessage(msg)
                if (cancelable != null)
                    setCancelable(cancelable)

                if (dismissListener != null)
                    setOnDismissListener(dismissListener)

                if (okClickListener != null)
                    setPositiveButton(positiveBtnLabel, okClickListener)

                if (neutralClickListener != null)
                    setNeutralButton(neutralBtnLabel, neutralClickListener)

                if (cancelClickListener != null)
                    setNegativeButton(negativeBtnLabel, cancelClickListener)
            }

            alearDialogBuilder.show()
        }
    }

}