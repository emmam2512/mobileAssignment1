package org.wit.MobileApp1.console.models



import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import org.wit.MobileApp1.console.helpers.*
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "Entrys.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<EntryModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class EntryJSONStore : EntryStore {

    var Entrys = mutableListOf<EntryModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<EntryModel> {
        return Entrys
    }

    override fun findOne(id: Long) : EntryModel? {
        var foundEntry: EntryModel? = Entrys.find { p -> p.id == id }
        return foundEntry
    }

    override fun create(Entry: EntryModel) {
        Entry.id = generateRandomId()
        Entrys.add(Entry)
        serialize()
    }

    override fun update(Entry: EntryModel) {
        var foundEntry = findOne(Entry.id!!)
        if (foundEntry != null) {
            foundEntry.food = Entry.food
            foundEntry.kcal = Entry.kcal
            foundEntry.date = Entry.date
            foundEntry.time = Entry.time
        }
        serialize()
    }

    internal fun logAll() {
        Entrys.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(Entrys, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        Entrys = Gson().fromJson(jsonString, listType)
    }
}