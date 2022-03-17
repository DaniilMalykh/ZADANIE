import java.io.BufferedReader
import java.io.File

fun main() {


    val readingStream = BufferedReader({}.javaClass.getResource("Text.txt").openStream().buffered().reader())
    val dictionary = mutableMapOf<String, HashMap<Int, String>>()
    var number = 1
    var readingRoles = false

    readingStream.forEachLine { line ->
        if (line.contains("roles")) {
            readingRoles = true
        } else if (line.contains("textLines")) {
            readingRoles = false
        } else {
            if (readingRoles) {
                dictionary[line] = HashMap()
            } else {

                val role = line.split(':') [0]
                val roleWord = line.substring(line.indexOf(':') + 1).trim()
                val rolesWords = dictionary[role]
                rolesWords?.let { it -> it[number] = roleWord }
                number++
            }
        }
    }
   
    File("./out/production/roles/outText.txt").bufferedWriter().use { buffWriter ->
        for (role in dictionary.keys) {
            buffWriter.write("${role}:\n")
            
            val roleWordss = dictionary[role]
             roleWordss?.let { roleWordss ->
                for (phraseKey in roleWordss.keys) {
                    buffWriter.write("${phraseKey} ${roleWordss[phraseKey]}\n")
                }
            }
            buffWriter.newLine()
        }
    }
}
