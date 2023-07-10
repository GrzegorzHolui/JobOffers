package com.domain.joboffers.offerfacade;


import java.util.List;

class ValidatorMessageConverter {

    List<String> convertMessagesToString(List<ValidatorMessage> validatorMessages) {
        return validatorMessages.stream().map(validatorMessage -> validatorMessage.message).toList();
    }
}
