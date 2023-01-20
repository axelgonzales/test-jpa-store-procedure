package us.core.core.cosubscription.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriberRequest {
	
	private Integer idSuscriptor;
	
	private String nombre;
   
    private String apellido;

    private String numDocumento;

    private String tipoDocumento;

    private String direccion;

    private String telefono;

    private String email;

    private String nombreUsuario;

    private String password;
}
