package com.client.ws.rasmooplus.service.impl;

import com.client.ws.rasmooplus.dto.SubscriptionTypeDto;
import com.client.ws.rasmooplus.exception.BadRequestException;
import com.client.ws.rasmooplus.exception.NotFoundException;
import com.client.ws.rasmooplus.model.SubscriptionType;
import com.client.ws.rasmooplus.repository.SubscriptionTypeRepository;
import com.client.ws.rasmooplus.service.SubscriptionTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SubscriptionTypeServiceImpl implements SubscriptionTypeService {

    private final SubscriptionTypeRepository subscriptionTypeRepository;

    SubscriptionTypeServiceImpl(SubscriptionTypeRepository subscriptionTypeRepository){
        this.subscriptionTypeRepository = subscriptionTypeRepository;
    }
    @Override
    public List<SubscriptionType> findAll() {
        return subscriptionTypeRepository.findAll();
    }

    @Override
    public SubscriptionType findById(Long id) {
        return getSubscriptionType(id);
    }

    @Override
    public SubscriptionType create(SubscriptionTypeDto dto) {
        if (Objects.nonNull(dto.getId())){
            throw new BadRequestException("Id deve ser nulo");
        }
        return subscriptionTypeRepository.save(SubscriptionType.builder()
                        .id(dto.getId())
                        .name(dto.getName())
                        .accessMonth(dto.getAccessMonth())
                        .price(dto.getPrice())
                        .productKey(dto.getProductKey())
                .build());
    }

    @Override
    public SubscriptionType update(Long id, SubscriptionTypeDto dto) {
        getSubscriptionType(id);

        return subscriptionTypeRepository.save(SubscriptionType.builder()
                .id(id)
                .name(dto.getName())
                .accessMonth(dto.getAccessMonth())
                .price(dto.getPrice())
                .productKey(dto.getProductKey())
                .build());
    }

    @Override
    public void delete(Long id) {

    }

    private SubscriptionType getSubscriptionType(Long id) {
        Optional<SubscriptionType> subscriptionType = subscriptionTypeRepository.findById(id);
        if (subscriptionType.isEmpty()){
            throw  new NotFoundException("SubscriptionType não encontrado");
        }
        return subscriptionType.get();
    }

}
