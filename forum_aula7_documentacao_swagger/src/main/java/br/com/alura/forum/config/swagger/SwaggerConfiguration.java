package br.com.alura.forum.config.swagger;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.alura.forum.modelo.Usuario;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

	@Bean
	public Docket forumApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.alura.forum"))
				.paths(PathSelectors.ant("/**"))
				.build()
				.ignoredParameterTypes(Usuario.class)
				//Parâmetro global (todos os Endpoints)
				.globalOperationParameters(Arrays.asList(new ParameterBuilder()
						//Nome parâmetro
						.name("Authorization")
						.description("Header para token JWT")
						//Tipo parâmetro
						.modelRef(new ModelRef("string"))
						//Cabeçalho (vai no header)
						.parameterType("header")
						//Não é requerido(nem todos os EndPoints precisam enviar)
						.required(false)
						.build()));
	}

}
