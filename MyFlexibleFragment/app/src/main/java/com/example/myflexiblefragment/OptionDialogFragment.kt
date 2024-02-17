package com.example.myflexiblefragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment

class OptionDialogFragment : DialogFragment() {
    private lateinit var btnChoose: Button
    private lateinit var btnClose: Button
    private lateinit var valOptions: RadioGroup
    private lateinit var valSaf: RadioButton
    private lateinit var valMou: RadioButton
    private lateinit var valLvg: RadioButton
    private lateinit var valMoyes: RadioButton
    private var optionDialogListener: OnOptionDialogListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnChoose = view.findViewById(R.id.btn_choose)
        btnClose = view.findViewById(R.id.btn_close)
        valOptions = view.findViewById(R.id.val_group)
        valSaf = view.findViewById(R.id.val_saf)
        valLvg = view.findViewById(R.id.val_lvg)
        valMou = view.findViewById(R.id.val_mou)
        valMoyes = view.findViewById(R.id.val_moyes)

        btnChoose.setOnClickListener {
            val checkedRadioButtonId = valOptions.checkedRadioButtonId
            if (checkedRadioButtonId != -1) {
                var coach: String? = when (checkedRadioButtonId) {
                    R.id.val_saf -> valSaf.text.toString().trim()
                    R.id.val_mou -> valMou.text.toString().trim()
                    R.id.val_lvg -> valLvg.text.toString().trim()
                    R.id.val_moyes -> valMoyes.text.toString().trim()
                    else -> null
                }
                optionDialogListener?.onOptionChosen(coach)
                dialog?.dismiss()
            }
        }
        btnClose.setOnClickListener {
            dialog?.cancel()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val fragment = parentFragment
        if(fragment is DetailCategoryFragment) {
            this.optionDialogListener = fragment.optionDialogListener
        }
    }

    override fun onDetach() {
        super.onDetach()

        this.optionDialogListener = null
    }

    interface OnOptionDialogListener {
        fun onOptionChosen(text: String?)
    }
}