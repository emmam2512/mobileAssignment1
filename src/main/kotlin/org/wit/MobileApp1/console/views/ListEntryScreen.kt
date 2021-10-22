package org.wit.MobileApp1.console.views


import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.scene.control.TableView
import javafx.scene.layout.GridPane
import org.wit.MobileApp1.console.controllers.EntryUIController
import org.wit.MobileApp1.console.models.EntryModel
import tornadofx.*

class ListEntryScreen : View("List Entrys") {

    val entryUIController: EntryUIController by inject()
    val tableContent = entryUIController.entrys.findAll()
    val data = tableContent.observable()


    override val root = vbox {
        setPrefSize(600.0, 200.0)
        tableview(data) {
            readonlyColumn("ID", EntryModel::id)
            readonlyColumn("FOOD", EntryModel::food)
            readonlyColumn("KCAL", EntryModel::kcal)
            readonlyColumn("DATE", EntryModel::date)
            readonlyColumn("TIME", EntryModel::time)
        }
        button("Close") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    entryUIController.closeList()
                }
            }
        }
    }

}