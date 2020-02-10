package fr.isen.berton.myandroidtoolbox

class NameWebServices{
    var title : String? = null
    var first : String? = null
    var last : String? = null

    constructor() : super (){}

    constructor(Title : String, First: String, Last: String) : super(){
        this.title = Title
        this.last = Last
        this.first = First
    }
}