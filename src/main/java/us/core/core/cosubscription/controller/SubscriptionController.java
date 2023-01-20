package us.core.core.cosubscription.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import us.core.core.cosubscription.constant.Constant;
import us.core.core.cosubscription.controller.request.SubscriptionRequest;
import us.core.core.cosubscription.controller.response.SubscriptionResponse;
import us.core.core.cosubscription.domain.SubscriptionEntity;
import us.core.core.cosubscription.exception.ExceptionResponse;
import us.core.core.cosubscription.exception.ModelNotFoundException;
import us.core.core.cosubscription.service.SubscriberService;
import us.core.core.cosubscription.service.SubscriptionService;

@RestController
@RequestMapping("v1/subscription")
@Api(value = "SubscriptionController", produces = "application/json", tags = { "Controlador Subscription" })
public class SubscriptionController {

	@Autowired
    private  SubscriptionService subscriptionService;

	@Autowired
	private SubscriberService subscriberService;


    @GetMapping("/subscriber/{IdSuscriptor}")
    public ResponseEntity<SubscriptionEntity> searchSubscriptions(@PathVariable Integer IdSuscriptor) {
    	SubscriptionEntity subscriptionEntity = subscriptionService.searchSubscriptions(IdSuscriptor);
        if (subscriptionEntity == null) {
        	throw new ModelNotFoundException(Constant.SUSCRIPCION_NOT_FOUND);
		} 
        return new ResponseEntity<>(subscriptionEntity,  HttpStatus.OK);
    }

    @ApiOperation(value = "Registra Subscription", tags = { "Controlador Subscription" })
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Subscription registrada", response = SubscriptionRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<SubscriptionResponse> createSubscription(@RequestBody @Validated SubscriptionRequest subscriptionRequest) {
    	if (subscriberService.findById(subscriptionRequest.getIdSuscriptor(), "ENCONTRADO") == null) {
    		throw new ModelNotFoundException(Constant.SUSCRIPTOR_NOT_FOUND);
		};
        return new ResponseEntity<>(subscriptionService.saveSubscription(subscriptionRequest),  HttpStatus.CREATED);
    }
}
