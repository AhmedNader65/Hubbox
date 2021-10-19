package com.hubbox.ui.home.createShipment.pickupContact

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.hubbox.R
import com.hubbox.adapters.SpinnerAdapter
import com.hubbox.ui.home.createShipment.ShipmentMainVM
import kotlinx.android.synthetic.main.fragment_pickup_address.*
import kotlinx.android.synthetic.main.fragment_pickup_contact.*
import kotlinx.android.synthetic.main.fragment_pickup_contact.address
import kotlinx.android.synthetic.main.fragment_pickup_contact.confirm


class PickupContactFragment : Fragment() {
    private lateinit var vm: ShipmentMainVM
    private val REQUEST_CONTACTS_PERMISSIONS = 3124
    private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.READ_CONTACTS)
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = layoutInflater.inflate(R.layout.fragment_pickup_contact, null)
        vm =
            ViewModelProvider(requireParentFragment().requireParentFragment()).get(ShipmentMainVM::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupUI()

    }

    private fun setupUI() {
        val language: ArrayList<String> = ArrayList()
        language.add("العربية")
        language.add("English")
        sCountry.adapter =
            SpinnerAdapter(requireContext(), R.layout.item_country_dropdown, language)

        confirm.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_pickupContactFragment_to_dropoffFragment)
        }
        senderName.setEndIconOnClickListener {

            if (allPermissionsGranted()) {
                readContacts()
            } else {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    REQUIRED_PERMISSIONS,
                    REQUEST_CONTACTS_PERMISSIONS
                )
            }
        }
    }

    private fun setupObservers() {
        vm.pickupAddress.observe(viewLifecycleOwner, {
            address.text = it.address
            addressDetails.text = it.details
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults:
        IntArray
    ) {
        if (requestCode == REQUEST_CONTACTS_PERMISSIONS) {
            if (allPermissionsGranted()) {
                readContacts()
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

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            requireContext(), it
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun readContacts() {
        var pickContact = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        pickContact.setDataAndType(
            ContactsContract.Contacts.CONTENT_URI,
            ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
        )
        startActivityForResult(pickContact, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val contactData: Uri? = data?.data
        val c: Cursor? =
            contactData?.let { requireActivity().contentResolver.query(it, null, null, null, null) }
        if (c?.moveToFirst() == true) {
            val phoneIndex: Int =
                c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            val nameIndex: Int =
                c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            val num: String = c.getString(phoneIndex)
            val name: String = c.getString(nameIndex)
            senderName.editText?.setText(name)
            senderName.editText?.setText(name)
            phoneEntry.setText(num)
        }
    }

}