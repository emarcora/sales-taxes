package com.emarcora.service.components.taxes;

import com.emarcora.model.Item;

import java.math.BigDecimal;

public interface Tax {

    BigDecimal applyTo(Item item);

}
