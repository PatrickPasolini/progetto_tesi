package it.unibs.view.atomicElements;

import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * Una JTextField con un placeholder che permette l'inserimento di numeri,
 * interi o decimali, con possibilità di limitare il valore a un range.
 * Estende {@link TextFieldWithPlaceholder} e utilizza un {@link DocumentFilter}
 * per filtrare l'input durante la digitazione, consentendo anche l'inserimento
 * del valore minimo e massimo esatti.
 */
public class NumericFieldWithPlaceholder extends TextFieldWithPlaceholder {
    private static final long serialVersionUID = 1L;

    private final boolean allowDecimal;
    private final Double minValue;
    private final Double maxValue;
    private final String minStr;
    private final String maxStr;

    private static final String INTEGER_REGEX = "\\d*";
    private static final String COMPLETE_INTEGER_REGEX = "\\d+";
    private static final String DECIMAL_REGEX = "-?\\d*([\\.,]\\d*)?";
    private static final String COMPLETE_DECIMAL_REGEX = "-?\\d+(?:[\\.,]\\d+)?";

    public NumericFieldWithPlaceholder(String placeholder) {
        this(placeholder, false, null, null);
    }

    public NumericFieldWithPlaceholder(String placeholder, boolean allowDecimal) {
        this(placeholder, allowDecimal, null, null);
    }

    public NumericFieldWithPlaceholder(String placeholder,
                                       boolean allowDecimal,
                                       Double minValue,
                                       Double maxValue) {
        super(placeholder);
        this.allowDecimal = allowDecimal;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.minStr = (minValue != null) ? String.valueOf(minValue) : null;
        this.maxStr = (maxValue != null) ? String.valueOf(maxValue) : null;
        installFilter();
    }

    private void installFilter() {
        DocumentFilter filter = new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                if (string == null) return;
                String current = fb.getDocument().getText(0, fb.getDocument().getLength());
                StringBuilder sb = new StringBuilder(current).insert(offset, string);
                if (isValid(sb.toString())) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (text == null) return;
                String current = fb.getDocument().getText(0, fb.getDocument().getLength());
                StringBuilder sb = new StringBuilder(current).replace(offset, offset + length, text);
                if (isValid(sb.toString())) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }

            @Override
            public void remove(FilterBypass fb, int offset, int length)
                    throws BadLocationException {
                super.remove(fb, offset, length);
            }

            private boolean isValid(String text) {
                // input intermedio (vuoto, segno, separatore)
                if (text.isEmpty() || text.equals("-") || text.equals(".") || text.equals(",")) {
                    return true;
                }
                // sintassi di base
                if (!text.matches(allowDecimal ? DECIMAL_REGEX : INTEGER_REGEX)) {
                    return false;
                }
                // controllo range
                if (minValue != null || maxValue != null) {
                    String norm = text.replace(',', '.');
                    // intval prefix per interi
                    if (!allowDecimal) {
                        // permetti prefissi del minimo e del massimo
                        if (minStr != null && minStr.startsWith(norm)) return true;
                        if (maxStr != null && maxStr.startsWith(norm)) return true;
                        // solo valori completi
                        if (norm.matches(COMPLETE_INTEGER_REGEX)) {
                            double val = Double.parseDouble(norm);
                            if (minValue != null && val < minValue) return false;
                            if (maxValue != null && val > maxValue) return false;
                        }
                    } else {
                        // prefissi per decimali
                        if (minStr != null && minStr.startsWith(norm)) return true;
                        if (maxStr != null && maxStr.startsWith(norm)) return true;
                        // solo valori completi
                        if (norm.matches(COMPLETE_DECIMAL_REGEX)) {
                            double val = Double.parseDouble(norm);
                            if (minValue != null && val < minValue) return false;
                            if (maxValue != null && val > maxValue) return false;
                        }
                    }
                }
                return true;
            }
        };
        ((AbstractDocument) getDocument()).setDocumentFilter(filter);
    }

    public Number getNumericValue() {
        String text = getText().trim();
        if (text.isEmpty() || text.equals("-") || text.equals(".") || text.equals(",")) {
            return 0;
        }
        if (allowDecimal) {
            String norm = text.replace(',', '.');
            try {
                double val = Double.parseDouble(norm);
                if (minValue != null && val < minValue) val = minValue;
                if (maxValue != null && val > maxValue) val = maxValue;
                return val;
            } catch (NumberFormatException e) {
                return 0.0;
            }
        } else {
            try {
                int v = Integer.parseInt(text);
                double val = v;
                if (minValue != null && val < minValue) val = minValue;
                if (maxValue != null && val > maxValue) val = maxValue;
                return (int) val;
            } catch (NumberFormatException e) {
                return 0;
            }
        }
    }
}
