/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package io.mosip.esignet.vci.config;

import io.mosip.esignet.api.dto.VCRequestDto;
import io.mosip.esignet.api.dto.VCResult;
import io.mosip.esignet.api.spi.VCIssuancePlugin;
import io.mosip.esignet.core.dto.vci.ParsedAccessToken;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

import foundation.identity.jsonld.JsonLDObject;


@Slf4j
@Configuration
@ComponentScan(basePackages = {"io.mosip.esignet.vci"})
public class VCIConfig {

    @Bean
    @RequestScope
    public ParsedAccessToken parsedAccessToken() {
        return new ParsedAccessToken();
    }

    @Bean("esignet-service")
    public VCIssuancePlugin getVCIssuancePlugin() {
    	return new VCIssuancePlugin() {
            @Override
            public VCResult<JsonLDObject> getVerifiableCredentialWithLinkedDataProof(VCRequestDto vcRequestDto, String holderId, Map<String, Object> identityDetails) {
            	VCResult<JsonLDObject> vcResult = new VCResult<>();
                vcResult.setCredential(new JsonLDObject());
                vcResult.setFormat("ldp_vc");
                return vcResult;
            }

            @Override
            public VCResult<String> getVerifiableCredential(VCRequestDto vcRequestDto, String holderId, Map<String, Object> identityDetails) {
            	VCResult<String> vcResult_jwt = new VCResult<>();
                vcResult_jwt.setCredential("jwt");
                vcResult_jwt.setFormat("jwt_vc_json-ld");
                return vcResult_jwt;
            }
        };
    }

}
