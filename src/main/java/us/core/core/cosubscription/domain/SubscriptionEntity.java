package us.core.core.cosubscription.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SqlResultSetMapping(
    name = "subscriptionMapping",
    entities = {
        @EntityResult(
            entityClass = SubscriptionEntity.class,
            fields = {
                @FieldResult(name = "idAsociacion", column = "IdAsociacion"),
                @FieldResult(name = "idSuscriptor", column = "IdSuscriptor"),
                @FieldResult(name = "fechaAlta", column = "FechaAlta"),
                @FieldResult(name = "fechaFin", column = "FechaFin"),
                @FieldResult(name = "motivoFin", column = "MotivoFin")
            }
        )
    }
)

@NamedStoredProcedureQuery(
    name = "getSubscriptionForSuscriber",
    procedureName = "get_subscription_findByIdSuscriptor",
    resultSetMappings = "subscriptionMapping",
    parameters = {
        @StoredProcedureParameter(name = "IdSuscriptor", type = Integer.class, mode = ParameterMode.IN),
    }
)

@NamedStoredProcedureQuery(
	    name = "getSubscriptionFindById",
	    procedureName = "get_subscription_findById",
	    resultSetMappings = "subscriptionMapping",
	    parameters = {
	        @StoredProcedureParameter(name = "IdAsociacion", type = Integer.class, mode = ParameterMode.IN),
	    }
	)

@NamedStoredProcedureQuery(
	    name = "registerSuscripcion",
	    procedureName = "register_suscripcion",
	    parameters = {
	        @StoredProcedureParameter(name = "IdSuscriptor", type = Integer.class, mode = ParameterMode.IN)	 ,
	        @StoredProcedureParameter(name = "LastInsertedId", type = Integer.class, mode = ParameterMode.OUT)	 

	        }
	)

@Entity
@Table(name="Suscripcion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SubscriptionEntity {

    @Id
    @Column(nullable = false, name = "IdAsociacion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAsociacion;

    @Column(nullable = false, name = "IdSuscriptor")
    private Integer idSuscriptor;
    
    @Column(nullable = false, name = "FechaAlta")
    private Timestamp fechaAlta;
    
    @Column(nullable = false, name = "FechaFin")
    private Timestamp fechaFin;
    
    @Column(nullable = false, name = "MotivoFin")
    @NotEmpty(message = "Text cannot be empty")
    private String motivoFin;
}
