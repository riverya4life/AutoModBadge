suspend fun main() {
    val kord = Kord("TOKEN")

    kord.on<MessageCreateEvent> {

        try {
            repeat(6) {
                println(message.getGuild().createKeywordAutoModerationRule(
                    "ПРАВИЛО$it",
                    AutoModerationRuleEventType.MessageSend
                ) {
                    keywords = mutableListOf("СЛОВО$it")

                    assignActions(mutableListOf(TimeoutAutoModerationActionBuilder(1.minutes)))
                })
            }

            message.reply {
                content = "Готово."
            }
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }

    kord.login {
        @OptIn(PrivilegedIntent::class)
        intents += Intent.MessageContent
    }
}
