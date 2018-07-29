package com.streamforge.auth.web.resource;

import com.streamforge.realm.twitch.oauth.builder.TwitchHttpQueryBuilder;
import com.streamforge.realm.twitch.oauth.service.TwitchAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.net.URISyntaxException;

import static com.streamforge.auth.web.AuthControllerDefinitions.*;

@Controller
public class AuthController {

    private final TwitchHttpQueryBuilder twitchHttpQueryBuilder;
    private final TwitchAuthService twitchAuthService;

    @Autowired
    public AuthController(TwitchHttpQueryBuilder twitchHttpQueryBuilder,
                          TwitchAuthService twitchAuthService) {
        this.twitchHttpQueryBuilder = twitchHttpQueryBuilder;
        this.twitchAuthService = twitchAuthService;
    }

    @RequestMapping(value = HOME_PATH, method = RequestMethod.GET)
    public String getInitialPage() {
        return "index.html";
    }

    @RequestMapping(value = AUTH_PATH, method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView authTwitch() throws URISyntaxException {
        return new ModelAndView("redirect:" + twitchHttpQueryBuilder.createAuthURI());
    }

    @RequestMapping(value = INTEGRATION_PATH, method = RequestMethod.GET)
    @ResponseBody
    public String twitchCallback(@RequestParam("code") String code,
                                 @RequestParam("state") String state,
                                 @RequestParam("scope") String scope) {
        return twitchAuthService.processCallback(code, state);
    }
}
