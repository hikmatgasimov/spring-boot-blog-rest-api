package com.springboot.blog.config;

import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.web.configuration.EnableWebSucurity;

@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true) //protect crud operations
public class Securityconfig  { // extends WebSecurityConfigurerAdapter
   /*
   @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
*/
    /*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }*/
   /*  @Override
   @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails gasimov = User.builder().username("gasimov").password(passwordEncoder()
                .encode("password")).roles("USER").build();
        UserDetails admin = User.builder().username("admin").password(passwordEncoder()
                .encode("admin")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(gasimov, admin);
    }*/

}
