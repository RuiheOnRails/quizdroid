package edu.washington.ruiheli.quizdroid

interface TopicRepository{
    fun getTopics(): Array<Topic>
}