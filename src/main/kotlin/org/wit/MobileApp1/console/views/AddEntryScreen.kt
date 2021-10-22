package org.wit.MobileApp1.console.views

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import org.wit.MobileApp1.console.controllers.EntryUIController
import tornadofx.*
import kotlin.reflect.KClass

class AddEntryScreen : View("Add Entry") {
    val model = ViewModel()
    val _food = model.bind { SimpleStringProperty() }
    val _kcal = model.bind { SimpleStringProperty() }
    val _date = model.bind { SimpleStringProperty() }
    val _time = model.bind { SimpleStringProperty() }
    val entryUIController: EntryUIController by inject()

    override val root = form {
        setPrefSize(600.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Food") {
                textfield(_food).required()
            }
            field("Kcal") {
                textarea(_kcal).required()
            }
            field("Date") {
                textfield(_date).required()
            }
            field("Time") {
                textarea(_time).required()
            }
            button("Add") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        entryUIController.add(_food.toString(),_kcal.toString(),_date.toString(),_time.toString())

                    }
                }
            }
            button("Close") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        entryUIController.closeAdd()
                    }
                }
            }
        }
    }

    override fun onDock() {
        _food.value = ""
        _kcal.value = ""
        _date.value = ""
        _time.value = ""
        model.clearDecorators()
    }
}