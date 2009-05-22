package org.richfaces.convert.seamtext.tags;

public class ParagraphTag extends LineTag {

    private static final long serialVersionUID = 1720000557944774249L;

    protected ParagraphTag() {
        super(P);
    }
    
    @Override
    public String printStart() {
        return "";
    }

    @Override
    public String printEnd() {
        return "\n\n"; 
    }

    @Override
    public String printStartSuffix() {
        return "";
    }
}
