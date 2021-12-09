package uk.ac.reigate.predictedgrades

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@SpringBootApplication
@EntityScan( basePackages = "uk.ac.reigate.domain.predictedgrades" )
@EnableJpaRepositories(basePackages = "uk.ac.reigate.predictedgrades.repository")
@EnableWebMvc
class PredictedGradeApplication {

	static void main(String[] args) {
		SpringApplication.run(PredictedGradeApplication, args)
	}

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                    .addMapping("/**")
					.allowCredentials(true)
                    .allowedOrigins("*")
                    .allowedMethods("*");
            }
        };
    }

}
