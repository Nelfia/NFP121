/**
 * Exception levée lorsqu'un cycle est détecté dans un traitement. Si un traitement se trouve dans ses suivants alors
 * un cycle est détecté et le programme s'arrête.
 */
public class CycleException extends RuntimeException {
    public CycleException(String traitement) {
        super("Cycle détecté dans le traitement: " + traitement);
        System.out.println(this.getMessage());
    }
}
