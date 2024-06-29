/**
 * Exception levée lorsqu'un cycle est détecté dans un traitement.
 * Si un traitement se trouve dans ses suivants (comparaison par référence) alors un cycle est détecté et le programme
 * s'arrête.
 * L'exception est non vérifiée, elle ne nécessite pas d'être récupérée avec un try/catch.
 */
public class CycleException extends RuntimeException {
    public CycleException(String traitement) {
        super("Cycle détecté dans le traitement: " + traitement);
        System.out.println(this.getMessage());
    }
}
