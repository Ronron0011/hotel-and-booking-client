package fa.training.mockproject.mockprojectfjb05group01.controller.auth;

import fa.training.mockproject.mockprojectfjb05group01.dto.client.ClientDTO;
import fa.training.mockproject.mockprojectfjb05group01.dto.client.RoleDTO;
import fa.training.mockproject.mockprojectfjb05group01.dto.request.LoginRequestDTO;
import fa.training.mockproject.mockprojectfjb05group01.dto.request.RegisterRequestDTO;
import fa.training.mockproject.mockprojectfjb05group01.entity.client.Client;
import fa.training.mockproject.mockprojectfjb05group01.model.enums.RoleType;
import fa.training.mockproject.mockprojectfjb05group01.service.client.ClientService;
import fa.training.mockproject.mockprojectfjb05group01.service.client.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/register")
public class AuthRegisterController {
    private final ClientService clientService;
    private final RoleService roleService;
    private final PasswordEncoder encoder;
    @Autowired
    public AuthRegisterController(ClientService clientService,
                                  RoleService roleService,
                                  PasswordEncoder encoder) {
        this.clientService = clientService;
        this.roleService = roleService;
        this.encoder = encoder;
    }

    @GetMapping("")
    public ModelAndView showRegisterPage() {
        ModelAndView modelAndView = new ModelAndView("client/register");
        modelAndView.addObject("register", new RegisterRequestDTO());
        return modelAndView;
    }

    @PostMapping("")
    public ModelAndView registerClient(@Valid @ModelAttribute("register") RegisterRequestDTO clientData,
                                       BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.addObject("register", clientData);
            return new ModelAndView("client/register");
        }
        if (clientService.existsClientByEmail(clientData.getEmail())) throw new RuntimeException("Error: Email has already existed in the database.");
        if (clientService.existsClientByPhone(clientData.getPhone())) throw new RuntimeException("Error: Phone number has already existed in the database.");
        Pattern pattern = Pattern.compile("^(\\+84|\\+840|84|840)");
        Matcher matcher = pattern.matcher(clientData.getPhone());

        String convertPhoneNumber = matcher.replaceAll("0");
        clientData.setPhone(convertPhoneNumber);
        clientData.setPassword(encoder.encode(clientData.getPassword()));

//        ClientDTO clientDTO = new ClientDTO();
//        clientDTO.setFirstName(clientData.getFirstName());
//        clientDTO.setLastName(clientData.getLastName());
//        clientDTO.setEmail(clientData.getEmail());
//        clientDTO.setPhone(convertPhoneNumber);
//        clientDTO.setPassword());
//
//
//
//        RoleDTO roleData = roleService.findRoleByRoleType(RoleType.ROLE_CLIENT);
//        clientDTO.setRoleId(roleData.getId());
//        clientService.createClient(clientDTO);
        clientService.registerClient(clientData);
        modelAndView.addObject("success","You have registered successfully.");
//        modelAndView.addObject("login", new LoginRequestDTO());
        modelAndView.setViewName("redirect:/login");
        return modelAndView;
    }
}
