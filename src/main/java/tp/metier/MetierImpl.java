package tp.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import tp.dao.IDao;

@Service
@Profile({"dev", "prod"})
public class MetierImpl implements IMetier {
    private final IDao dao;

    // @Autowired est explicite pour le TP ; il est facultatif
    // lorsque la classe possède un seul constructeur.
    @Autowired
    public MetierImpl(IDao dao) {
        this.dao = dao;
    }

    @Override
    public double calcul() {
        return dao.getData() * 2;
    }
}
