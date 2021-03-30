package com.example.challengepapb

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.ImageView
import com.example.challengepapb.databinding.ActivityMainBinding
import com.google.android.material.switchmaterial.SwitchMaterial

class MainActivity : AppCompatActivity(), View.OnClickListener,
    CompoundButton.OnCheckedChangeListener {
    companion object {
        const val keyBundle = "bundle"
        const val keyHead = "head"
        const val keyHair = "hair"
        const val keyMoustache = "moustache"
        const val keyEyes = "eyes"
        const val keyEybrow = "eyebrow"
        const val keyBread = "Bread"

        const val keyUriHead = "UriHead"
        const val keyUriHair = "UriHair"
        const val keyUriMoustach = "UriMoustach"
        const val keyUriEyes = "UriEyes"
        const val keyUriEyebrow = "UriEyebrow"
        const val keyUriBread = "UriBread"

    }
    var UriHead: Uri? =null
    var UriHair: Uri? =null
    var UriMoustach: Uri? =null
    var UriEyes: Uri? =null
    var UriEyebrow: Uri? =null
    var UriBread: Uri? =null

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHead.setOnClickListener(this)
        binding.btnHair.setOnClickListener(this)
        binding.btnMoustache.setOnClickListener(this)
        binding.btnEyes.setOnClickListener(this)
        binding.btnEyebrow.setOnClickListener(this)
        binding.btnBread.setOnClickListener(this)

        binding.idHead.setOnCheckedChangeListener(this)
        binding.idHead.setOnCheckedChangeListener(this)
        binding.idHair.setOnCheckedChangeListener(this)
        binding.idMoustache.setOnCheckedChangeListener(this)
        binding.idEyes.setOnCheckedChangeListener(this)
        binding.idEyebrow.setOnCheckedChangeListener(this)
        binding.idBread.setOnCheckedChangeListener(this)
    }

    fun pickImage(kode: Int) {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, kode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            val takenImage = data.data
            Log.d("DataImage", takenImage.toString())
            binding.idHead.isChecked = true
            UriHead = takenImage
            binding.head.setImageURI(takenImage)
        } else if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            val takenImage = data.data
            Log.d("DataImage", takenImage.toString())
            binding.idHair.isChecked = true
            UriHair = takenImage
            binding.hair.setImageURI(takenImage)
        } else if (requestCode == 3 && resultCode == Activity.RESULT_OK && data != null) {
            val takenImage = data.data
            Log.d("DataImage", takenImage.toString())
            binding.idMoustache.isChecked = true
            UriMoustach = takenImage
            binding.moustache.setImageURI(takenImage)
        } else if (requestCode == 4 && resultCode == Activity.RESULT_OK && data != null) {
            val takenImage = data.data
            Log.d("DataImage", takenImage.toString())
            binding.idEyes.isChecked = true
            UriEyes = takenImage
            binding.eyes.setImageURI(takenImage)
        } else if (requestCode == 5 && resultCode == Activity.RESULT_OK && data != null) {
            val takenImage = data.data
            Log.d("DataImage", takenImage.toString())
            binding.idEyebrow.isChecked = true
            UriEyebrow = takenImage
            binding.eyebrow.setImageURI(takenImage)
        } else if (requestCode == 6 && resultCode == Activity.RESULT_OK && data != null) {
            val takenImage = data.data
            Log.d("DataImage", takenImage.toString())
            binding.idBread.isChecked = true
            UriBread = takenImage
            binding.bread.setImageURI(takenImage)
        }
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnHead -> pickImage(1)
            R.id.btnHair -> pickImage(2)
            R.id.btnMoustache -> pickImage(3)
            R.id.btnEyes -> pickImage(4)
            R.id.btnEyebrow -> pickImage(5)
            R.id.btnBread -> pickImage(6)
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        when (buttonView?.id) {
            R.id.idHead -> visible(binding.idHead, binding.head)
            R.id.idHair -> visible(binding.idHair, binding.hair)
            R.id.idMoustache -> visible(binding.idMoustache, binding.moustache)
            R.id.idEyes -> visible(binding.idEyes, binding.eyes)
            R.id.idEyebrow -> visible(binding.idEyebrow, binding.eyebrow)
            R.id.idBread -> visible(binding.idBread, binding.bread)

        }
    }

    private fun visible(idCheckbox: SwitchMaterial, image: ImageView) {
        if (idCheckbox.isChecked) {
            image.visibility = View.VISIBLE
        } else {
            image.visibility = View.GONE
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val bundle = Bundle()
        bundle.putBoolean(keyHead, binding.idHead.isChecked)
        bundle.putBoolean(keyHair, binding.idHair.isChecked)
        bundle.putBoolean(keyMoustache, binding.idMoustache.isChecked)
        bundle.putBoolean(keyEyes, binding.idEyes.isChecked)
        bundle.putBoolean(keyEybrow, binding.idEyebrow.isChecked)
        bundle.putBoolean(keyBread, binding.idBread.isChecked)

        bundle.putString(keyUriHead,UriHead.toString())
        bundle.putString(keyUriHair,UriHair.toString())
        bundle.putString(keyUriMoustach,UriMoustach.toString())
        bundle.putString(keyUriEyes,UriEyes.toString())
        bundle.putString(keyUriEyebrow,UriEyebrow.toString())
        bundle.putString(keyUriBread,UriBread.toString())
        outState.putBundle(keyBundle, bundle)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val bundle = savedInstanceState.getBundle(keyBundle)
        if (!bundle!!.getBoolean(keyHead)) {
            binding.head.visibility = View.GONE
            UriHead = Uri.parse(bundle?.getString(keyUriHead))
            binding.head.setImageURI((UriHead))
        }
        else{
            UriHead = Uri.parse(bundle?.getString(keyUriHead))
            binding.head.setImageURI((UriHead))

        }

        if (!bundle.getBoolean(keyHair)) {
             binding.hair.visibility = View.GONE
             UriHair = Uri.parse(bundle?.getString(keyUriHair))
             binding.hair.setImageURI((UriHair))
         } else{
            UriHair = Uri.parse(bundle?.getString(keyUriHair))
             binding.hair.setImageURI((UriHair))
         }

         if (!bundle.getBoolean(keyMoustache)) {
             binding.moustache.visibility = View.GONE
             UriMoustach = Uri.parse(bundle?.getString(keyUriMoustach))
             binding.moustache.setImageURI((UriMoustach))
         } else{
             UriMoustach = Uri.parse(bundle?.getString(keyUriMoustach))
             binding.moustache.setImageURI((UriMoustach))
         }
         if (!bundle.getBoolean(keyEyes)) {
             binding.eyes.visibility = View.GONE
             UriEyes = Uri.parse(bundle?.getString(keyUriEyes))
             binding.eyes.setImageURI((UriEyes))
         } else{
             UriEyes = Uri.parse(bundle?.getString(keyUriEyes))
             binding.eyes.setImageURI((UriEyes))
         }
         if (!bundle.getBoolean(keyEybrow)) {
             binding.eyebrow.visibility = View.GONE
             UriEyebrow = Uri.parse(bundle?.getString(keyUriEyebrow))
             binding.eyebrow.setImageURI((UriEyebrow))
         } else{
             UriEyebrow = Uri.parse(bundle?.getString(keyUriEyebrow))
             binding.eyebrow.setImageURI((UriEyebrow))
         }
         if (!bundle.getBoolean(keyBread)) {
             binding.bread.visibility = View.GONE
             UriBread = Uri.parse(bundle?.getString(keyUriBread))
             binding.bread.setImageURI((UriBread))
         } else{
             UriBread = Uri.parse(bundle?.getString(keyUriBread))
             binding.bread.setImageURI((UriBread))
         }
    }

}