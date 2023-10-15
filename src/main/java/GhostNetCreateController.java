import dao.GhostNetDAO;
import entities.GhostNet;
import entities.GhostNetStatus;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@ViewScoped
public class GhostNetCreateController implements Serializable {
    private float longitude;
    private float latitude;
    private int size;

    private final GhostNetDAO ghostNetDAO;

    public GhostNetCreateController() {
        this.ghostNetDAO = new GhostNetDAO();
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void createGhostNet() {
        GhostNet gn = new GhostNet(this.longitude, this.latitude, GhostNetStatus.REPORTED, this.size);
        this.ghostNetDAO.add(gn);
        FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_INFO, "Aktion erfolgreich", "Das Geisternetz wurde erfolgreich erstellt."));
        this.resetForm();
    }

    private void resetForm() {
        this.longitude = 0;
        this.latitude = 0;
        this.size = 1;
    }
}
