package tp.presentation;

import java.util.Arrays;
import java.util.Set;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tp.config.AppConfig;
import tp.dao.IDao;
import tp.metier.IMetier;

public class Application {
    public static void main(String[] args) {
        if (args.length > 1) {
            throw new IllegalArgumentException(
                "Utilisation : Application [dev|prod|qualifier]"
            );
        }

        try (var contexte = new AnnotationConfigApplicationContext()) {
            // Configurer l'environnement avant le scan et la création des beans.
            contexte.getEnvironment().setDefaultProfiles("dev");
            if (args.length == 1) {
                contexte.getEnvironment().setActiveProfiles(args[0]);
            }

            String[] profils = contexte.getEnvironment().getActiveProfiles();
            if (profils.length == 0) {
                profils = contexte.getEnvironment().getDefaultProfiles();
            }
            // Le TP isole les scénarios : un seul profil à la fois.
            if (profils.length != 1
                    || !Set.of("dev", "prod", "qualifier").contains(profils[0])) {
                throw new IllegalArgumentException(
                    "Choisir un seul profil : dev, prod ou qualifier."
                );
            }

            contexte.register(AppConfig.class);
            contexte.refresh();

            System.out.println("Profil : " + profils[0]);
            String[] daos = contexte.getBeanNamesForType(IDao.class);
            Arrays.sort(daos);
            System.out.println("DAO disponibles : " + String.join(", ", daos));

            IMetier metier = contexte.getBean(IMetier.class);
            System.out.println("Service : " + metier.getClass().getSimpleName());
            System.out.println("Résultat : " + metier.calcul());
        }
    }
}
