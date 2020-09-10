package com.emarcora.service.api;

import com.emarcora.api.AboutApiDelegate;
import com.emarcora.model.AboutInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AboutApiService implements AboutApiDelegate {

    @Value("Sales Taxes Service")
    private String product;

    @Value("sales-taxes")
    private String component;

    @Value("1")
    private String version;

    @Value("1")
    private String[] apiVersions;

    @Override
    public ResponseEntity<AboutInfo> about() {
        AboutInfo aboutInfo = new AboutInfo();
        aboutInfo.setProduct(product);
        aboutInfo.setVersion(version);
        aboutInfo.setComponent(component);
        aboutInfo.setApiVersions(Arrays.asList(apiVersions));

        return ResponseEntity.ok(aboutInfo);
    }
}
