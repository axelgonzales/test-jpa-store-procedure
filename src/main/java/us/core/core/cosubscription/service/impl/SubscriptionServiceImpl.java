package us.core.core.cosubscription.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import us.core.core.cosubscription.constant.Constant;
import us.core.core.cosubscription.controller.request.SubscriptionRequest;
import us.core.core.cosubscription.controller.response.SubscriptionResponse;
import us.core.core.cosubscription.domain.SubscriptionEntity;
import us.core.core.cosubscription.exception.BadRequestException;
import us.core.core.cosubscription.exception.ModelNotFoundException;
import us.core.core.cosubscription.service.SubscriptionService;
import us.core.core.cosubscription.service.impl.mapper.SubscriptionDTOToSubscriptionEntityMapper;

@Slf4j
@Service
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {

	@PersistenceContext
	private EntityManager entityManager;

	private SubscriptionDTOToSubscriptionEntityMapper mapper = new SubscriptionDTOToSubscriptionEntityMapper();

	public SubscriptionEntity searchSubscriptions(Integer idSuscriptor) {

		StoredProcedureQuery storedProcedure = entityManager
				.createNamedStoredProcedureQuery("getSubscriptionForSuscriber");
		storedProcedure.setParameter("IdSuscriptor", idSuscriptor);
		List<SubscriptionEntity> result = storedProcedure.getResultList();
		log.info("GET searchSubscriptions");

		if (result.isEmpty()) {
			return null;
		}
		return result.get(0);
	}

	public SubscriptionResponse saveSubscription(SubscriptionRequest subscriptionRequest) {
		if (searchSubscriptions(subscriptionRequest.getIdSuscriptor()) != null) {
			throw new BadRequestException(Constant.REG_INS_EXIST);
		}
		try {
			StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("registerSuscripcion");
			storedProcedure.setParameter("IdSuscriptor", subscriptionRequest.getIdSuscriptor());
			storedProcedure.execute();
			Integer lastInsertedId = (Integer) storedProcedure.getOutputParameterValue("LastInsertedId");
			StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("getSubscriptionFindById");
			query.setParameter("IdAsociacion", lastInsertedId);
			List<SubscriptionEntity> result = query.getResultList();
			log.info("POST saveSubscription");

			if (result.isEmpty()) {
				throw new ModelNotFoundException(Constant.REG_INS_NOT_ACCEPTED);
			}
			return mapper.subscriptionEntityToSubscriptionResponseData(result.get(0), Constant.REG_INS_ACCEPTED);

		} catch (Exception e) {
			log.error("Error: The INSERT statement conflicted with the FOREIGN KEY constraint", e);
			throw new BadRequestException(Constant.REG_INS_NOT_ACCEPTED_FK);
		}
	}

}