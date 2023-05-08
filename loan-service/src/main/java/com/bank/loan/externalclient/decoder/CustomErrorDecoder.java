package com.bank.loan.externalclient.decoder;

import java.io.IOException;

import org.springframework.web.ErrorResponse;

import com.bank.loan.exception.LoanException;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {
	@Override
	public Exception decode(String s, Response response) {

		log.info("::{}", response.request().url());
		log.info("::s{}", response.request().headers());

		ObjectMapper objectMapper = new ObjectMapper();
		ErrorResponse errorResponse;
		try {

			errorResponse = objectMapper.readValue(response.body().asInputStream(), ErrorResponse.class);
			return new LoanException(errorResponse.getDetailMessageCode(), errorResponse.getStatusCode().toString(),
					response.status());
		} catch (IOException e) {
			throw new LoanException("Internal Server error", "INTERNAL_SERVER_ERROR", 500);
		}

	}
}
