package tp.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import tp.dao.IDao;

@Service
@Profile("qualifier")
public class MetierQualifie implements IMetier {
    private final IDao dao;

    @Autowired
    public MetierQualifie(@Qualifier("daoApi") IDao dao) {
        this.dao = dao;
    }

    @Override
    public double calcul() {
        return dao.getData() * 2;
    }
}
