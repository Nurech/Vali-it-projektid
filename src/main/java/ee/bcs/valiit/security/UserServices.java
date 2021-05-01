package ee.bcs.valiit.security;

import ee.bcs.valiit.exeptionhandler.ApplicationExpetion;
import ee.bcs.valiit.exeptionhandler.ErrorHandler;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserServices extends ErrorHandler {

    @Autowired
    private UserREPO userREPO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger LOG = LoggerFactory.getLogger(UserServices.class);

    //check password hash from DB
    public String LoginReq(LoginRequestDAO loginRequestDAO) {

        String loginUsername = loginRequestDAO.getUsername();
        String loginPassword = loginRequestDAO.getPassword();
        String dbUsername = userREPO.getOne(loginRequestDAO.getUsername()).getUsername();
        String dbPassword = userREPO.getOne(loginRequestDAO.getUsername()).getPassword();
        LOG.info(loginPassword);

        if (loginUsername.equals(dbUsername) && passwordEncoder.matches(loginRequestDAO.getPassword(), dbPassword)) {
            Date today = new Date();
            Date tokenExpirationDate = new Date(today.getTime() + 1000 * 60 * 60);
            JwtBuilder jwtBuilder = Jwts.builder()
                    .setExpiration(tokenExpirationDate)
                    .setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "c2VjcmV0")
                    .claim("username", loginRequestDAO.getUsername());
            LOG.info("User " + loginRequestDAO.getUsername() + " logged in successfully");
            return jwtBuilder.compact();
        } else if (loginUsername.equals(dbUsername)) {
            LOG.info("User " + loginRequestDAO.getUsername() + " entered wrong password");
            throw new ApplicationExpetion("wrong password");
        }
        else {
            LOG.info("User " + loginRequestDAO.getUsername() + " entered nothing and sent empty request");
            throw new ApplicationExpetion("Please enter anything");
        }
    }

    //register new user in DB when username available
    public String createUser(CreateUserDAO createUserDAO) {

        //Optional object may or may not contain requested data use dbUserName.get to get all
        Optional<User> dbUsername = userREPO.findById(createUserDAO.getUsername());
        if (dbUsername.isPresent()) {
            LOG.info("User " + createUserDAO.getUsername() + " already exists in db");
            throw new ApplicationExpetion("Account already exists in db");
        }
        String encodedPassword = passwordEncoder.encode(createUserDAO.getPassword());
        User newUser = new User();
        newUser.setUsername(createUserDAO.getUsername());
        newUser.setPassword(encodedPassword);
        userREPO.save(newUser);
        LOG.info("User " + createUserDAO.getUsername() + " created!");
        return "user created!";
    }

}

