package edu.washington.ruiheli.quizdroid

class Quiz(question: String, options: Array<String>, correctIndex: Int){

    private val text: String
    private val answers : Array<String>
    private val answer : Int

    init {
        this.text = question
        this.answers = options
        this.answer = correctIndex
    }

    fun getQuestion():String{
        return this.text
    }

    fun getOptions(): Array<String> {
        return this.answers
    }

    fun getCorrectIndex(): Int {
        return this.answer
    }
}