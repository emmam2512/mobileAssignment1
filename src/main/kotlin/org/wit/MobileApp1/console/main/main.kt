package org.wit.MobileApp1.console.main


import mu.KotlinLogging
import org.wit.MobileApp1.console.controllers.EntryController
import org.wit.MobileApp1.console.models.EntryModel
import org.wit.MobileApp1.console.models.EntryMemStore
import org.wit.MobileApp1.console.views.EntryView
import java.awt.SystemColor.menu

private val logger = KotlinLogging.logger {}

val entrys = EntryMemStore()
val entryView = EntryView()


fun main(args: Array<String>) {
    EntryController().start()
}