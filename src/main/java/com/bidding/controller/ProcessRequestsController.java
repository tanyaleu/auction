package com.bidding.controller;

import com.bidding.model.Request;
import com.bidding.repository.RequestRepository;
import com.bidding.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Iterator;
import java.util.List;


@Controller
public class ProcessRequestsController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private RequestRepository requestRepository;


    @GetMapping("/requests/process")
    public String processRequests(Model model) {
        List<Request> sellRequests = requestRepository.findByType(Request.SELL);
        List<Request> allBuyRequests = requestRepository.findByType(Request.BUY);
        System.out.println("sell Requests before end of day:" + sellRequests.size());
        System.out.println("buy Requests before end of day:" + allBuyRequests.size());

        for (Iterator<Request> sellRequestIterator = sellRequests.iterator(); sellRequestIterator.hasNext(); ) {
            Request sellRequest = sellRequestIterator.next();
            List<Request> buyRequests = requestRepository.findQuery(sellRequest.getProduct(), sellRequest.getPrice(), sellRequest.getItemsCount(), Request.BUY);


            if (buyRequests.size() > 0) {
                Request buyRequest = buyRequests.get(0);
                sellRequest.getRequester().takeResult(sellRequest);
                buyRequest.getRequester().takeResult(buyRequest);

                requestRepository.delete(buyRequest);
                requestRepository.delete(sellRequest);
            }
            System.out.println("sell Requests after end of day:" + sellRequests.size());
            System.out.println("buy Requests after end of day:" + buyRequests.size());
        }
        model.addAttribute("requests", requestRepository.findAll());
        return "requestslist";
    }


}
