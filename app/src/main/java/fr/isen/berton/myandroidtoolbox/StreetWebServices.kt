package fr.isen.berton.myandroidtoolbox

class StreetWebServices{

    var number : Int? = null
    var name: String? = null

    constructor() : super (){}

    constructor(Number : Int, Name: String) : super(){
        this.number = Number
        this.name = Name
    }

    override fun toString(): String {
        return "${number} ${name}"
    }
}