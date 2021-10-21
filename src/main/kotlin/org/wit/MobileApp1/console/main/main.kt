package org.wit.MobileApp1.console.main

import mu.KotlinLogging
import org.wit.MobileApp1.console.models.EntryModel

private val logger = KotlinLogging.logger {}

val entrys = ArrayList<EntryModel>()

fun main(args: Array<String>) {
    logger.info { "Launching Entry Console App" }
    println("Entry Kotlin App Version 1.0")

    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> addEntry()
            2 -> updateEntry()
            3 -> listEntrys()
            4 -> searchEntry()
            -99 -> dummyData()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Entry Console App" }
}

fun menu() : Int {

    var option : Int
    var input: String?

    println("MAIN MENU")
    println(" 1. Add Entry")
    println(" 2. Update Entry")
    println(" 3. List All Entrys")
    println(" 4. Search Entrys")
    println("-1. Exit")
    println()
    print("Enter Option : ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option
}

fun addEntry(){
    var aEntry = EntryModel()
    println("Add Entry")
    println()
    print("Enter Food Name : ")
    aEntry.food = readLine()!!
    print("Enter Kcal : ")
    aEntry.kcal = readLine()!!
    print("Enter a Date : ")
    aEntry.date = readLine()!!
    print("Enter a Time : ")
    aEntry.time = readLine()!!


    if (aEntry.food.isNotEmpty() && aEntry.date.isNotEmpty()&& aEntry.time.isNotEmpty()&& aEntry.kcal.isNotEmpty()) {
        aEntry.id = entrys.size.toLong()
        entrys.add(aEntry.copy())
        logger.info("Entry Added : [ $aEntry ]")
    }
    else
        logger.info("Entry Not Added")
}

fun updateEntry() {
    println("Update Entry")
    println()
    listEntrys()
    var searchId = getId()
    val aEntry = search(searchId)
    var tempFood : String?
    var tempDate : String?
    var tempTime : String?
    var tempKcal : String?

    if(aEntry != null) {
        print("Enter a new Food for [ " + aEntry.food + " ] : ")
        tempFood = readLine()!!
        print("Enter a new Date for [ " + aEntry.date + " ] : ")
        tempDate = readLine()!!
        print("Enter a new Time for [ " + aEntry.time + " ] : ")
        tempTime = readLine()!!
        print("Enter a new Kcal for [ " + aEntry.kcal + " ] : ")
        tempKcal = readLine()!!

        if (!tempFood.isNullOrEmpty() && !tempDate.isNullOrEmpty()&& !tempTime.isNullOrEmpty()&& !tempKcal.isNullOrEmpty()) {
            aEntry.food = tempFood
            aEntry.date = tempDate
            aEntry.time = tempTime
            aEntry.kcal = tempKcal

            println(
                "You updated [ " + aEntry.food + " ] for food " +
                        "and [ " + aEntry.date + " ] for date" +
                        "and [ " + aEntry.time + " ] for time")
                        "and [ " + aEntry.kcal + " ] for time"
            logger.info("Entry Updated : [ $aEntry ]")
        }
        else
            logger.info("Entry Not Updated")
    }
    else
        println("Entry Not Updated...")
}

fun listEntrys() {
    println("List All Entrys")
    println()
    entrys.forEach { logger.info("${it}") }
    println()
}

fun searchEntry() {

    var searchId = getId()
    val aEntry = search(searchId)

    if(aEntry != null)
        println("Entry Details [ $aEntry ]")
    else
        println("Entry Not Found...")
}

fun getId() : Long {
    var strId : String? // String to hold user input
    var searchId : Long // Long to hold converted id
    print("Enter id to Search/Update : ")
    strId = readLine()!!
    searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
        strId.toLong()
    else
        -9
    return searchId
}

fun search(id: Long) : EntryModel? {
    var foundEntry: EntryModel? = entrys.find { p -> p.id == id }
    return foundEntry
}

fun dummyData() {
    entrys.add(EntryModel(1, "New York New York", "So Good They Named It Twice"))
    entrys.add(EntryModel(2, "Ring of Kerry", "Some place in the Kingdom"))
    entrys.add(EntryModel(3, "Waterford City", "You get great Blaas Here!!"))
}