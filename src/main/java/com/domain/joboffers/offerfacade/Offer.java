package com.domain.joboffers.offerfacade;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Builder
@Document
public
record Offer(
        @Id
        String id,
        @Field("link") @Indexed(unique = true) String linkToOffer,
        @Field("position") String jobName,
        @Field("company") String nameOfCompany,
        @Field("salary") Earnings earnings) {
}
