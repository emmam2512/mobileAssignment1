package org.wit.MobileApp1.console.controllers

import mu.KotlinLogging
import org.wit.MobileApp1.console.models.EntryJSONStore
import org.wit.MobileApp1.console.models.EntryModel
import org.wit.MobileApp1.console.views.*
import tornadofx.*

class EntryUIController : Controller() {

    val entrys = EntryJSONStore()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Entry TornadoFX UI App" }
    }
    fun add(_food : String, _kcal : String,_date : String, _time : String){

        var aEntry = EntryModel(food = _food, kcal = _kcal, date = _date, time = _time)
            entrys.create(aEntry)
            logger.info("Entry Added")
    }

    fun loadListScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(ListEntryScreen::class, sizeToScene = true, centerOnScreen = true)
        }
        entrys.logAll()
    }

    fun loadAddScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(AddEntryScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun loadUpdateScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(UpdateEntryScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun loadDeleteScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(DeleteEntryScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun loadSearchScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(SearchEntryScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun closeAdd() {
        runLater {
            find(AddEntryScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun closeList() {
        runLater {
            find(ListEntryScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

}