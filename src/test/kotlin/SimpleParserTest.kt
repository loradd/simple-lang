import io.loradd.simple.SimpleParser
import org.antlr.v4.runtime.ParserRuleContext
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import java.util.stream.Stream
import java.util.stream.Stream.of
import org.junit.jupiter.params.ParameterizedTest as test
import org.junit.jupiter.params.provider.MethodSource as arguments

class SimpleParserTest {

    @test(name = "{1}")
    @arguments("SimpleParserTestKt#parserTests")
    fun parse(resourceFolder: String, resourceName: String, contextProvider: (SimpleParser) -> ParserRuleContext) {
        val resourcePath = "${resourceFolder}/${resourceName}"
        assertEquals(expectedParseTree(this, resourcePath), parseTree(contextProvider(parser(this, resourcePath))))
    }

}

// test cases
fun parserTests(): Stream<Arguments> = of(
    arguments("workspaces", "fibonacci", SimpleParser::workspace),
    arguments("functions", "function_without_parameters", SimpleParser::function),
    arguments("functions", "function_with_single_parameter", SimpleParser::function),
    arguments("functions", "function_with_multiple_parameters", SimpleParser::function),
    arguments("statements/return_statement", "return_statement", SimpleParser::statement),
    arguments("statements/print_statement", "print_statement", SimpleParser::statement),
    arguments("statements/conditional_statement", "conditional_statement", SimpleParser::statement),
    arguments("statements/bind_statement", "bind_statement", SimpleParser::statement),
    arguments("statements/expression_statement", "expression_statement", SimpleParser::statement),
    arguments("expressions/paren_expression", "paren_expression", SimpleParser::expression),
    arguments("expressions/unary_expression", "unary_expression", SimpleParser::expression),
    arguments("expressions/multiplicative_expression", "multiplicative_expression", SimpleParser::expression),
    arguments("expressions/additive_expression", "additive_expression", SimpleParser::expression),
    arguments("expressions/relational_expression", "relational_expression", SimpleParser::expression),
    arguments("expressions/equality_expression", "equality_expression", SimpleParser::expression),
    arguments("expressions/invocation_expression", "invocation_expression_without_arguments", SimpleParser::expression),
    arguments("expressions/invocation_expression", "invocation_expression_with_single_argument", SimpleParser::expression),
    arguments("expressions/invocation_expression", "invocation_expression_with_multiple_arguments", SimpleParser::expression),
    arguments("expressions/name_expression", "name_expression", SimpleParser::expression),
    arguments("expressions/number_expression", "number_expression", SimpleParser::expression)
)
