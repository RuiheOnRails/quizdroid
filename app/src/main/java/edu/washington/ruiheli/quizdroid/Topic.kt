package edu.washington.ruiheli.quizdroid

class Topic(title: String, shortDesc: String, longDesc: String, quizzes: Array<Quiz>){
    private val title: String
    private val shortDesc: String
    private val desc: String
    private val questions: Array<Quiz>

    init{
        this.title = title
        this.shortDesc = shortDesc
        this.desc = longDesc
        this.questions = quizzes
    }

    fun getTitle():String {
        return this.title
    }

    fun getShortDesc(): String{
        return this.desc
    }

    fun getLongDesc(): String{
        return this.desc
    }

    fun getQuizzes(): Array<Quiz>{
        return this.questions
    }
}