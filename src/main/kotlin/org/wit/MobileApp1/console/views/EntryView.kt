package org.wit.MobileApp1.console.views


import org.wit.MobileApp1.console.main.entrys
import org.wit.MobileApp1.console.models.EntryJSONStore
import org.wit.MobileApp1.console.models.EntryMemStore
import org.wit.MobileApp1.console.models.EntryModel

class EntryView {

    fun menu() : Int {

        var option : Int
        var input: String?
        val YELLOW_BACK = "\u001b[43m"
        val reset = "\u001b[0m"

        println(YELLOW_BACK + "MAIN MENU")
        println(" 1. Add Entry")
        println(" 2. Update Entry")
        println(" 3. List All Entrys")
        println(" 4. Search Entrys")
        println(" 5. Delete Entrys")
        println("-1. Exit")
        println(reset)
        print("Enter Option : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listEntrys(entrys : EntryJSONStore) {
        println("List All Entrys")
        println()
        entrys.logAll()
        println()
    }

    fun showEntry(entry : EntryModel) {
        if(entry != null)
            println("Entry Details [ $entry ]")
        else
            println("Entry Not Found...")
    }

    fun addEntryData(entry : EntryModel) : Boolean {

        println()
        print("Enter Food Name : ")
        entry.food = readLine()!!
        print("Enter Kcal : ")
        entry.kcal = readLine()!!
        print("Enter Date : ")
        entry.date = readLine()!!
        print("Enter Time : ")
        entry.time = readLine()!!

        return entry.food.isNotEmpty() && entry.kcal.isNotEmpty()  && entry.date.isNotEmpty() && entry.time.isNotEmpty()
    }

    fun updateEntryData(entry : EntryModel) : Boolean {

        var tempFood: String?
        var tempKcal: String?
        var tempDate: String?
        var tempTime: String?


        if (entry != null) {
            print("Enter a new Food for [ " + entry.food + " ] : ")
            tempFood = readLine()!!
            print("Enter a new Kcal for [ " + entry.kcal+ " ] : ")
            tempKcal = readLine()!!
            print("Enter a new Kcal for [ " + entry.kcal+ " ] : ")
            tempDate = readLine()!!
            print("Enter a new Kcal for [ " + entry.kcal+ " ] : ")
            tempTime = readLine()!!

            if (!tempFood.isNullOrEmpty() && !tempKcal.isNullOrEmpty()&& !tempDate.isNullOrEmpty()&& !tempTime.isNullOrEmpty()) {
                entry.food = tempFood
                entry.kcal = tempKcal
                entry.date = tempDate
                entry.time = tempTime
                return true
            }
        }
        return false
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

}