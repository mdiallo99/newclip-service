package com.example.authenticationapp.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import static com.example.authenticationapp.config.SwaggerConfig.BEARER_KEY;
import static com.example.authenticationapp.utils.Constants.*;

@RestController
@RequiredArgsConstructor
public class BackendController {


    /**
     * This controller is made for only Sylob requests
     * It controls for user authentication and redirect the user request toward the ProxyController
     *
     * !!! If the request does not need sylob, we use ProxyController directly
     *
     * @return
     */
    @Operation(security = {@SecurityRequirement(name = BEARER_KEY)})
    @GetMapping(SYLOB_ARTICLES_LIST_ENDPOINT)
    public RedirectView getArticlesList(){
        return new RedirectView(PROXY_SYLOB_ARTICLES_LIST_ENDPOINT);
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY)})
    @GetMapping(COMPANY_LIST_ENDPOINT)
    public RedirectView getCompanyLis(){
        return new RedirectView(PROXY_COMPANY_LIST_ENDPOINT);
    }

    @GetMapping(SYLOB_COMPANY_LIST_ENDPOINT)
    public RedirectView saveCompanyListFromSylob() {

        return new RedirectView(PROXY_SYLOB_COMPANY_LIST_ENDPOINT);
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY)})
    @GetMapping(GET_COMPANY_BY_CODE_ENDPOINT)
    public RedirectView getCompanyByCode(@PathVariable String code){
        return new RedirectView(PROXY_GET_COMPANY_BY_CODE_ENDPOINT);
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY)})
    @GetMapping(GET_COMPANY_BY_SOCIAL_REASON_ENDPOINT)
    @ResponseBody
    public RedirectView getCompanyBySocialReason(@PathVariable String socialReason){
        return new RedirectView(PROXY_GET_COMPANY_BY_SOCIAL_REASON_ENDPOINT);
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY)})
    @GetMapping(SYLOB_KIT_LIST_ENDPOINT)
    @ResponseBody
    public RedirectView getKitListFromSylob(){

        return new RedirectView(PROXY_SYLOB_KIT_LIST_ENDPOINT);
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY)})
    @GetMapping(FIND_KIT_BY_LABEL_ENDPOINT)
    @ResponseBody
    public RedirectView getArticlesFromKit(@PathVariable String label){

        return new RedirectView(PROXY_FIND_KIT_BY_LABEL_ENDPOINT);
    }

}
