package com.emarcora.service.api;

import com.emarcora.api.ApplyTaxesApiDelegate;
import com.emarcora.model.TaxesRequest;
import com.emarcora.model.TaxesResponse;
import com.emarcora.service.components.SalesTaxesCalculator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ApplyTaxesService implements ApplyTaxesApiDelegate {

    private final SalesTaxesCalculator salesTaxesCalculator;

    @Override
    public ResponseEntity<TaxesResponse> applyTaxes(TaxesRequest taxesRequest) {
        TaxesResponse response = Optional.
                of(taxesRequest.getItems())
                .map(salesTaxesCalculator::computeTaxes)
                .orElseGet(() -> new TaxesResponse().salesTaxes(BigDecimal.ZERO).totalAmount(BigDecimal.ZERO));

        return ResponseEntity.ok(response);
    }
}
