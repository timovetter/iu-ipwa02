package entities;

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
    public GhostNet(float longitude, float latitude, GhostNetStatus status, int size) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.setStatus(status);
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public GhostNetStatus getStatus() {
        return status;
    }

    public int getSize() {
        return size;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStatus(GhostNetStatus status) {
        this.status = status;
    }
}
