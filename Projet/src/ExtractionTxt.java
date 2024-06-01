abstract class ExtractionTxt extends ExtractionSourceAbstraite{
    private final String TYPE;

    public ExtractionTxt(String nomDocument, String type) {
        super(nomDocument);
        this.TYPE = type;
    }

    public String getType() {
        return this.TYPE;
    }
}
