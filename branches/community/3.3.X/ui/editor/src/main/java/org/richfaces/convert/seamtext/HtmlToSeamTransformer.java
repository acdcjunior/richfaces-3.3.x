package org.richfaces.convert.seamtext;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Stack;

import org.richfaces.convert.seamtext.tags.HtmlTag;

import static org.richfaces.convert.seamtext.tags.HtmlTag.*;

/**
 * @user: akolonitsky
 * Date: Mar 24, 2009
 */
public class HtmlToSeamTransformer {
    public boolean preformatted = false;

    private static final Collection<String> SIMPLE_HTML_SEAM_TEXT_ELEMENTS = new HashSet<String>(Arrays.asList(
            DEL, SUP, PRE, Q, I, TT, U)); 

    private static final Collection<String> FORMATTED_HTML_SEAM_TEXT_ELEMENTS = new HashSet<String>(Arrays.asList(
            H1, H2, H3, H4, P, UL, OL, LI, A, BLOCKQOUTE));

    private static final Collection<String> HTML_SEAM_TEXT_ELEMENTS =
            new HashSet<String>(SIMPLE_HTML_SEAM_TEXT_ELEMENTS.size() + FORMATTED_HTML_SEAM_TEXT_ELEMENTS.size());
    static {
        HTML_SEAM_TEXT_ELEMENTS.addAll(SIMPLE_HTML_SEAM_TEXT_ELEMENTS);
        HTML_SEAM_TEXT_ELEMENTS.addAll(FORMATTED_HTML_SEAM_TEXT_ELEMENTS);
    }

    private Stack<HtmlTag> htmlElementStack = new Stack<HtmlTag>();

    private StringBuilder newLinesCollector;
    
    private HtmlTag currentTag = null;

    private boolean isHeaderProcessed = false;

    private boolean isFirstChars = false;

    public Stack<HtmlTag> getHtmlElementStack() {
        return htmlElementStack;
    }

    public void setHtmlElementStack(Stack<HtmlTag> htmlElementStack) {
        this.htmlElementStack = htmlElementStack;
    }

    private void appendStart(HtmlTag tag) {
        currentTag.appendBody(tag);
        currentTag = tag;
    }

    private void appendBody(String ... strings) {
        for (String str : strings) {
            if (str != null && !"".equals(str)) {
                currentTag.appendBody(str);
            }
        }
    }
    
    private void appendEnd() {
        currentTag = htmlElementStack.peek();
    }
    
    public String escapeSeamText(String tokenName, boolean preformatted) {
        final StringBuilder result = new StringBuilder(tokenName.length() + 1);
        if (!preformatted) {
            result.append('\\');
        } 
        result.append(tokenName);

        return result.toString();
    }

    public boolean isSeamTextElement(HtmlTag element) {
        return HTML_SEAM_TEXT_ELEMENTS.contains(element.getName().toLowerCase());
    }

    public boolean isSimpleSeamTextElement(HtmlTag element) {
        return SIMPLE_HTML_SEAM_TEXT_ELEMENTS.contains(element.getName().toLowerCase());
    }

    public boolean isFormattedHtmlSeamTextElement(HtmlTag element) {
        return FORMATTED_HTML_SEAM_TEXT_ELEMENTS.contains(element.getName().toLowerCase());
    }

    public boolean isPlainHtmlRequired(HtmlTag name) {

        if (!isSeamTextElement(name)) {
            return true;
        }

        if (isSimpleSeamTextElement(name)) {
            return false;
        }

        if (!name.isLink() && !name.isParagraph()) {
            for (HtmlTag token : htmlElementStack) {
                if (token.isHeader() || token.isListItem()) {
                    return true;
                }
            }
        }

        return false;
    }
    
    public void text(char[] text, int start, int length) {
        if (isFirstChars) {
//            append(htmlElementStack.peek().printStartSuffix());

            while (text[start] == '\n' && length > 0) {
                start++;
                length--;
            }
            isFirstChars = false;
        }


        while (length > 0) {
            processChar(text, start, 1);

            setHeaderProcessed();

            start ++;
            length --;
        }
    }

    private void setHeaderProcessed() {
        final HtmlTag token = htmlElementStack.peek();
        if (!token.isParagraph() && isHeaderProcessed) {
            isHeaderProcessed = false;
        }
    }

    private void processChar(final char[] text, final int start, final int localLength) {
        switch (text[start]) {
            case '*': case '|': case '^' : case '+':
            case '=': case '#': case '\\': case '~':
            case '[': case ']': case '`' :
            case '<': case '>': case '&':
                seamCharacters(text, start, localLength);
                break;

            case '\n':
                final HtmlTag token = htmlElementStack.peek();
                if (!(/*token.isParagraph()
                      ||*/ isHeaderProcessed
                      || HtmlToSeamSAXParser.ROOT_TAG_NAME.equals(token.getName())
                      || token.isList())) {
                    out(text, start, localLength);
                }

                break;
            default:
                out(text, start, localLength);
                break;
        }
    }

    public void seamCharacters(char[] text, int start, int length) {
        appendBody(escapeSeamText(new String(text, start, length), preformatted));
    }

    public void plain(char[] text, int start, int length) {
        out(text, start, length);

        setHeaderProcessed();
    }

    public void out(char[] text, int start, int length) {
        appendBody(new String(text, start, length));
    }

    public void space(char[] text, int start, int length) {
        if (!htmlElementStack.isEmpty()) {
            final HtmlTag token = htmlElementStack.peek();
            if (isPlainHtmlRequired(token)) {
                out(text, start, length);
            }
        }
    }

    public void newline(char[] text, int start, int length) {
        if (preformatted && newLinesCollector != null){
            newLinesCollector.append(new String(text, start, length));
        }
    }

    public void openTag(HtmlTag tag) {
        
        if (tag.isPreFormattedElement()) {
            preformatted = true;
        }

        if (isPlainHtmlRequired(tag)) {
            outValueCollector();
            appendStart(tag);
        } else {
            if (!isFormattedHtmlSeamTextElement(tag)) {
                outValueCollector();
            } 
            appendStart(tag);
        }

        isFirstChars = true;
    }

    private void outValueCollector() {
        if (newLinesCollector != null) {
            appendBody(newLinesCollector.toString());
            newLinesCollector = null;
        }
    }

    public void closeTagWithBody(HtmlTag tag) {
        if (!tag.getName().equals(tag.getName())) {
            throw new IllegalStateException("Can not convert to the Seam Text:  </" + tag.getName() + "> expected");
        }

        String value = "";
        if (newLinesCollector != null) {
            value = newLinesCollector.toString();
        }

        if (tag.isLink()) {
            value = "";
        } else if (tag.isParagraph()) {
            isHeaderProcessed = false;
        } else if (tag.isHeader()) {
            isHeaderProcessed = true;
        }
        
        appendBody(value);
        appendEnd();

        newLinesCollector = null;
        if (tag.isPreFormattedElement()) {
            preformatted = false;
        }
    }
    
    public HtmlTag getCurrentTag() {
        return currentTag;
    }

    public void setCurrentTag(HtmlTag currentTag) {
        this.currentTag = currentTag;
    }
}

