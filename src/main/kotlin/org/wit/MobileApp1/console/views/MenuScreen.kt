package org.wit.MobileApp1.console.views

import javafx.application.Platform
import javafx.geometry.Orientation
import org.wit.MobileApp1.console.controllers.EntryUIController
import tornadofx.*

class MenuScreen : View("Entry Main Menu") {

    val entryUIController: EntryUIController by inject()

    override val root = form {
        setPrefSize(400.0, 300.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")
            button("Add Entry") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        entryUIController.loadAddScreen()
                    }
                }
            }
            text("")
            button("List Entrys") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        entryUIController.loadListScreen()
                    }
                }
            }
            text("")
            button("Update Entrys") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        entryUIController.loadUpdateScreen()
                    }
                }
            }
            text("")
            button("Search Entrys") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        entryUIController.loadSearchScreen()
                    }
                }
            }
            text("")
            button("Delete Entrys") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        entryUIController.loadDeleteScreen()
                    }
                }
            }
            text("")
            button("Exit") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        Platform.exit();
                        System.exit(0);
                    }
                }
            }
        }

    }


}