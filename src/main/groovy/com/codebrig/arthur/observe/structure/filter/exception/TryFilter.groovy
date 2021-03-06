package com.codebrig.arthur.observe.structure.filter.exception

import com.codebrig.arthur.SourceNode
import com.codebrig.arthur.observe.structure.StructureFilter
import com.codebrig.arthur.observe.structure.filter.MultiFilter
import com.codebrig.arthur.observe.structure.filter.RoleFilter
import com.codebrig.arthur.observe.structure.filter.TypeFilter

/**
 * Match by try in exception handling construct
 *
 * @version 0.4
 * @since 0.3
 * @author <a href="mailto:brandon.fergerson@codebrig.com">Brandon Fergerson</a>
 * @author <a href="mailto:valpecaoco@gmail.com">Val Pecaoco</a>
 */
class TryFilter extends StructureFilter<TryFilter, Void> {

    private final MultiFilter filter

    TryFilter() {
        filter = MultiFilter.matchAll(
                new RoleFilter("TRY"), new RoleFilter("STATEMENT"),
                new RoleFilter().reject("BLOCK", "SCOPE", "BODY", "FINALLY")
        )
    }

    @Override
    boolean evaluate(SourceNode node) {
        boolean result = filter.evaluate(node)
        if (result) {
            return MultiFilter.matchAll(
                    new TypeFilter("TryExcept", "TryStatement", "TryKeyword", "CPPASTTryBlockStatement")
            ).evaluate(node)
        }
        return result
    }
}
