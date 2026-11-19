package com.tesla.platform.u20241f946.shared.domain.model.aggregates;

import org.springframework.data.domain.AbstractAggregateRoot;

/** Provides a common base for domain aggregate roots. */
public abstract class AbstractDomainAggregateRoot<T extends AbstractDomainAggregateRoot<T>>
        extends AbstractAggregateRoot<T> {}
