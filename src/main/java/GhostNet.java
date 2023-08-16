import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class GhostNet {
    private float[] location;
    private GhostNetStatus status;
    private int size;

}
enum GhostNetStatus {
    REPORTED,
    PENDING_RESCUE,
    RESCUED,
    LOST
}
