package tp;

import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tp.config.AppConfig;
import tp.dao.IDao;
import tp.metier.IMetier;
import tp.metier.MetierImpl;
import tp.metier.MetierQualifie;
import tp.presentation.Application;

import static org.junit.jupiter.api.Assertions.*;

class SpringInjectionTest {
    private AnnotationConfigApplicationContext ouvrir(String profil) {
        var contexte = new AnnotationConfigApplicationContext();
        contexte.getEnvironment().setDefaultProfiles("dev");
        if (profil != null) {
            contexte.getEnvironment().setActiveProfiles(profil);
        }
        contexte.register(AppConfig.class);
        try {
            contexte.refresh();
            return contexte;
        } catch (RuntimeException e) {
            contexte.close();
            throw e;
        }
    }

    @Test
    void devInjecteUniquementLeDaoMemoire() {
        try (var contexte = ouvrir("dev")) {
            assertEquals(Set.of("daoMemoire"),
                contexte.getBeansOfType(IDao.class).keySet());
            assertInstanceOf(MetierImpl.class, contexte.getBean(IMetier.class));
            assertEquals(20.0, contexte.getBean(IMetier.class).calcul(), 0.0001);
        }
    }

    @Test
    void prodInjecteUniquementLeDaoApi() {
        try (var contexte = ouvrir("prod")) {
            assertEquals(Set.of("daoApi"),
                contexte.getBeansOfType(IDao.class).keySet());
            assertInstanceOf(MetierImpl.class, contexte.getBean(IMetier.class));
            assertEquals(50.0, contexte.getBean(IMetier.class).calcul(), 0.0001);
        }
    }

    @Test
    void qualifierChoisitApiParmiDeuxDaos() {
        try (var contexte = ouvrir("qualifier")) {
            assertEquals(Set.of("daoMemoire", "daoApi"),
                contexte.getBeansOfType(IDao.class).keySet());
            assertInstanceOf(MetierQualifie.class, contexte.getBean(IMetier.class));
            assertEquals(50.0, contexte.getBean(IMetier.class).calcul(), 0.0001);
        }
    }

    @Test
    void profilParDefautUtiliseDev() {
        try (var contexte = ouvrir(null)) {
            assertEquals(Set.of("daoMemoire"),
                contexte.getBeansOfType(IDao.class).keySet());
            assertEquals(20.0, contexte.getBean(IMetier.class).calcul(), 0.0001);
        }
    }

    @Test
    void profilInconnuEstRejete() {
        assertThrows(IllegalArgumentException.class,
            () -> Application.main(new String[]{"inconnu"}));
    }
}
