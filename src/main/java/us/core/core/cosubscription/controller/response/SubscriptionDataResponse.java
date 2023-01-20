package us.core.core.cosubscription.controller.response;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class SubscriptionDataResponse {

	private Integer idAsociacion;

    private Integer idSuscriptor;
    
    private Timestamp fechaAlta;
    
    private Timestamp fechaFin;

    private String motivoFin;
}
