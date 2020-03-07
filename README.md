
* level 0 : projet SpringBoot 2.2.5 - java 8 - un seul endpoint /ok   avec le test associé
* level 1 : ajout de la dépendance et de la configuration swagger 2.9.2
* level 2 : création de l'API Team. Le controller, les endpoints, l'application service, le domain service, les entity.
            pas de repository, le domain service garde tout en mémoire dans cette version. Les tests unitaires couvrent le
            controller et le domain service.
* level 3 : introduction de la notion de repository. J'utilise une base H2 et SpringJDBC. La base est crée et peuplée via les fichiers schema.sql et data.sql
* level 4 : on passe en JPA , cela simplifie un peu les repository mais les tests doivent être annotés @Transactional pour fonctionner

