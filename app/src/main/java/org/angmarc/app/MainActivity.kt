package org.angmarc.app

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.angmarch.views.NiceSpinner
import org.angmarch.views.OnSpinnerItemSelectedListener
import org.angmarch.views.SimpleSpinnerTextFormatter
import org.angmarch.views.SpinnerTextFormatter
import java.util.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {
   private lateinit var niceSpinner: NiceSpinner;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupDefault()
        setupTintedWithCustomClass()
        setupXml()
    }

    override fun onDestroy() {
        super.onDestroy()
        val item = niceSpinner.selectedItem
        Log.d("jzh"," selectedItemPosition "+ niceSpinner.selectedItemPosition)
        Log.d("jzh",if(item ==null) "is null" else (item as Person).name + " "+ niceSpinner.selectedItemPosition)
    }

    private fun setupXml() {
        val spinner = findViewById<NiceSpinner>(R.id.niceSpinnerXml)
        spinner.setTextSize(TypedValue.COMPLEX_UNIT_SP,30.0f)
        spinner.onSpinnerItemSelectedListener = object : OnSpinnerItemSelectedListener {
            override fun onItemSelected(parent: NiceSpinner?, view: View?, position: Int, id: Long) {
                val item = parent!!.getItemAtPosition(position).toString()
                Toast.makeText(this@MainActivity, "Selected: $item", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupTintedWithCustomClass() {
      niceSpinner = findViewById<NiceSpinner>(R.id.tinted_nice_spinner)
        niceSpinner.setDefaultSelected(false)
        val people: MutableList<Person> = ArrayList()
        niceSpinner.attachDataSource(people)
//        people.add(Person("Tony", "Stark"))
//        people.add(Person("Steve", "Rogers"))
//        people.add(Person("Bruce", "Banner"))
        val textFormatter: SpinnerTextFormatter<*> = object : SimpleSpinnerTextFormatter() {
            fun format(person: Person): Spannable {
                return SpannableString(person.name + " " + person.surname)
            }
        }
        niceSpinner.setSpinnerTextFormatter(textFormatter)
        niceSpinner.setSelectedTextFormatter(textFormatter)
        niceSpinner.onItemClickListener = object : AdapterView.OnItemClickListener{
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                TODO("Not yet implemented")
            }

        }
        niceSpinner.notifyDataSetChanged(true)
        niceSpinner.attachDataSource(people)
        niceSpinner.setSelection(-1)
    }

    private fun setupDefault() {
        val spinner = findViewById<NiceSpinner>(R.id.nice_spinner)
        val dataset: List<String> = LinkedList(Arrays.asList("One", "Two", "Three", "Four", "Five"))
        spinner.attachDataSource(dataset)
        spinner.onSpinnerItemSelectedListener = object : OnSpinnerItemSelectedListener {
            override fun onItemSelected(parent: NiceSpinner?, view: View?, position: Int, id: Long) {
                val item = parent!!.getItemAtPosition(position).toString()
                Toast.makeText(this@MainActivity, "Selected: $item", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


internal class Person(val name: String, val surname: String) {
    override fun toString(): String {
        return "$name $surname"
    }
}