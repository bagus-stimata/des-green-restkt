package com.erp.distribution.desgreenrestkt.security_config

import com.erp.distribution.desgreenrestkt.AppPublicService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig : WebSecurityConfigurerAdapter(), WebMvcConfigurer {
    // /*
    // Ini akan melakukan permit all kepada semua
    // */
    // // @Override
    // // protected void configure(HttpSecurity http) throws Exception {
    // //     http.authorizeRequests().anyRequest().permitAll();
    // // }
    @Autowired
    private val successHandler: MyAuthenticationSuccessHandler? = null

    @Autowired
    private val accessDeniedHandler: LoggingAccessDeniedHandler? = null

    @Autowired
    private val userDetailsService: AuthUserDetailsService? = null

    // @Autowired
    // public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    // 	auth.inMemoryAuthentication()
    //       .withUser("user")
    //         .password(passwordEncoder().encode("user"))
    //         .roles(Role.USER, "USER")
    //       .and()
    //       .withUser("admin")
    //         .password(passwordEncoder().encode("admin"))
    //         // .roles(Role.ADMIN, Role.USER);
    //         .roles(Role.ADMIN);
    // }
    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }
    //Dengan database cara pertama
    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
//         auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//        auth.userDetailsService(userDetailsService).passwordEncoder(PassEncoding.Companion.getInstance().passwordEncoder())
        auth.userDetailsService(userDetailsService).passwordEncoder(PassEncoding.instance!!.passwordEncoder() )
    }

    // @Bean //tidak perlu di perlukan
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }
    // Dengan database cara kedua
    // @Bean
    // public DaoAuthenticationProvider authenticationProvider() {
    //     DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    //     authenticationProvider.setUserDetailsService(userDetailsService);
    //     authenticationProvider.setPasswordEncoder(passwordEncoder());
    //     return authenticationProvider;
    // }
    // @Autowired
    // public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.userDetailsService(userDetailsService);
    //     auth.authenticationProvider(authenticationProvider());
    // }
    // @Autowired
    // public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.userDetailsService(userDetailsService);
    // }
    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                /**
                 * dengan menggunakan metode/konsep csrf yang digunakan pada controller
                 * akan dapat mengamankan aplikasi. metode ini seperti halnya Outh2 yang dipasang
                 * pada controller
                 *
                 * Khusus untuk vaadin .csrf harus di dsable
                 */
                .csrf().disable()
                .httpBasic()
                .and() //MASIH TANDA TANYA
                // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
                //     authorizeRequests().antMatchers(HttpMethod.GET, "/**").hasAnyRole("ADMIN", "USER")
                //                     .antMatchers(HttpMethod.POST, "/r**").hasAnyRole("ADMIN", "USER")
                //                     .antMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
                //                     .antMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN").and().
                //     requestCache().requestCache(new NullRequestCache())
                // .and()
                // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
                //     authorizeRequests().antMatchers(HttpMethod.GET, "/**").hasAnyRole("ADMIN", "USER")
                //                     .antMatchers(HttpMethod.POST, "/**").hasAnyRole("ADMIN", "USER")
                //                     .antMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
                //                     .antMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN").and().
                //     requestCache().requestCache(new NullRequestCache())
                // .and()
                .authorizeRequests() // .antMatchers( "/**" ).permitAll() // Untuk pMelakukan Permit kepada semua dan tidak perlu otorisasi: Mengacu pada contoh diatas
                // // .antMatchers( "/", "/home", "resources/**", "/registration" ).permitAll() // daftar un-secure page
                .antMatchers(
                        "/home",
                        "/js/**",
                        "/css/**", "/font/**", "/mdb-addons/**", "/scss/**",
                        "/src/**", "/assets/**",
                        "/img/**", "/images/**", "/app-images/**",
                        "/template/**",
                        "/webjars/**", "/registration")
                .permitAll()
                /**
                 * Otomatis dibaca:
                 * ROLE_ADMIN, ROLE_USER, ROLE_ACCOUNTING
                 */
                // .antMatchers("/admin/**").hasRole("ADMIN")
                // .antMatchers("/task/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/anonymous*").anonymous()
                .antMatchers("/login*", "/register/**", "/createSuperUser/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin() //Jika un-autorize akan di redirct ke /login
                .loginPage("/login") // .failureUrl("/login?error=1")
                // .defaultSuccessUrl("/home")
                .successHandler(successHandler) //more spesific using success handler
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
        // .accessDeniedPage("/access-denied"); //this is the same
    }

    /**
     * gambarnya --> /images
     */
    //  @Override
    //  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //      registry.addResourceHandler("/images/**").addResourceLocations("file:" + uploadDirectory+"/");
    //  }
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/images/**", "/css/**", "/img/**", "/js/**") //Ini harus didaftarkan juga pada configurer anymatcher
                .addResourceLocations(
                        "file:$filesDirectory"
                        , "file:$reportsDirectory"
                        , "classpath:/static/home"
                        , "classpath:/static/js/"
                        , "classpath:/static/css/"
                        , "classpath:/static/src/"
                        , "classpath:/static/img/"
                        , "classpath:/static/assets/"
                        , "classpath:/static/template/"
                        , "classpath:/static/webjars/")
    }

    companion object {
        // public static String uploadDirectory= System.getProperty("user.home") + "\\images";
        //  public static String uploadDirectory = "/Users/yhawin/gambarnya";
        var filesDirectory = AppPublicService.FILE_PATH
        var reportsDirectory = AppPublicService.FILE_PATH
    }
}