package newFeature;

public sealed interface Expr
        permits ConstantExpr { }

record ConstantExpr(int i)       implements Expr { }

