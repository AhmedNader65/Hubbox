package com.hubbox.ui.home.paymentCards

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.google.mlkit.vision.text.Text
import com.hubbox.R
import com.hubbox.utils.CreditCardImageAnalyzer
import com.hubbox.utils.OnAnalyzerFinished
import kotlinx.android.synthetic.main.fragment_credit_scanner.*
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class CreditCardScannerFragment : Fragment(), OnAnalyzerFinished {
    private var imageCapture: ImageCapture? = null

    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService
    private var cardNum: String? = null
    private var cardHolder: String? = null
    private var expMonth: String? = null
    private var expYear: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = layoutInflater.inflate(com.hubbox.R.layout.fragment_credit_scanner, null)
// Request camera permissions


        // Set up the listener for take photo button
//        camera_capture_button.setOnClickListener { takePhoto() }

        outputDirectory = getOutputDirectory()

        cameraExecutor = Executors.newSingleThreadExecutor()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addManually.setOnClickListener{

            val navBuilder = NavOptions.Builder()
            navBuilder.setEnterAnim(R.anim.slide_in_left)
            view?.let {
                Navigation.findNavController(it)
                    .navigate(R.id.addCardFragment,null, navBuilder.build())
            }
        }

        if (allPermissionsGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(), REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
            )
        }

    }

    private fun startCamera() {

        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(cameraView.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder()
                .build()

            val imageAnalyzer = ImageAnalysis.Builder()
                .build()
                .also {
                    it.setAnalyzer(cameraExecutor, CreditCardImageAnalyzer(this))
                }
            // Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageAnalyzer
                )

            } catch (exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(requireContext()))

    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            requireContext(), it
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun getOutputDirectory(): File {
        val mediaDir = requireActivity().externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(com.hubbox.R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else requireActivity().filesDir
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    companion object {
        private const val TAG = "CameraXBasic"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults:
        IntArray
    ) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                Toast.makeText(
                    context,
                    "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT
                ).show()
                requireActivity().onBackPressed()
            }
        }
    }

    override fun onAnalyzerDone(x: List<Text.TextBlock>) {

        val lines = arrayListOf<String>()
        for (block in x) {
            for (line in block.lines) {
                lines.add(line.text)
            }
        }

        extractOwner(lines)?.let { cardHolder = it }
        extractNumber(lines)?.let { cardNum = it }
        extractExpiration(lines).let {
            expMonth = it.first
            expYear = it.second
        }
        if (cardNum != null && expMonth != null && expYear != null) {
            var bundle = Bundle()
            bundle.putString("cardNum", cardNum)
            bundle.putString("cardHolder", cardHolder)
            bundle.putString("exp", "$expMonth/$expYear")
            val navBuilder = NavOptions.Builder()
            navBuilder.setEnterAnim(R.anim.slide_in_left)
            view?.let {
                Navigation.findNavController(it)
                    .navigate(R.id.addCardFragment, bundle, navBuilder.build())
            }
        }

    }

    private fun extractOwner(lines: List<String>): String? {
        val all = lines
            .filter { it.contains(" ") }
            .filter { line -> line.asIterable().none { char -> char.isDigit() } }
        if (all.size > 0)
            return all.get(all.size - 1)
        return null
    }

    private fun extractNumber(lines: List<String>): String? {
        return lines.firstOrNull { line ->
            val subNumbers = line.split(" ")
            subNumbers.isNotEmpty() && subNumbers.size == 4 && subNumbers.flatMap { it.asIterable() }
                .all { it.isDigit() }
        }
    }

    private fun extractExpiration(lines: List<String>): Pair<String?, String?> {
        val expirationLine = extractExpirationLine(lines)

        val month = expirationLine?.substring(startIndex = 0, endIndex = 2)
        val year = expirationLine?.substring(startIndex = 3)
        return Pair(month, year)
    }


    private fun extractExpirationLine(lines: List<String>) =
        lines.flatMap { it.split(" ") }
            .firstOrNull { (it.length == 5 || it.length == 7) && it[2] == '/' }


}