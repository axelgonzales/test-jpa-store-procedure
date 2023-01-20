package us.core.core.cosubscription.service;

import us.core.core.cosubscription.controller.request.SubscriptionRequest;
import us.core.core.cosubscription.controller.response.SubscriptionResponse;
import us.core.core.cosubscription.domain.SubscriptionEntity;

public interface SubscriptionService {

    public SubscriptionEntity  searchSubscriptions(Integer IdSuscriptor);

    public SubscriptionResponse saveSubscription(SubscriptionRequest subscriptionRequest);
    
}
