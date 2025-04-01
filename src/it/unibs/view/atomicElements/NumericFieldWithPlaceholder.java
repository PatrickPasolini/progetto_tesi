/**
 * Una JTextField con un placeholder che permette solo l'inserimento di numeri interi.
 * Estende {@link TextFieldWithPlaceholder} e utilizza un {@link DocumentFilter} 
 * per filtrare l'input e consentire solo caratteri numerici.
 */
package it.unibs.view.atomicElements;

import javax.swing.text.*;

public class NumericFieldWithPlaceholder extends TextFieldWithPlaceholder {
    private static final long serialVersionUID = 1L;

    /**
     * Costruttore che inizializza il campo di testo con un placeholder e imposta il filtro numerico.
     * 
     * @param placeholder Il testo del placeholder visualizzato quando il campo è vuoto.
     */
    public NumericFieldWithPlaceholder(String placeholder) {
        super(placeholder);
        installIntegerFilter();
    }

    /**
     * Installa un filtro sul documento per consentire solo l'inserimento di numeri interi.
     */
    private void installIntegerFilter() {
        ((AbstractDocument) getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (isInteger(string)) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (isInteger(text)) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }

            @Override
            public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
                super.remove(fb, offset, length);
            }

            /**
             * Verifica se la stringa fornita contiene solo cifre numeriche.
             *
             * @param text La stringa da verificare.
             * @return {@code true} se la stringa contiene solo cifre numeriche, altrimenti {@code false}.
             */
            private boolean isInteger(String text) {
                return text.matches("\\d*"); // Consente solo numeri interi
            }
        });
    }
    
    /**
     * Restituisce il valore numerico inserito nel campo di testo.
     *
     * @return Il valore intero inserito oppure 0 se il campo è vuoto o non valido.
     */
    public int getNumericValue() {
        String text = getText().trim();
        return text.isEmpty() ? 0 : Integer.parseInt(text);
    }
}
