package fa.training.mockproject.mockprojectfjb05group01.controller.auth;

import fa.training.mockproject.mockprojectfjb05group01.configuration.security.CustomClientDetails;
import fa.training.mockproject.mockprojectfjb05group01.configuration.jwt.JwtTokenProvider;
import fa.training.mockproject.mockprojectfjb05group01.dto.request.LoginRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class AuthLoginController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    @Autowired
    public AuthLoginController(AuthenticationManager authenticationManager,
                               JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @GetMapping("")
    public ModelAndView showLoginPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView modelAndView = new ModelAndView("client/login");

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            modelAndView.addObject("login", new LoginRequestDTO());
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/main");
        return modelAndView;
    }
    @PostMapping("")
    public ModelAndView loginUser(@AuthenticationPrincipal HttpServletResponse httpServletResponse,
                                  @Valid @ModelAttribute("login") LoginRequestDTO loginDto,
                                  BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();

        if (result.hasErrors()) {
            modelAndView.addObject("login", loginDto);
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            CustomClientDetails customClientDetails = (CustomClientDetails) authentication.getPrincipal();
            //sinh ra jwt tra ve cho client
            String jwt = tokenProvider.generateToken(customClientDetails);
            Cookie cookie = new Cookie("jwtToken", jwt);
            cookie.setMaxAge(86400);
            cookie.setPath("/");
            httpServletResponse.addCookie(cookie);
            modelAndView.setViewName("redirect:/main");
        }catch (Exception e) {
            return new ModelAndView("redirect:/login?error");
        }

        return modelAndView;
    }
}
