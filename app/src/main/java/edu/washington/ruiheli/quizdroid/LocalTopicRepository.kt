package edu.washington.ruiheli.quizdroid

class LocalTopicRepository:TopicRepository{
    override fun getTopics(): Array<Topic> {
        var mathQ1 = Quiz("1 + 1 = ?", arrayOf("1","2","3","4"),1)
        var mathQ2 = Quiz("1 + 2 = ?", arrayOf("1","2","3","4"),2)
        var mathTopic = Topic("Math","Basic Math", "These are very basic math questions", arrayOf(mathQ1,mathQ2))

        var physicsQ1 = Quiz("What's the 1kg/ m/s^2 in Newton unit?", arrayOf("1","2","3","4"), 0)
        var physicsQ2 = Quiz("How many Gs is Earth's gravity?", arrayOf("1","2","3","4"), 0)
        var physicsTopic = Topic("Physics","Basic Physics", "These are very basic physics questions", arrayOf(physicsQ1,physicsQ2))

        var marvelQ1 = Quiz("What's iron man's first name?", arrayOf("Tony", "Steven", "Bruce", "Strange"), 0)
        var marvelQ2 = Quiz("What's Dr.Strange's last name?", arrayOf("Tony", "Steven", "Bruce", "Strange"), 3)
        var marvelTopic = Topic("Marvel", "Super Hero", "Questions about marvel super hero", arrayOf(marvelQ1,marvelQ2))

        return arrayOf(mathTopic,physicsTopic,marvelTopic)
    }
}