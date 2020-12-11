package com.ilham.uasapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilham.uasapplication.adapter.UserAdapter
import com.ilham.uasapplication.model.realm.User
import io.realm.Realm
import io.realm.exceptions.RealmException
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    lateinit var userAdapter: UserAdapter
    val lm = LinearLayoutManager(activity)
    lateinit var realm : Realm
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        action()
    }
    fun initView(){
        rv_user.layoutManager = lm
        userAdapter = UserAdapter(activity!!)
        rv_user.adapter = userAdapter


        realm = Realm.getDefaultInstance()
        getAllUSer()

    }

    fun action(){
        btn_add.setOnClickListener{
            realm.beginTransaction()
            var count = 0

            realm.where(User::class.java).findAll().let {
                for (i in it){
                    count++
                }
            }
            try {
                var user = realm.createObject(User::class.java)
                user.setId(count+1)
                user.setNama(et_nama.text.toString())
                user.setEmail(et_email.text.toString())
                getAllUSer()
                //tv_result.text = user.getId().toString()+" "+ user.getNama()+ " "+ user.getEmail()
                et_nama.setText("")
                et_email.setText("")
                realm.commitTransaction()
            } catch (e : RealmException){
                Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getAllUSer(){
        realm.where(User::class.java).findAll().let {
            userAdapter.setUser(it)
        }
    }

}