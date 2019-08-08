package io.horizontalsystems.bankwallet.modules.send.sendviews.confirmation.subviews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import io.horizontalsystems.bankwallet.R
import io.horizontalsystems.bankwallet.modules.send.sendviews.confirmation.SendConfirmationModule
import kotlinx.android.synthetic.main.view_button_with_progressbar.view.*

class ConfirmationSendButtonView : ConstraintLayout {

    init {
        inflate(context, R.layout.view_button_with_progressbar, this)
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun bind(state: SendConfirmationModule.SendButtonState) {
        when(state) {
            SendConfirmationModule.SendButtonState.ACTIVE -> {
                buttonText.text = context.getString(R.string.Send_Confirmation_Send_Button)
                progressBar.visibility = View.GONE
                buttonText.isEnabled = true
                buttonWrapper.isEnabled = true
            }
            SendConfirmationModule.SendButtonState.SENDING -> {
                buttonText.text = context.getString(R.string.Send_Sending)
                progressBar.visibility = View.VISIBLE
                buttonText.isEnabled = false
                buttonWrapper.isEnabled = false
            }
        }
        invalidate()
    }

}