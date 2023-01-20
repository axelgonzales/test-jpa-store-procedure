package us.core.core.cosubscription.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import javax.validation.constraints.NotEmpty;


@SqlResultSetMapping(
	    name = "subscriberMapping",
	    entities = {
	        @EntityResult(
	            entityClass = SubscriberEntity.class,
	            fields = {
	                @FieldResult(name = "idSuscriptor", column = "IdSuscriptor"),
	                @FieldResult(name = "nombre", column = "Nombre"),
	                @FieldResult(name = "apellido", column = "Apellido"),
	                @FieldResult(name = "numDocumento", column = "NumeroDocumento"),
	                @FieldResult(name = "tipoDocumento", column = "TipoDocumento"),
	                @FieldResult(name = "direccion", column = "Direccion"),
	                @FieldResult(name = "telefono", column = "Telefono"),
	                @FieldResult(name = "email", column = "Email") ,
	                @FieldResult(name = "nombreUsuario", column = "NombreUsuario") ,
	                @FieldResult(name = "password", column = "Password") 
	               
	            }
	        )
	    }
	)

	@NamedStoredProcedureQuery(
	    name = "registerSubscriber",
	    procedureName = "register_subscriber",
	    parameters = {
	        @StoredProcedureParameter(name = "nombre", type = String.class, mode = ParameterMode.IN),
	        @StoredProcedureParameter(name = "apellido", type = String.class, mode = ParameterMode.IN),
	        @StoredProcedureParameter(name = "numeroDocumento", type = String.class, mode = ParameterMode.IN),
	        @StoredProcedureParameter(name = "tipoDocumento", type = String.class, mode = ParameterMode.IN),
	        @StoredProcedureParameter(name = "direccion", type = String.class, mode = ParameterMode.IN),
	        @StoredProcedureParameter(name = "telefono", type = String.class, mode = ParameterMode.IN),
	        @StoredProcedureParameter(name = "email", type = String.class, mode = ParameterMode.IN),
	        @StoredProcedureParameter(name = "nombreUsuario", type = String.class, mode = ParameterMode.IN),
	        @StoredProcedureParameter(name = "password", type = String.class, mode = ParameterMode.IN),
	        @StoredProcedureParameter(name = "LastInsertedId", type = Integer.class, mode = ParameterMode.OUT)
	    }
	)

	@NamedStoredProcedureQuery(
	    name = "updateSubscriber",
	    procedureName = "update_subscriber",
	    parameters = {
    		@StoredProcedureParameter(name = "idSuscriptor", type = Integer.class, mode = ParameterMode.IN),
	        @StoredProcedureParameter(name = "nombre", type = String.class, mode = ParameterMode.IN),
	        @StoredProcedureParameter(name = "apellido", type = String.class, mode = ParameterMode.IN),
	        @StoredProcedureParameter(name = "direccion", type = String.class, mode = ParameterMode.IN),
	        @StoredProcedureParameter(name = "telefono", type = String.class, mode = ParameterMode.IN),
	        @StoredProcedureParameter(name = "email", type = String.class, mode = ParameterMode.IN),
	        @StoredProcedureParameter(name = "password", type = String.class, mode = ParameterMode.IN)
	    }
	)


	@NamedStoredProcedureQuery(
	    name = "getSubscriberFindById",
	    procedureName = "get_subscriber_findById",
	    resultSetMappings = "subscriberMapping",
	    parameters = {
	        @StoredProcedureParameter(name = "IdSuscriptor", type = Integer.class, mode = ParameterMode.IN),
	    }
	)

	@NamedStoredProcedureQuery(
	    name = "getSubscriber",
	    procedureName = "get_subscriber_for_document",
	    resultSetMappings = "subscriberMapping",
	    parameters = {
	        @StoredProcedureParameter(name = "tipoDocumento", type = String.class, mode = ParameterMode.IN),
	        @StoredProcedureParameter(name = "numDocumento", type = String.class, mode = ParameterMode.IN)
	    }
	)


@Entity
@Table(name="Suscriptor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "IdSuscriptor")
    private Integer idSuscriptor;

    @Column(nullable = false, name = "Nombre")
    @NotEmpty(message = "Text cannot be empty")
    private String nombre;
    
    @Column(nullable = false, name = "Apellido")
    @NotEmpty(message = "Text cannot be empty")
    private String apellido;
    
    @Column(nullable = false, name = "NumeroDocumento")
    @NotEmpty(message = "Text cannot be empty")
    private String numDocumento;
    
    @Column(nullable = false, name = "TipoDocumento")
    @NotEmpty(message = "Text cannot be empty")
    private String tipoDocumento;
    
    @Column(nullable = false, name = "Direccion")
    @NotEmpty(message = "Text cannot be empty")
    private String direccion;
    
    @Column(nullable = false, name = "Telefono")
    @NotEmpty(message = "Text cannot be empty")
    private String telefono;
    
    @Column(nullable = false, name = "Email")
    @NotEmpty(message = "Text cannot be empty")
    private String email;
    
    @Column(nullable = false, name = "NombreUsuario", unique = true)
    @NotEmpty(message = "Text cannot be empty")
    private String nombreUsuario;
    
    @Column(nullable = false, name = "Password")
    @NotEmpty(message = "Text cannot be empty")
    private String password;
}
