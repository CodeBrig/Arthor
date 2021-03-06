package com.codebrig.arthur.observe.structure.literal

import com.codebrig.arthur.SourceNode
import com.codebrig.arthur.observe.structure.StructureLiteral

/**
 * Used to determine and get the literal type of Python AST nodes
 *
 * @version 0.4
 * @since 0.2
 * @author <a href="mailto:brandon.fergerson@codebrig.com">Brandon Fergerson</a>
 * @author <a href="mailto:valpecaoco@gmail.com">Val Pecaoco</a>
 */
class PythonLiteral extends StructureLiteral {

    @Override
    String getNodeLiteralAttribute(SourceNode node) {
        switch (Objects.requireNonNull(node).internalType) {
            case "Num":
                return numberValueLiteral()
            case "Str":
                return stringValueLiteral()
            default:
                return null
        }
    }

    @Override
    List<String> getPossibleNodeLiteralAttributes(SourceNode node) {
        switch (Objects.requireNonNull(node).internalType) {
            case "Num":
                return [numberValueLiteral()]
            case "Str":
                return [stringValueLiteral()]
            default:
                return Collections.emptyList()
        }
    }

    @Override
    Object getNodeLiteralValue(SourceNode node) {
        boolean isNegative = node.parentSourceNode.children.any { it.roles.any { it.negative } }
        switch (node.getLiteralAttribute()) {
            case numberValueLiteral():
                return toDouble(((isNegative) ? "-" : "") + node.token)
            default:
                return super.getNodeLiteralValue(node)
        }
    }

    static boolean isOctalLiteral(String literal) {
        return literal.toUpperCase().contains("0O")
    }

    static Object getOctalValue(SourceNode node) {
        boolean isNegative = new PythonLiteral().isNodeLiteralNegative(node)
        String octal = node.token.toUpperCase().replaceFirst("0O", "0")
        return toLong(((isNegative) ? "-" : "") + octal)
    }
}
