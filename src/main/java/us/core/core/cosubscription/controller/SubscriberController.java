package us.core.core.cosubscription.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import us.core.core.cosubscription.controller.request.SearchFilter;
import us.core.core.cosubscription.controller.request.SubscriberRequest;
import us.core.core.cosubscription.controller.response.SubscriberResponse;
import us.core.core.cosubscription.exception.ExceptionResponse;
import us.core.core.cosubscription.service.SubscriberService;

@RestController
@RequestMapping("v1/subscriber")
@Api(value = "SubscriberController", produces = "application/json", tags = { "Controlador Subscriber" })
public class SubscriberController {

    private final SubscriberService subscriberService;

    @Autowired
    public SubscriberController(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @GetMapping()
    public SubscriberResponse searchFilter(SearchFilter search) {
        return subscriberService.searchFilter(search);
    }

    @ApiOperation(value = "Registra Subscriber", tags = { "Controlador Subscriber" })
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Subscriber registrada", response = SubscriberRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<SubscriberResponse> createSubscriber(@RequestBody @Validated SubscriberRequest subscriberRequest) {
        
        return new ResponseEntity<>(subscriberService.saveSubscriber(subscriberRequest) , HttpStatus.CREATED);
    }

    @ApiOperation(value = "Actualiza Subscriber", tags = { "Controlador Subscriber" })
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Subscriber actualizada", response = SubscriberRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<SubscriberResponse> updateSubscriber(@PathVariable Integer id, @RequestBody SubscriberRequest subscriberRequest) throws Exception {
        return new ResponseEntity<>(subscriberService.updateSubscriber(subscriberRequest, id), HttpStatus.CREATED);
    }

}
