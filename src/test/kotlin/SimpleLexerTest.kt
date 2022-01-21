import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.provider.Arguments
import java.util.stream.Stream
import org.junit.jupiter.params.ParameterizedTest as test
import org.junit.jupiter.params.provider.MethodSource as arguments

class SimpleLexerTest {

    @test(name = "{1}")
    @arguments("SimpleLexerTestKt#lexerTests")
    fun tokenize(resourceFolder: String, resourceName: String) {
        val resourcePath = "${resourceFolder}/${resourceName}"
        assertEquals(expectedTokens(this, resourcePath), tokens(lexer(this, resourcePath)))
    }

}

// test cases
fun lexerTests(): Stream<Arguments> =
    Stream.of(
        Arguments.arguments("workspaces", "fibonacci"),
        Arguments.arguments("functions", "function_without_parameters"),
        Arguments.arguments("functions", "function_with_single_parameter"),
        Arguments.arguments("functions", "function_with_multiple_parameters"),
        Arguments.arguments("statements/return_statement", "return_statement"),
        Arguments.arguments("statements/print_statement", "print_statement"),
        Arguments.arguments("statements/conditional_statement", "conditional_statement"),
        Arguments.arguments("statements/bind_statement", "bind_statement"),
        Arguments.arguments("statements/expression_statement", "expression_statement"),
        Arguments.arguments("expressions/paren_expression", "paren_expression"),
        Arguments.arguments("expressions/unary_expression", "unary_expression"),
        Arguments.arguments("expressions/multiplicative_expression", "multiplicative_expression"),
        Arguments.arguments("expressions/additive_expression", "additive_expression"),
        Arguments.arguments("expressions/relational_expression", "relational_expression"),
        Arguments.arguments("expressions/equality_expression", "equality_expression"),
        Arguments.arguments("expressions/invocation_expression", "invocation_expression_without_arguments"),
        Arguments.arguments("expressions/invocation_expression", "invocation_expression_with_single_argument"),
        Arguments.arguments("expressions/invocation_expression", "invocation_expression_with_multiple_arguments"),
        Arguments.arguments("expressions/name_expression", "name_expression"),
        Arguments.arguments("expressions/number_expression", "number_expression")
    )
