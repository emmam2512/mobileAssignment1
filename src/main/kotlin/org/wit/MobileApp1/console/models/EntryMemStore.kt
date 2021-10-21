package org.wit.MobileApp1.console.models
import mu.KotlinLogging
import org.wit.MobileApp1.console.helpers.write
import org.wit.MobileApp1.console.models.EntryModel
import org.wit.MobileApp1.console.models.EntryStore

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class EntryMemStore : EntryStore {

    val entrys = ArrayList<EntryModel>()

    override fun findAll(): List<EntryModel> {
        return entrys
    }

    override fun findOne(id: Long) : EntryModel? {
        var foundEntrys: EntryModel? = entrys.find { p -> p.id == id }
        return foundEntrys
    }

    override fun create(entry: EntryModel) {
        entry.id = getId()
        entrys.add(entry)
        logAll()
        write("fileio.txt",entry.id.toString() + " Food:  " + entry.food.toString() + " Kcal: " + entry.kcal.toString() + " Date: " + entry.date.toString() + " Time: " + entry.time)
    }

    override fun update(entry: EntryModel) {
        var foundEntry = findOne(entry.id!!)
        if (foundEntry != null) {
            foundEntry.food = entry.food
            foundEntry.kcal = entry.kcal
            foundEntry.date = entry.date
            foundEntry.time = entry.time
        }

    }
    override fun delete(entry: EntryModel) {
        entrys.remove(entry)
    }

    internal fun logAll() {
        entrys.forEach { logger.info("${it}") }
    }
}

