package com.example.sewingandclothing.data

import com.example.sewingandclothing.constants.Constants.Companion.USER_DATABASE
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.reactivex.Observable
import javax.inject.Inject

class UserRepository @Inject constructor() {

    private val mAuth = FirebaseAuth.getInstance()

    private val usersDatabase = FirebaseDatabase.getInstance().reference

    fun isUserOrSeamstress(): Observable<String>{
        val currentUserID = mAuth.currentUser?.uid

        return Observable.create {emitter ->
            usersDatabase.child(currentUserID!!).child(USER_DATABASE).child("userVsSeamstress")
                .addListenerForSingleValueEvent(object : ValueEventListener{

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val userOrSeamstress = dataSnapshot.value.toString()
                        emitter.onNext(userOrSeamstress)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        emitter.onError(error.toException())
                    }

                })
        }
    }

}