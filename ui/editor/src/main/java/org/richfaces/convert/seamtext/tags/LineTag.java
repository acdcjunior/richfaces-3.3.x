package org.richfaces.convert.seamtext.tags;

class LineTag extends HtmlTag {
    private static final long serialVersionUID = 6972613670825989225L;

    private String startTag;
    protected boolean isHtml = false;
    
    protected LineTag(String name) {
        super(name);
    }

    public LineTag(String name, String startTag) {
        super(name);
        setStartTag(startTag);
    }

    @Override
    public String printStart() {
        return startTag;
    }

    @Override
    public String printPlain() {
        isHtml = true;
        return super.printPlain();
    }
    
    private boolean isFirstChars = true;
    
    @Override
    public void appendBody(String str) {
        if (isFirstChars) {
            char text[] = str.toCharArray();
            int i = 0;
            while (i < text.length && (text[i] == '\r' || text[i] == '\n')) {
                i++;
            }
              
            if (i < text.length) {
                super.appendBody(str.substring(i));
                isFirstChars = false;
            } else {
                return;
            }
        } else {
            super.appendBody(str);
        }
    }

    @Override
    protected void appendChildTag(StringBuilder res, HtmlTag child) {
        if (child instanceof LineTag) {
            res.append(((LineTag)child).printPlain());
        } else {
            res.append(child);
        }
    }

    @Override
    protected String printBody() {
        while (!isBodyEmpty() && isBreakLineChild(body.getLast())) {
            body.removeLast();
        }
        
        return super.printBody();
    }
    
    @Override
    public String printEnd() {
        return "\n";
    }

    public String getStartTag() {
        return startTag;
    }

    public void setStartTag(String startTag) {
        this.startTag = startTag;
    }
}

