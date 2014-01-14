package ro.redeul.google.go.formatter.builder;

import com.intellij.formatting.Block;
import com.intellij.psi.tree.TokenSet;
import ro.redeul.google.go.formatter.blocks.*;
import ro.redeul.google.go.lang.psi.GoFile;
import ro.redeul.google.go.lang.psi.declarations.GoConstDeclaration;
import ro.redeul.google.go.lang.psi.declarations.GoConstDeclarations;
import ro.redeul.google.go.lang.psi.declarations.GoVarDeclaration;
import ro.redeul.google.go.lang.psi.declarations.GoVarDeclarations;
import ro.redeul.google.go.lang.psi.toplevel.*;
import ro.redeul.google.go.lang.psi.visitors.GoTypedVisitor;

import static ro.redeul.google.go.lang.parser.GoElementTypes.FUNCTION_PARAMETER_LIST;
import static ro.redeul.google.go.lang.parser.GoElementTypes.LITERAL_IDENTIFIER;

abstract class TopLevel extends GoTypedVisitor<Block, State> {

    @Override
    public Block visitFile(GoFile file, State s) {
        return new File(file, s.settings);
    }

    @Override
    public Block visitPackageDeclaration(GoPackageDeclaration declaration, State s) {
        return new Code<GoPackageDeclaration>(declaration, s.settings, s.indent, null, s.alignmentsMap);
    }

    @Override
    public Block visitImportDeclaration(GoImportDeclarations declarations, State s) {
        return new ImportDeclaration(declarations, s.settings);
    }

    @Override
    public Block visitImportSpec(GoImportDeclaration spec, State s) {
        return new Code<GoImportDeclaration>(spec, s.settings, s.indent);
    }

    @Override
    public Block visitFunctionDeclaration(GoFunctionDeclaration declaration, State s) {
        return new FunctionDeclaration(declaration, s.settings, s.indent, s.alignment, s.alignmentsMap);
    }

    @Override
    public Block visitFunctionParameter(GoFunctionParameter parameter, State s) {
        return new Code<GoFunctionParameter>(parameter, s.settings, s.indent)
            .setCustomSpacing(GoBlockUtil.CustomSpacings.NO_SPACE_BEFORE_COMMA)
            .setIndentedChildTokens(TokenSet.create(LITERAL_IDENTIFIER));
    }

    @Override
    public Block visitFunctionResult(GoFunctionResult result, State s) {
        return new Code<GoFunctionResult>(result, s.settings, s.indent)
            .setCustomSpacing(GoBlockUtil.CustomSpacings.FUNCTION_RESULT)
            .setIndentedChildTokens(TokenSet.create(FUNCTION_PARAMETER_LIST));
    }

    @Override
    public Block visitTypeDeclaration(GoTypeDeclaration declaration, State s) {
        return new TypeDeclaration(declaration, s.settings, s.indent, s.alignmentsMap);
    }

    @Override
    public Block visitTypeSpec(GoTypeSpec spec, State s) {
        return new TypeSpec(spec, s.settings, s.indent, s.alignmentsMap);
    }

    @Override
    public Block visitConstDeclaration(GoConstDeclarations declaration, State s) {
        return new ConstDeclaration(declaration, s.settings, s.indent, s.alignmentsMap);
    }

    @Override
    public Block visitConstSpec(GoConstDeclaration spec, State s) {
        return new ConstSpec(spec, s.settings, s.indent, s.alignmentsMap);
    }

    @Override
    public Block visitVarDeclaration(GoVarDeclarations declaration, State s) {
        return new VarDeclaration(declaration, s.settings, s.indent, s.alignmentsMap);
    }

    @Override
    public Block visitVarSpec(GoVarDeclaration spec, State s) {
        return new VarSpec(spec, s.settings, s.indent, s.alignmentsMap);
    }
}
