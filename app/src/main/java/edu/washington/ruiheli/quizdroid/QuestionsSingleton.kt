package edu.washington.ruiheli.quizdroid


object QuizSingleton{
    var currentTopicIndex: Int = 0
    var currentQuestionIndex: Int = 0
    var currentSelectedAnswer: Int = 0
    var rightAnswerCount: Int = 0
    var preRightAnswerCount: Int = 0
    var topics: Array<String>? = null

    // topic to description
    var topicDescription: HashMap<String, String>? = null

    //topic to array of questions
    var topicQuestions : HashMap<String, Array<String>>? = null

    //question to array of options
    var options: HashMap<String, Array<String>>? = null

    //question to correctAnswer
    var answers: HashMap<String, String>? = null

}
