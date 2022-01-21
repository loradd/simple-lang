import io.loradd.simple.SimpleLexer
import io.loradd.simple.SimpleParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream

// build lexer for resource
fun <T : Any> lexer(context: T, resourceName: String) =
    SimpleLexer(CharStreams.fromStream(context.javaClass.getResourceAsStream("/${resourceName}.simple")))

// build parser for resource
fun <T : Any> parser(context: T, resourceName: String) =
    SimpleParser(CommonTokenStream(lexer(context, resourceName)))

// extract expected parse tree from resource
fun <T : Any> expectedParseTree(context: T, resourceName: String): String =
    context.javaClass.getResourceAsStream("${resourceName}.tree")?.bufferedReader()?.readText()?.trimIndent() ?: ""


// extract expected tokens from resource
fun <T : Any> expectedTokens(context: T, resourceName: String): List<String> =
    context.javaClass.getResourceAsStream("${resourceName}.tokens")?.bufferedReader()?.readLines() ?: emptyList()
