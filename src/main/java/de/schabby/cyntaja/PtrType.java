package de.schabby.cyntaja;

public class PtrType implements Type
{
    public final BasicType basicType;

    public PtrType(BasicType basicType)
    {
        this.basicType = basicType;
    }

    public String toC()
    {
        return basicType.getType() + "*";
    }

    public BasicType getBasicType() {
        return basicType;
    }

    @Override
    public String getType() {
        return basicType.getType() + "*";
    }
}
