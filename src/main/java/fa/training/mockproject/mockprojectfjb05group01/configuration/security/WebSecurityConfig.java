package fa.training.mockproject.mockprojectfjb05group01.configuration.security;

import fa.training.mockproject.mockprojectfjb05group01.configuration.jwt.JwtEntryPoint;
import fa.training.mockproject.mockprojectfjb05group01.configuration.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomClientDetailsService customClientDetailsService;
    private final JwtEntryPoint jwtEntryPoint;

    @Value("${security.ignored-resources}")
    private String ignoredResources;

    public WebSecurityConfig(CustomClientDetailsService customClientDetailsService, JwtEntryPoint jwtEntryPoint) {
        this.customClientDetailsService = customClientDetailsService;
        this.jwtEntryPoint = jwtEntryPoint;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        //lay ra authenticationManager bean
        return  super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        return new InMemoryTokenRepositoryImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //cung cap customUserDetails service cho spring security
        auth.userDetailsService(customClientDetailsService)
                //cung cap passwordEncoder cho spring security
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void  configure(HttpSecurity http) throws Exception {
        http.csrf()//ngan chan request tu mot domain khac
                .and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/*").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/register/**").permitAll()
//                .anyRequest().authenticated()
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .userDetailsService(userDetailsService())
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtEntryPoint) //truong hop co loi xay ra
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        String[] resources = ignoredResources.split(",");
        return (web -> web
                .ignoring()
                .antMatchers(resources));
    }
}
