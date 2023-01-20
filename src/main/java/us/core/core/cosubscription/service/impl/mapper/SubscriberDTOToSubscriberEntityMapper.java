package us.core.core.cosubscription.service.impl.mapper;

import org.modelmapper.ModelMapper;

import us.core.core.cosubscription.controller.request.SubscriberRequest;
import us.core.core.cosubscription.controller.response.SubscriberResponse;
import us.core.core.cosubscription.domain.SubscriberEntity;
import us.core.core.cosubscription.util.UtilEncrypt;

public class SubscriberDTOToSubscriberEntityMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public SubscriberEntity subscriberDTOToSubscriberEntityMapper(SubscriberRequest subscriberRequest) {
    	SubscriberEntity  entity =  modelMapper.map(subscriberRequest, SubscriberEntity.class);
    	
    	entity.setPassword(UtilEncrypt.encrypt(subscriberRequest.getPassword()));
    	return entity;
    }
    
    public SubscriberResponse subscriberEntityToSubscriberResponse(SubscriberEntity subscriberEntity, String message) {
    	SubscriberRequest  request = modelMapper.map(subscriberEntity, SubscriberRequest.class);
    	request.setPassword(UtilEncrypt.decrypted(subscriberEntity.getPassword()));
    	SubscriberResponse subscriberResponse = new SubscriberResponse();
    	subscriberResponse.setMessage(message);
    	subscriberResponse.setSuscriber(request);
    	return subscriberResponse;
    }
    
    public SubscriberResponse subscriberRequestoSubscriberResponse(SubscriberRequest request, String message) {
    	SubscriberResponse subscriberResponse = new SubscriberResponse();
    	subscriberResponse.setMessage(message);
    	subscriberResponse.setSuscriber(request);
    	return subscriberResponse;
    }
    
    

}