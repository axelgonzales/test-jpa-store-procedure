package us.core.core.cosubscription.service.impl.mapper;

import org.modelmapper.ModelMapper;

import us.core.core.cosubscription.controller.request.SubscriptionRequest;
import us.core.core.cosubscription.controller.response.SubscriptionDataResponse;
import us.core.core.cosubscription.controller.response.SubscriptionResponse;
import us.core.core.cosubscription.domain.SubscriptionEntity;

public class SubscriptionDTOToSubscriptionEntityMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public SubscriptionEntity subscriptionDTOToSubscriptionEntityMapper(SubscriptionRequest subscriptionRequest) {
        return modelMapper.map(subscriptionRequest, SubscriptionEntity.class);
    }
    
    public SubscriptionResponse subscriptionEntityToSubscriptionResponseData(SubscriptionEntity entity, String message) {
    	SubscriptionDataResponse subscriptionDataResponse =  modelMapper.map(entity, SubscriptionDataResponse.class);
    	SubscriptionResponse subscriptionResponse = new SubscriptionResponse();
    	subscriptionResponse.setSuscription(subscriptionDataResponse);
    	subscriptionResponse.setMessage(message);
    	return subscriptionResponse;
    }
    
 
}