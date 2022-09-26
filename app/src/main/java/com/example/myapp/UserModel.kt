package com.example.myapp

data class UserModel (
    var id: Int = getAutoId(),
    var name: String = "",
    var age: Int = 0,
    var email: String = "",
){
    companion object{
        fun getAutoId():Int{
            val random = java.util.Random()
            return random.nextInt(100)
        }
    }

}