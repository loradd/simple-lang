import io.loradd.simple.SimpleLexer
import org.antlr.v4.runtime.CharStreams
import java.util.*
import kotlin.test.assertEquals
import org.junit.Test as test

class SimpleLexerTest {

//    Workspace

    @test
    fun parseFibonacciWorkspace() {
        assertEquals(expectedTokensForResource("fibonacci"), tokens(lexerForResource("fibonacci")))
    }

//    Function

    @test // function (no parameters)
    fun parseFunctionWithoutParameters() {
        assertEquals(
            listOf("FUNCTION", "NAME", "L_PAREN", "R_PAREN", "L_BRACE", "R_BRACE", "EOF"),
            tokens(lexerForCode("function example() {}"))
        )
    }

    @test // function (single parameter)
    fun parseFunctionWithSingleParameter() {
        assertEquals(
            listOf("FUNCTION", "NAME", "L_PAREN", "NAME", "R_PAREN", "L_BRACE", "R_BRACE", "EOF"),
            tokens(lexerForCode("function example(a) {}"))
        )
    }

    @test // function (multiple parameters)
    fun parseFunctionWithMultipleParameters() {
        assertEquals(
            listOf("FUNCTION", "NAME", "L_PAREN", "NAME", "COMMA", "NAME", "R_PAREN", "L_BRACE", "R_BRACE", "EOF"),
            tokens(lexerForCode("function example(a, b) {}"))
        )
    }

//    Statements

    @test // return statement
    fun parseReturnStatement() {
        assertEquals(
            listOf("RETURN", "NAME", "COLON", "EOF"),
            tokens(lexerForCode("return expression;"))
        )
    }

    @test // print statement
    fun parsePrintStatement() {
        assertEquals(
            listOf("PRINT", "NAME", "COLON", "EOF"),
            tokens(lexerForCode("print expression;"))
        )
    }

    @test // conditional statement
    fun parseConditionalStatement() {
        assertEquals(expectedTokensForResource("conditional"), tokens(lexerForResource("conditional")))
    }

    @test // bind statement
    fun parseBindStatement() {
        assertEquals(
            listOf("NAME", "BIND", "NAME", "COLON", "EOF"),
            tokens(lexerForCode("variable = expression;"))
        )
    }

    @test // expression statement
    fun parseExpressionStatement() {
        assertEquals(
            listOf("NAME", "PLUS", "NAME", "COLON", "EOF"),
            tokens(lexerForCode("first + second;"))
        )
    }

//    Expressions

    @test // parenExpression
    fun parseParenExpression() {
        assertEquals(
            listOf("L_PAREN", "L_PAREN", "NUMBER", "R_PAREN", "R_PAREN", "EOF"),
            tokens(lexerForCode("((10))"))
        )
    }

    @test // unary expression
    fun parseUnaryMixedExpression() {
        assertEquals(
            listOf("MINUS", "PLUS", "NOT", "NAME", "EOF"),
            tokens(lexerForCode("-+!variable"))
        )
    }

    @test // multiplicative expression
    fun parseMultiplicativeExpression() {
        assertEquals(
            listOf("NAME", "ASTERISK", "NUMBER", "DIVISION", "NAME", "EOF"),
            tokens(lexerForCode("first * 10 / third"))
        )
    }

    @test // additive expression
    fun parseAdditionExpression() {
        assertEquals(
            listOf("NUMBER", "PLUS", "NAME", "MINUS", "NUMBER", "EOF"),
            tokens(lexerForCode("10 + second - 11"))
        )
    }

    @test // relational expression
    fun parseRelationalExpression() {
        assertEquals(expectedTokensForResource("relational"), tokens(lexerForResource("relational")))
    }

    @test // equality expression
    fun parseEqualityExpression() {
        assertEquals(
            listOf("NAME", "EQUAL", "NUMBER", "NOT_EQUAL", "NAME", "EOF"),
            tokens(lexerForCode("first == 10 != second"))
        )
    }

    @test // invocation expression (no arguments)
    fun parseInvocationExpressionWithoutArguments() {
        assertEquals(
            listOf("NAME", "L_PAREN", "R_PAREN", "EOF"),
            tokens(lexerForCode("example()"))
        )
    }

    @test // invocation expression (single argument)
    fun parseInvocationExpressionWithSingleArgument() {
        assertEquals(
            listOf("NAME", "L_PAREN", "NUMBER", "R_PAREN", "EOF"),
            tokens(lexerForCode("example(10)"))
        )
    }

    @test // invocation expression (multiple arguments)
    fun parseInvocationExpressionWithMultipleArguments() {
        assertEquals(
            listOf("NAME", "L_PAREN", "NAME", "COMMA", "NUMBER", "R_PAREN", "EOF"),
            tokens(lexerForCode("example(variable, 10)"))
        )
    }

    @test // name expression
    fun parseNameExpression() {
        assertEquals(listOf("NAME", "EOF"), tokens(lexerForCode("simple")))
    }

    @test // number expression
    fun parseNumberExpression() {
        assertEquals(listOf("NUMBER", "EOF"), tokens(lexerForCode("10")))
    }

    private fun lexerForCode(code: String) = SimpleLexer(CharStreams.fromString(code))

    private fun lexerForResource(resourceName: String) =
        SimpleLexer(CharStreams.fromStream(this.javaClass.getResourceAsStream("/${resourceName}.simple")))

    private fun tokens(lexer: SimpleLexer): List<String> {
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

    private fun expectedTokensForResource(resourceName: String) =
        this.javaClass.getResourceAsStream("${resourceName}.tokens")?.bufferedReader()?.readLines() ?: emptyList()

}
