//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.info.Info;
//import org.springframework.context.annotation.Configuration;
//
//@OpenAPIDefinition(info = @Info(
//        title = "my app"
//))
//public class SwaggerConfig {
//
////    public static final String AUTHORIZATION_HEADER = "Authorization";
////
////    private ApiKey apiKey(){
////        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
////    }
////
////    private ApiInfo apiInfo(){
////        return new ApiInfo(
////                "Spring Boot Blog REST APIs",
////                "Spring Boot Blog REST API Documentation",
////                "1",
////                "Terms of service",
////                new Contact("Ramesh Fadatare", "www.javaguides.net", "ramesh@gmail.com"),
////                "License of API",
////                "API license URL",
////                Collections.emptyList()
////        );
////    }
////
////    @Bean
////    public Docket api(){
////        return new Docket(DocumentationType.SWAGGER_2)
////                .apiInfo(apiInfo())
////                .securityContexts(Arrays.asList(securityContext()))
////                .securitySchemes(Arrays.asList(apiKey()))
////                .select()
////                .apis(RequestHandlerSelectors.any())
////                .paths(PathSelectors.any())
////                .build();
////    }
////
////    private SecurityContext securityContext(){
////        return SecurityContext.builder().securityReferences(defaultAuth()).build();
////    }
////
////    private List<SecurityReference> defaultAuth(){
////        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
////        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
////        authorizationScopes[0] = authorizationScope;
////        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
////    }
//
//}