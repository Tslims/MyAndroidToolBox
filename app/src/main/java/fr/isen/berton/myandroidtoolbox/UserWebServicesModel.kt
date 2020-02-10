package fr.isen.berton.myandroidtoolbox

class UserWebServicesModel{
    var gender: String ?=null
    var name : NameWebServices ?= null
    var location : LocationWebServices ?= null
    var email : String ?= null
    var picture : PictureWebServices ?= null

    constructor(){}

    constructor(Gender: String ,Name: NameWebServices, Location: LocationWebServices, Email: String, Picture: PictureWebServices){
        this.gender = gender
        this.name =Name
        this.location = Location
        this.email = Email
        this.picture = Picture
    }

}