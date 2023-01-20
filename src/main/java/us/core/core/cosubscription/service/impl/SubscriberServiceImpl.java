package us.core.core.cosubscription.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import us.core.core.cosubscription.constant.Constant;
import us.core.core.cosubscription.controller.request.SearchFilter;
import us.core.core.cosubscription.controller.request.SubscriberRequest;
import us.core.core.cosubscription.controller.response.SubscriberResponse;
import us.core.core.cosubscription.domain.SubscriberEntity;
import us.core.core.cosubscription.exception.BadRequestException;
import us.core.core.cosubscription.exception.ModelNotFoundException;
import us.core.core.cosubscription.service.SubscriberService;
import us.core.core.cosubscription.service.impl.mapper.SubscriberDTOToSubscriberEntityMapper;
import us.core.core.cosubscription.util.UtilEncrypt;

@Slf4j
@Service
@Transactional
public class SubscriberServiceImpl implements SubscriberService {

	@PersistenceContext
	private EntityManager entityManager;
	
	private SubscriberDTOToSubscriberEntityMapper mapper = new SubscriberDTOToSubscriberEntityMapper();

	public SubscriberResponse searchFilter(SearchFilter search) {
		log.info("GET searchFilter");

		StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("getSubscriber");
		query.setParameter("tipoDocumento", search.getTipoDocumento());
		query.setParameter("numDocumento", search.getNumDocumento());
		List<SubscriberEntity> result = query.getResultList();
		if (result.isEmpty()) {
			throw new ModelNotFoundException(Constant.SUSCRIPTOR_NOT_FOUND);
		}
		return mapper.subscriberEntityToSubscriberResponse(result.get(0), "USUARIO ENCOENTRADO");

	}

	public SubscriberResponse saveSubscriber(SubscriberRequest subscriberRequest) {
		try {
			log.info("POST saveSubscriber");
			StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("registerSubscriber");
			storedProcedure.setParameter("tipoDocumento", subscriberRequest.getTipoDocumento());
			storedProcedure.setParameter("numeroDocumento", subscriberRequest.getNumDocumento());
			storedProcedure.setParameter("nombre", subscriberRequest.getNombre());
			storedProcedure.setParameter("apellido", subscriberRequest.getApellido());
			storedProcedure.setParameter("direccion", subscriberRequest.getDireccion());
			storedProcedure.setParameter("telefono", subscriberRequest.getTelefono());
			storedProcedure.setParameter("email", subscriberRequest.getEmail());
			storedProcedure.setParameter("nombreUsuario", subscriberRequest.getNombreUsuario());
			storedProcedure.setParameter("password", UtilEncrypt.encrypt(subscriberRequest.getPassword()));

			storedProcedure.execute();

			Integer lastInsertedId = (Integer) storedProcedure.getOutputParameterValue("LastInsertedId");

			return findById(lastInsertedId, Constant.REG_INS_ACCEPTED);
		} catch (Exception e) {
			log.error("Error: The INSERT statement conflicted with the UNIQUE constraint", e);
			throw new BadRequestException(Constant.REG_INS_NOT_ACCEPTED_UNQ);
		}
	}

	public SubscriberResponse updateSubscriber(SubscriberRequest subscriberRequest, Integer id) {
		log.info("PUT updateSubscriber");
		if (findById(id, Constant.REG_ACT_ACCEPTED) == null) {
			throw new ModelNotFoundException(Constant.SUSCRIPTOR_NOT_FOUND);
		};
		
		StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("updateSubscriber");
		query.setParameter("idSuscriptor", id);
		query.setParameter("nombre", subscriberRequest.getNombre());
		query.setParameter("apellido", subscriberRequest.getApellido());
		query.setParameter("direccion", subscriberRequest.getDireccion());
		query.setParameter("telefono", subscriberRequest.getTelefono());
		query.setParameter("email", subscriberRequest.getEmail());
		query.setParameter("password", UtilEncrypt.encrypt(subscriberRequest.getPassword()));

		query.executeUpdate();
		entityManager.clear();
		return findById(id, Constant.REG_ACT_ACCEPTED);
	}

	@Override
	public SubscriberResponse findById(Integer subscriberId , String message) {
		log.info("GET findById");
		StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("getSubscriberFindById");
		query.setParameter("IdSuscriptor", subscriberId);
		List<SubscriberEntity> result = query.getResultList();
		log.info("POST saveSubscription");
		if (result.isEmpty()) {
			return null;
		}
		return mapper.subscriberEntityToSubscriberResponse(result.get(0), message);
	}

}