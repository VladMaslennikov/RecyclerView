package com.example.recyclerviewmvvm.model

import com.github.javafaker.Faker
import java.util.*

typealias UsersListener = (users: List<User>) -> Unit

class UsersService  {

    private var users = mutableListOf<User>()
    private val listeners = mutableSetOf<UsersListener>()
    init {
        val faker = Faker.instance()
        IMAGES.shuffle()
        users = (1..100).map {User(
            id = it.toLong(),
            name = faker.name().name(),
            company = faker.company().name(),
            photo = IMAGES[it % IMAGES.size]
        )}.toMutableList()
    }

    fun getUsers():List<User>{
        return users
    }

    fun deleteUser(user: User){
        val indexToDelete = users.indexOfFirst { it.id == user.id }
        if (indexToDelete != -1) users.removeAt(indexToDelete)
        notifyChange()
    }

    fun moveUser(user: User, moveBy: Int){
        val oldIndex = users.indexOfFirst { it.id == user.id }
        if (oldIndex == -1) return
        val newIndex = oldIndex + moveBy
        if (newIndex < 0 || newIndex >= users.size) return
        Collections.swap(users, oldIndex, newIndex)
        notifyChange()
    }

    fun addListener(listener: UsersListener){
        listeners.add(listener)
        listener.invoke(users)
    }

    fun deleteListener(listener: UsersListener){
        listeners.remove(listener)
    }

    private fun notifyChange(){
        listeners.forEach{ it.invoke(users) }
    }

    companion object {
        private val IMAGES = mutableListOf(
            "https://lifehacker.ru/wp-content/uploads/2018/04/woman-3083375_1920_1524655476-e1524655499103.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQPvCq4vsa7EBhRvVLjVxyRurH4N4PxouNSaDrlY-5YTFcogt-F3T2sSuJxkrhaTKY_v9w&usqp=CAU",
            "https://i0.wp.com/coroklet.ru/wp-content/uploads/2017/08/2x9mdy-dhfm_ru-devushka-volosy-kra.jpg",
            "https://cdn.lifehacker.ru/wp-content/uploads/2018/04/man-1828654_1920_1524662517-e1524662535417.jpg",
            "https://img.freepik.com/free-photo/portrait-of-successful-man-having-stubble-posing-with-broad-smile-keeping-arms-folded_171337-1267.jpg?w=2000",
            "https://img.freepik.com/free-photo/handsome-young-entertainer-with-microphone-giving-speech_176420-17969.jpg?w=2000",
            "https://st3.depositphotos.com/4421345/14894/i/450/depositphotos_148943109-stock-photo-the-young-man-shrugs-his.jpg",
            "https://st3.depositphotos.com/4421345/18060/i/600/depositphotos_180605608-stock-photo-unsure-caucasian-man-throws-up.jpg",
            "https://st3.depositphotos.com/1017228/18684/i/600/depositphotos_186840254-stock-photo-portrait-of-a-scared-bearded.jpg",
            "https://st2.depositphotos.com/3489481/9431/i/450/depositphotos_94318786-stock-photo-frightened-shocked-scared-woman-looking.jpg",
        )
    }
}