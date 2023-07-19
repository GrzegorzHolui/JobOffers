package com.domain.joboffers.offerfacade;

import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class OfferValidator {

    List<ValidatorMessage> validateData(Offer offer) {
        List<ValidatorMessage> resultMessages = new ArrayList<>();
        List<ValidatorMessage> validateEarningsMessage = validateEarnings(offer.earnings());
        boolean validateIsLinkValid = validateIsLinkValid(offer.linkToOffer());
        if (!validateIsLinkValid) {
            resultMessages.add(ValidatorMessage.LINK_IS_INVALID);
        }
        resultMessages.addAll(validateEarningsMessage);
        return resultMessages;
    }

    private boolean validateIsLinkValid(String linkToOffer) {
        try {
            URL url = new URL(linkToOffer);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            return (responseCode == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            return false;
        }
    }

    private List<ValidatorMessage> validateEarnings(Earnings earnings) {
        List<ValidatorMessage> result = new ArrayList<>();
        BigDecimal minSalary = earnings.minSalary();
        BigDecimal maxSalary = earnings.maxSalary();

        if (minSalary.compareTo(BigDecimal.ZERO) < 0 || maxSalary.compareTo(BigDecimal.ZERO) < 0) {
            result.add(ValidatorMessage.MIN_SALARY_OR_MAX_SALARY_UNDER_ZERO);
        }

        if (minSalary.compareTo(maxSalary) > 0) {
            result.add(ValidatorMessage.MIN_SALARY_HAS_BIGGER_VALUE_THAN_MAX_SALARY);
        }

        return result;
    }
}
