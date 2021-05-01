package ee.bcs.valiit.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServices userServices;

    // LOGIN
    @CrossOrigin
    @PostMapping ("/public/login")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody LoginRequestDAO loginRequestDAO) {
        LOG.info("User tried login form");
        return userServices.LoginReq(loginRequestDAO);
    }


    // REGISTER
    @CrossOrigin
    @PostMapping ("/public/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String login(@RequestBody CreateUserDAO createUserDAO) {
        LOG.info("User tried to register");
        return userServices.createUser(createUserDAO);
    }

}
