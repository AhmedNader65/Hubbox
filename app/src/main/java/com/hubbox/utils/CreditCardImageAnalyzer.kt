package com.hubbox.utils

import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import java.lang.Double.parseDouble

class CreditCardImageAnalyzer(val mListener:OnAnalyzerFinished) : ImageAnalysis.Analyzer {
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy?.image
        if (mediaImage != null) {
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
            // Pass image to an ML Kit Vision API
            // ...
            val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
            val result = recognizer.process(image)
                .addOnSuccessListener { visionText ->
                    // Task completed successfully
                    // ...
                     val recognisedText =  StringBuilder("");

                    Log.e("blockText", "completed")
                    for (block in visionText.textBlocks) {
                        recognisedText.append(block.getText() +"\n");
                        val blockText = block.text
                        val blockFrame = block.boundingBox

                        for (line in block.lines) {
                            val lineText = line.text
                            //once you get the text elements, you can easily extract the text, email address, name mentioned
                            //on the visiting card or any other image containing text.
                            //Apply your logic accordingly.
                            //One sample is shown below for extracting the number of the form:- 011-22246388
                            for (element in line.elements) {
                                var elementText = element.text

                                if (elementText.contains('-')) {
                                    val split = elementText.split('-')
                                    var part1 = ""
                                    var part2 = ""
                                    split.let {
                                        part1 = it[0]
                                        part2 = it[1]
                                    }
                                    elementText = part1 + part2
                                }

                                if (!elementText.isEmpty()) {
                                    var numeric = true
                                    var num: Double = parseDouble("0")
                                    try {
                                        num = parseDouble(elementText)
                                    } catch (e: NumberFormatException) {
                                        numeric = false
                                    }
                                    if (numeric)
                                        print("Phone number detected is $num")
                                    else
                                        print(" Phone number is not detected on the card.")
                                }
                            }
                        }
                    }
                    mListener.onAnalyzerDone(visionText.textBlocks)
                }
                .addOnFailureListener { e ->
                    e.printStackTrace()
                    // Task failed with an exception
                    // ...
                }
                .addOnCompleteListener {

                    imageProxy.close()
                }
        }
    }
}