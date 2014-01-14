package ro.redeul.google.go.lang.psi.impl.expressions.binary;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import ro.redeul.google.go.lang.lexer.GoTokenTypes;
import ro.redeul.google.go.lang.parser.GoElementTypes;
import ro.redeul.google.go.lang.psi.expressions.binary.GoLogicalAndExpression;
import ro.redeul.google.go.lang.psi.typing.GoType;
import ro.redeul.google.go.lang.psi.typing.GoTypes;
import ro.redeul.google.go.lang.psi.visitors.GoTypedVisitor;
import ro.redeul.google.go.lang.stubs.GoNamesCache;

import static ro.redeul.google.go.lang.psi.expressions.binary.GoLogicalAndExpression.Op;

public class GoLogicalAndExpressionImpl extends GoBinaryExpressionImpl<Op>
    implements GoLogicalAndExpression
{
    public GoLogicalAndExpressionImpl(@NotNull ASTNode node) {
        super(node, Op.values(), GoElementTypes.OPS_LOG_AND);
    }

    @Override
    protected GoType[] resolveTypes() {
        return new GoType[]{
                GoTypes.getBuiltin(
                        GoTypes.Builtin.Bool,
                        GoNamesCache.getInstance(getProject()))
        };
    }

    @Override
    public <T, S> T accept(GoTypedVisitor<T, S> visitor, S data) {
        return visitor.visitExpressionLogicalAnd(this, data);
    }
}

