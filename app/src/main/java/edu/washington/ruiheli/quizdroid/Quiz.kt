package edu.washington.ruiheli.quizdroid

class Quiz(question: String, options: Array<String>, correctIndex: Int){

    private val question: String
    private val options : Array<String>
    private val correctIndex : Int

    init {
        this.question = question
        this.options = options
        this.correctIndex = correctIndex
    }

    fun getQuestion():String{
        return this.question
    }

    fun getOptions(): Array<String> {
        return this.options
    }

    fun getCorrectIndex(): Int {
        return this.correctIndex
    }
}