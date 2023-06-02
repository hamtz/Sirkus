package com.example.sirkus

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    private lateinit var btnSensorOn: Button
    private lateinit var btnSensorOff: Button
    private lateinit var btnFrekuensiOn: Button
    private lateinit var btnFrekuensiOff: Button
    private lateinit var database: FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nilaiSensor = findViewById<TextView>(R.id.tx_nilai_sensor)
        val nilaiFrekuensi = findViewById<TextView>(R.id.tx_nilai_frekuensi)
        val txDeteksi = findViewById<TextView>(R.id.tx_deteksi)

        // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        val motionRef = database.getReference("motion")
        val frekuensiRef = database.getReference("frekuensi")

        btnSensorOn = findViewById(R.id.btn_sensor_on)
        btnSensorOff = findViewById(R.id.btn_sensor_off)
        btnFrekuensiOn = findViewById(R.id.btn_frekuensi_on)
        btnFrekuensiOff = findViewById(R.id.btn_frekuensi_off)





        btnSensorOn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
//                Toast.makeText(this@MainActivity,"1",Toast.LENGTH_SHORT).show()
                setSensorOn()
            }
        })
        btnSensorOff.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                setSensorOff()
            }
        })
        btnFrekuensiOn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
//                Toast.makeText(this@MainActivity,"1",Toast.LENGTH_SHORT).show()
                setFrekuensiOn()
            }
        })
        btnFrekuensiOff.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                setFrekuensiOff()
            }
        })


        // Read from the database
        motionRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val curSensor = snapshot.getValue(String::class.java)
                if (curSensor == "1") {
                    nilaiSensor.text = "ON"
                    txDeteksi.text = "ADA GERAKAN"
                }else{
                    nilaiSensor.text="OFF"
                    txDeteksi.text = "TIDAK ADA GERAKAN"
                }
                Log.d(TAG, "Value is: " + curSensor)
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }

        })

        frekuensiRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val curFrekuensi = snapshot.getValue(String::class.java)
                if (curFrekuensi == "1") {
                    nilaiFrekuensi.text = "ON"
                }else{
                    nilaiFrekuensi.text="OFF"
                }
                Log.d(TAG, "Value is: " + curFrekuensi)
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }

        })
    }

    private fun setSensorOn() {
        val value = "1" // Nilai yang ingin disimpan
        val database = Firebase.database
        val myRef = database.getReference("motion")

        myRef.setValue(value)
            .addOnSuccessListener {
                Toast.makeText(this,"1",Toast.LENGTH_SHORT).show()
                // Penyimpanan berhasil
                // Tambahkan tindakan yang ingin dilakukan setelah penyimpanan berhasil
            }
            .addOnFailureListener {
                Toast.makeText(this,"gagal menyimpan",Toast.LENGTH_SHORT).show()
                // Penyimpanan gagal
                // Tambahkan tindakan yang ingin dilakukan jika penyimpanan gagal
            }
    }
    private fun setSensorOff() {
        val value = "0"
        val database = Firebase.database
        val myRef = database.getReference("motion")

        myRef.setValue(value)
            .addOnSuccessListener {
                Toast.makeText(this,"0",Toast.LENGTH_SHORT).show()
                // Penyimpanan berhasil
                // Tambahkan tindakan yang ingin dilakukan setelah penyimpanan berhasil
            }
            .addOnFailureListener {
                Toast.makeText(this,"gagal menyimpan",Toast.LENGTH_SHORT).show()
                // Penyimpanan gagal
                // Tambahkan tindakan yang ingin dilakukan jika penyimpanan gagal
            }

    }

    private fun setFrekuensiOn() {
        val value = "1" // Nilai yang ingin disimpan
        val database = Firebase.database
        val myRef = database.getReference("frekuensi")

        myRef.setValue(value)
            .addOnSuccessListener {
                Toast.makeText(this,"1",Toast.LENGTH_SHORT).show()
                // Penyimpanan berhasil
                // Tambahkan tindakan yang ingin dilakukan setelah penyimpanan berhasil
            }
            .addOnFailureListener {
                Toast.makeText(this,"gagal menyimpan",Toast.LENGTH_SHORT).show()
                // Penyimpanan gagal
                // Tambahkan tindakan yang ingin dilakukan jika penyimpanan gagal
            }
    }
    private fun setFrekuensiOff() {
        val value = "0" // Nilai yang ingin disimpan
        val database = Firebase.database
        val myRef = database.getReference("frekuensi")

        myRef.setValue(value)
            .addOnSuccessListener {
                Toast.makeText(this,"1",Toast.LENGTH_SHORT).show()
                // Penyimpanan berhasil
                // Tambahkan tindakan yang ingin dilakukan setelah penyimpanan berhasil
            }
            .addOnFailureListener {
                Toast.makeText(this,"gagal menyimpan",Toast.LENGTH_SHORT).show()
                // Penyimpanan gagal
                // Tambahkan tindakan yang ingin dilakukan jika penyimpanan gagal
            }
    }
}
