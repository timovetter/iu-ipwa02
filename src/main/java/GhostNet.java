import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class GhostNet {
    private Coordinate location;
    private GhostNetStatus status;
    private int size;



}
