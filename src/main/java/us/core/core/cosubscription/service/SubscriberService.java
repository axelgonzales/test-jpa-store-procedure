package us.core.core.cosubscription.service;

import us.core.core.cosubscription.controller.request.SearchFilter;
import us.core.core.cosubscription.controller.request.SubscriberRequest;
import us.core.core.cosubscription.controller.response.SubscriberResponse;

public interface SubscriberService {

	public SubscriberResponse searchFilter(SearchFilter search);

	public SubscriberResponse findById(Integer subscriberId, String message);

	public SubscriberResponse saveSubscriber(SubscriberRequest subscriberRequest);

	public SubscriberResponse updateSubscriber(SubscriberRequest subscriberRequest, Integer id);

}
