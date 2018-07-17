package com.streamforge.auth.web;

import com.streamforge.realm.twitch.oauth.camel.TwitchHttpQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.net.URISyntaxException;

@Controller
public class AuthController {

    @Autowired
    private TwitchHttpQueryBuilder twitchHttpQueryBuilder;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getInitialPage() {
        return "index.html";
    }

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView authTwitch() throws URISyntaxException {
        return new ModelAndView("redirect:" + twitchHttpQueryBuilder.createAuthURI());
    }


}
