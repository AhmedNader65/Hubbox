package com.hubbox.utils

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputConnection
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import com.hubbox.R


class AppKeyboard(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
    LinearLayout(context, attrs, defStyleAttr), View.OnClickListener {
    // constructors
    constructor(context: Context?) : this(context, null, 0) {}
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0) {}

    // keyboard keys (buttons)
    private var mButton1: Button? = null
    private var mButton2: Button? = null
    private var mButton3: Button? = null
    private var mButton4: Button? = null
    private var mButton5: Button? = null
    private var mButton6: Button? = null
    private var mButton7: Button? = null
    private var mButton8: Button? = null
    private var mButton9: Button? = null
    private var mButton0: Button? = null
    private var mButtonHash: ImageButton? = null
    private var mButtonStar: Button? = null

    // This will map the button resource id to the String value that we want to
    // input when that button is clicked.
    var keyValues = SparseArray<String>()

    // Our communication link to the EditText
    private var inputConnection: InputConnection? = null
    private fun init(context: Context?, attrs: AttributeSet?) {

        // initialize buttons
        LayoutInflater.from(context).inflate(R.layout.app_keyboard, this, true)
        mButton1 = findViewById<View>(R.id.btn1) as Button
        mButton2 = findViewById<View>(R.id.btn2) as Button
        mButton3 = findViewById<View>(R.id.btn3) as Button
        mButton4 = findViewById<View>(R.id.btn4) as Button
        mButton5 = findViewById<View>(R.id.btn5) as Button
        mButton6 = findViewById<View>(R.id.btn6) as Button
        mButton7 = findViewById<View>(R.id.btn7) as Button
        mButton8 = findViewById<View>(R.id.btn8) as Button
        mButton9 = findViewById<View>(R.id.btn9) as Button
        mButton0 = findViewById<View>(R.id.btn0) as Button
        mButtonHash = findViewById<View>(R.id.delete) as ImageButton


        // set button click listeners
        mButton1?.setOnClickListener(this)
        mButton2?.setOnClickListener(this)
        mButton3?.setOnClickListener(this)
        mButton4?.setOnClickListener(this)
        mButton5?.setOnClickListener(this)
        mButton6?.setOnClickListener(this)
        mButton7?.setOnClickListener(this)
        mButton8?.setOnClickListener(this)
        mButton9?.setOnClickListener(this)
        mButton0?.setOnClickListener(this)
        mButtonHash?.setOnClickListener(this)

        // map buttons IDs to input strings
        keyValues.put(R.id.btn1, "1")
        keyValues.put(R.id.btn2, "2")
        keyValues.put(R.id.btn3, "3")
        keyValues.put(R.id.btn4, "4")
        keyValues.put(R.id.btn5, "5")
        keyValues.put(R.id.btn6, "6")
        keyValues.put(R.id.btn7, "7")
        keyValues.put(R.id.btn8, "8")
        keyValues.put(R.id.btn9, "9")
        keyValues.put(R.id.btn0, "0")
        keyValues.put(R.id.delete, "#")

    }

    override fun onClick(v: View) {

        // do nothing if the InputConnection has not been set yet
        if (inputConnection == null) return

        // Delete text or input key value
        // All communication goes through the InputConnection
        if (v.id == R.id.delete) {
            val selectedText = inputConnection!!.getSelectedText(0)
            if (TextUtils.isEmpty(selectedText)) {
                // no selection, so delete previous character
                inputConnection!!.deleteSurroundingText(1, 0)
            } else {
                // delete the selection
                inputConnection!!.commitText("", 1)
            }
        } else {
            val value = keyValues[v.id]
            inputConnection!!.commitText(value, 1)
        }
    }

    // The activity (or some parent or controller) must give us
    // a reference to the current EditText's InputConnection
    fun setInputConnection(ic: InputConnection?) {
        inputConnection = ic
    }

    init {
        init(context, attrs)
    }
}