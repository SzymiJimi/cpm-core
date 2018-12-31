package com.thesis.cpmcore.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@EnableSpringConfigured
@EnableGlobalMethodSecurity(prePostEnabled = true)
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"com.thesis.cpmcore"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;

    @Autowired
    private DataSource dataSource;


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/login").allowedOrigins("http://192.168.0.54:4200" ).allowCredentials(true);
                registry.addMapping("/user").allowedOrigins("http://192.168.0.54:4200").allowCredentials(true);
                registry.addMapping("/reservations").allowedOrigins("http://192.168.0.54:4200").allowCredentials(true);
                registry.addMapping("/checkout").allowedOrigins("http://192.168.0.54:4200").allowCredentials(true);
                registry.addMapping("/register/new").allowedOrigins("http://192.168.0.54:4200").allowCredentials(false);
                registry.addMapping("/**").allowedOrigins("http://192.168.0.54:4200").allowCredentials(true);
            }
        };
    }

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcUserDetailsManager manager1 = new JdbcUserDetailsManager();
        manager1.setDataSource(dataSource);
        manager1.setUsersByUsernameQuery("select username, password, true " + "from user where username=? ");
        manager1.setAuthoritiesByUsernameQuery("select user.username, role.name from user, role where user.idRole=role.idRole AND username = ?");
        return manager1;
    }

//   @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl(env.getProperty("spring.datasource.url"));
//        dataSource.setUsername(env.getProperty("spring.datasource.username"));
//        dataSource.setPassword(env.getProperty("spring.datasource.password"));
//        return dataSource;
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/register/new").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, true " + "from user where username=? ")
                .authoritiesByUsernameQuery("select user.username, role.name from user, role where user.idRole=role.idRole AND username = ?")
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}