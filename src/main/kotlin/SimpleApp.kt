import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.switch
import com.github.ajalt.clikt.parameters.types.file
import io.loradd.simple.SimpleLexer
import io.loradd.simple.SimpleParser
import org.antlr.v4.runtime.CharStreams
import java.io.FileInputStream

class SimpleCommand : CliktCommand(
    name = "simple",
    help = "Simple Language - Command Line Interface"
) {
    override fun run() = Unit
}

class ParseCommand : CliktCommand(
    name = "parse",
    help = "Parse simple language file"
) {
    override fun run() = Unit
}

class TokensCommand : CliktCommand(
    name = "tokens",
    help = "Print out token list"
) {
    private val file by argument()
        .file(mustExist = true, canBeDir = false, mustBeReadable = true, canBeSymlink = false)

    override fun run() {
        echo("Printing token list for ${file.name}")
        echo(tokens(SimpleLexer(CharStreams.fromStream(FileInputStream(file)))))
    }
}

class TreeCommand : CliktCommand(
    name = "tree",
    help = "Print out parse tree"
) {
    private val contextProvider by option().switch(
        "--workspace" to SimpleParser::workspace,
        "--function" to SimpleParser::function,
        "--statement" to SimpleParser::statement,
        "--expression" to SimpleParser::expression,
    ).default(SimpleParser::workspace)

    private val file by argument()
        .file(mustExist = true, canBeDir = false, mustBeReadable = true, canBeSymlink = false)

    override fun run() {
        echo("Printing parse tree for ${file.name}")
        echo(parseTree(contextProvider(parser(file))))
    }
}

fun main(args: Array<String>) =
    SimpleCommand()
        .subcommands(
            ParseCommand()
                .subcommands(TokensCommand(), TreeCommand())
        ).main(args)
