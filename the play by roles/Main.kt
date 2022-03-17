import java.io.BufferedReader
import java.io.File

fun main() {

// Создание потока для чтения файла
    val readingStream = BufferedReader({}.javaClass.getResource("Text.txt").openStream().buffered().reader())
   
// Создаём словарь для ролей 
    val dictionary = mutableMapOf<String, HashMap<Int, String>>()
    var number = 1
// Флаг для определения состояния чтения
    var readingRoles = false
// Наполнения словаря репликами и ролями 
    readingStream.forEachLine { line ->
        if (line.contains("roles")) {
            readingRoles = true
        } 
// Проверка если уже прочитали роли  
        else if (line.contains("textLines")) {
          
            readingRoles = false
        } else {
            if (readingRoles) {
// Присваиваем роль               
                dictionary[line] = HashMap()
            } else {
// Берем часть строки до : как роль
                val role = line.split(':') [0]
// Вторую часть берем как фразу
                val roleWord = line.substring(line.indexOf(':') + 1).trim()
                val rolesWords = dictionary[role]
                rolesWords?.let { it -> it[number] = roleWord }
                number++
            }
        }
    }
// Создаём файл с обработанным текстом
    File("./out/production/roles/outText.txt").bufferedWriter().use { buffWriter ->
        for (role in dictionary.keys) {
            buffWriter.write("${role}:\n")
// Записываем реплику в файл
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
