import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.List;

@Named
@RequestScoped
public class GhostNetListController {
    private List<GhostNet> ghostNetList;

    public GhostNetListController() {}
}
