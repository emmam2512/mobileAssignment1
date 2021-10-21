package org.wit.MobileApp1.console.controllers

import mu.KotlinLogging
import org.wit.MobileApp1.console.models.EntryJSONStore
import org.wit.MobileApp1.console.models.EntryMemStore
import org.wit.MobileApp1.console.models.EntryModel
import org.wit.MobileApp1.console.views.EntryView

class EntryController {

    val entrys = EntryJSONStore()
    val entryView = EntryView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { " Launching Entry Console App" }
        println("Entry Kotlin App Version 1.0")
    }

    fun menu() :Int { return entryView.menu()

     }

    fun add(){
        var aEntry = EntryModel()

        if (entryView.addEntryData(aEntry))
            entrys.create(aEntry)
        else
            logger.info("Entry Not Added")
    }

    fun list() {
        entryView.listEntrys(entrys)
    }

    fun update() {

        entryView.listEntrys(entrys)
        var searchId = entryView.getId()
        val aEntry = search(searchId)

        if(aEntry != null) {
            if(entryView.updateEntryData(aEntry)) {
                entrys.update(aEntry)
                entryView.showEntry(aEntry)
                logger.info("Entry Updated : [ $aEntry ]")
            }
            else
                logger.info("Entry Not Updated")
        }
        else
            println("Entry Not Updated...")
    }

    fun search() {
        val aEntry = search(entryView.getId())!!
        entryView.showEntry(aEntry)
    }


    fun search(id: Long) : EntryModel? {
        var foundEntry = entrys.findOne(id)
        return foundEntry
    }
    fun delete() {
        entryView.listEntrys(entrys)
        var searchId = entryView.getId()
        val aEntry = search(searchId)

        if(aEntry != null) {
            entrys.delete(aEntry)
            println("Entry Deleted...")
            entryView.listEntrys(entrys)
        }
        else
            println("entry Not Deleted...")
    }
    fun dummyData() {
        entrys.create(EntryModel(1, "donut", "10/10/2021", "14:00","146"))
        entrys.create(EntryModel(2, "Cake", "10/10/2021", "15:00", "450"))
        entrys.create(EntryModel(3, "Peas", "11/10/2021", "17:00", "50"))
    }
    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
                -99 -> dummyData()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down  Entry Console App" }
    }

}