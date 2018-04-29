package edu.washington.ruiheli.quizdroid

class Topic(title: String, shortDesc: String, longDesc: String, quizzes: Array<Quiz>){
    private val title: String
    private val shortDesc: String
    private val longDesc: String
    private val quizzes: Array<Quiz>

    init{
        this.title = title
        this.shortDesc = shortDesc
        this.longDesc = longDesc
        this.quizzes = quizzes
    }

    fun getTitle():String {
        return this.title
    }

    fun getShortDesc(): String{
        return this.shortDesc
    }

    fun getLongDesc(): String{
        return this.longDesc
    }

    fun getQuizzes(): Array<Quiz>{
        return this.quizzes
    }
}