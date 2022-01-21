import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.TerminalNode
import java.util.*

/**
 * Parse tree serialization utilities
 * Credits: [https://tomassetti.me/building-and-testing-a-parser-with-antlr-and-kotlin/]
 **/

// Parse Tree - Base Node
abstract class SimpleParseTreeElement {
    abstract fun toMultiLineString(indentation: String = ""): String
}

// Parse Tree - Intermediate Node
class SimpleParseTreeNode(val name: String) : SimpleParseTreeElement() {

    val children = LinkedList<SimpleParseTreeElement>()

    override fun toString(): String = "Node($name) $children"

    override fun toMultiLineString(indentation: String): String {
        val builder = StringBuilder("${indentation}${name}\n")
        children.forEach { child -> builder.append(child.toMultiLineString("$indentation  ")) }
        return builder.toString()
    }
}

// Parse Tree - Leaf None (terminals)
class SimpleParseTreeLeaf(private val text: String) : SimpleParseTreeElement() {
    override fun toString(): String =
        "T[$text]"

    override fun toMultiLineString(indentation: String): String =
        "${indentation}T[$text]\n"

}

// Transform ANTLR parse tree into SimpleParseTrees
fun toSimpleParseTree(node: ParserRuleContext): SimpleParseTreeNode {
    val simpleParseTree = SimpleParseTreeNode(node.javaClass.simpleName.removeSuffix("Context"))
    node.children.forEach { child ->
        when (child) {
            is ParserRuleContext -> simpleParseTree.children.add(toSimpleParseTree(child))
            is TerminalNode -> simpleParseTree.children.add(SimpleParseTreeLeaf(child.text))
        }
    }
    return simpleParseTree
}
