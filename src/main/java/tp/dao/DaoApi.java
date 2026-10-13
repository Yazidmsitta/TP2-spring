package tp.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository("daoApi")
@Profile({"prod", "qualifier"})
public class DaoApi implements IDao {
    @Override
    public double getData() {
        // Simulation pédagogique : aucun appel HTTP réel.
        System.out.println("Source : API distante simulée");
        return 25.0;
    }
}
