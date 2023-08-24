import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GhostNet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private float longitude;
    private float latitude;
    private GhostNetStatus status;
    private int size;

    public GhostNet() {}

}
