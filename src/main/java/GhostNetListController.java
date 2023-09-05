import dao.GhostNetDAO;
import entities.GhostNet;
import entities.GhostNetStatus;
import entities.User;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class GhostNetListController implements Serializable {
    private final GhostNetDAO ghostNetDAO;

    public GhostNetListController() {
        ghostNetDAO = new GhostNetDAO();
    }

    public List<GhostNet> getGhostNetList() {
        return this.ghostNetDAO.findAll();
    }

    public void addSalvageUser(User user, GhostNet ghostNet) {
        ghostNet.setUser(user);
        ghostNet.setStatus(GhostNetStatus.PENDING_RESCUE);
        this.ghostNetDAO.update(ghostNet);
    }

    public void netRescued(GhostNet ghostNet) {
        ghostNet.setStatus(GhostNetStatus.RESCUED);
        this.ghostNetDAO.update(ghostNet);
    }
}
