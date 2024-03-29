package dev.eidentification.bankid.client.model.enums;

public enum CardReader {
    /**
     * The transaction must be performed using a card reader where the PIN code is entered on a computer keyboard, or a card reader of higher class.
     */
    CLASS1("class1"),
    /**
     * The transaction must be performed using a card reader where the PIN code is entered on the reader, or a reader of higher class.
     */
    CLASS2("class2");

    private final String code;

    CardReader(final String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
