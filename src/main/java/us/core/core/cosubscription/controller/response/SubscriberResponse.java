package us.core.core.cosubscription.controller.response;

import lombok.Data;
import us.core.core.cosubscription.controller.request.SubscriberRequest;

@Data
public class SubscriberResponse {
 
	private SubscriberRequest suscriber;
    private String message;

   
}