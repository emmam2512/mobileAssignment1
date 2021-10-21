package org.wit.MobileApp1.console.models

interface EntryStore {
    fun findAll(): List<EntryModel>
    fun findOne(id: Long): EntryModel?
    fun create(entry: EntryModel)
    fun update(entry: EntryModel)
    fun delete(entry: EntryModel)
}
