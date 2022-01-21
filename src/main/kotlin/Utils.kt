import io.loradd.simple.SimpleLexer
import io.loradd.simple.SimpleParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.ParserRuleContext
import java.io.File
import java.io.FileInputStream
import java.util.*

// build parser for file
fun parser(file: File) =
    SimpleParser(CommonTokenStream(lexer(file)))

// build lexer for file
fun lexer(file: File) =
    SimpleLexer(CharStreams.fromStream(FileInputStream(file)))

// extract tree from parser
fun parseTree(context: ParserRuleContext): String =
    toSimpleParseTree(context).toMultiLineString().trimIndent()

// extract tokens from lexer
fun tokens(lexer: SimpleLexer): List<String> {
    val tokens = LinkedList<String>()
    do {
        val token = lexer.nextToken()
        when (token.type) {
            -1 -> tokens.add("EOF")
            else -> if (token.type != SimpleLexer.WHITESPACE)
                tokens.add(lexer.ruleNames[token.type - 1])
        }
    } while (token.type != -1)
    return tokens
}
