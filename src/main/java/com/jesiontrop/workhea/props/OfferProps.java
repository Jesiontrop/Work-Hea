package com.jesiontrop.workhea.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "workhea.offer")
@Data
public class OfferProps {

    private int pageSize = 20;
}
