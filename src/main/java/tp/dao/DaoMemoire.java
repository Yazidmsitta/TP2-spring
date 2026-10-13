package tp.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository("daoMemoire")
@Profile({"dev", "qualifier"})
public class DaoMemoire implements IDao {
    @Override
    public double getData() {
        System.out.println("Source : mémoire (développement)");
        return 10.0;
    }
}
