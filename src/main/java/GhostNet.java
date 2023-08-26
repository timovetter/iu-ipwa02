import javax.persistence.*;

@Entity
public class GhostNet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private float longitude;
    private float latitude;
    private GhostNetStatus status;
    private int size;

    @ManyToOne
    private User user;

    public GhostNet() {}

}
