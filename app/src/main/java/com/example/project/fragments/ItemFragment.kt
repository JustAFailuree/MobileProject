package com.example.project.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.project.R
import com.example.project.data.Contact
import com.example.project.fragments.placeholder.PlaceholderContent

/**
 * A fragment representing a list of Items.
 */
class ItemFragment : Fragment() {

    private var columnCount = 1
    private var contactList: List<Contact> = listOf()


    private val myImages = listOf(
        R.drawable.ob1,
        R.drawable.ob2,
        R.drawable.ob3,
        R.drawable.ob4,
        R.drawable.ob5,
        R.drawable.ob6,
        R.drawable.ob7,
        R.drawable.ob8,
        R.drawable.ob9,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                contactList = createContactList()
                adapter = MyItemRecyclerViewAdapter(contactList)

                //val userName : EditText = findViewById(R.id.AddName)
                //val UserNumber : EditText = findViewById(R.id.AddNumber)
                //val AddButton : Button = findViewById(R.id.AddUserInfo)



                //AddButton.setOnClickListener {
                   // val name = userName.text.toString()
                   // val pnumber = UserNumber.text.toString()
                    //val newContact = Contact(name, pnumber, randomImage())
                   //addContact(newContact)
                //}

            }
        }
        return view
    }

    private fun createContactList(): List<Contact> = buildList<Contact> {
        for(i in 1..5) {
            val name = "Użytkownik ${i}"
            val number  = randomPhoneNumber()
            val image = randomImage()

            val newContact = Contact(name, number, image)
            add(newContact)
        }
    }

    private fun addContact(contact: Contact){
        val list = contactList.toMutableList()
        list.add(contact)
        contactList = list
    }

    private fun randomPhoneNumber(): String {
        var number = "+"
        number += randomNumber(2)
        number += " "
        number += randomNumber(3)
        number += " "
        number += randomNumber(3)
        number += " "
        number += randomNumber(3)

        return number

    }

    private fun randomNumber(n: Int): Any? {
        var number =""
        for(i in 1..n){
            number += "${(0..9).random()}"
        }
        return number
    }

    private fun randomImage(): Int {
        val i =(0..<9).random()

        return myImages[i]
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}