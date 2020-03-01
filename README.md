# dojo Level 1

Dans ce niveau j'ajoute une dépendance à swagger et swagger-ui pour exposer la documention des endpoints

Cela se fait en modifier le build.gradle en ajoutant les dépendances suivantes :


```
implementation group: 'io.springfox', name: 'springfox-swagger2', version: '2.9.2'
implementation group: 'io.springfox', name: 'springfox-swagger-ui', version: '2.9.2'
```



Placé dans la classe principale du projet ( celle qui contient main et qui est annotée SpringBootApplication) , ce code :

```
@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.regex("(?!/error).+")).paths(PathSelectors.regex("(?!/actuator).+"))
		.build();
	}
```


permet de générer la documention des endpoints si ils ne commencent pas par /error ou /actuator
Si l'on ajoute d'autres dépendances, vers des outils qui exposent des endpoints, il faut venir compléter cette méthode pour ne pas documenter ces apis.
