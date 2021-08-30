package com.hubbox.utils

import com.google.mlkit.vision.text.Text

interface OnAnalyzerFinished {
    fun onAnalyzerDone(x : List<Text.TextBlock>)
}