package org.technical.exam.priceapi.application.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.technical.exam.priceapi.application.rest.response.PriceResponse;
import org.technical.exam.priceapi.domain.port.PriceControllerPort;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/api/${api.version}")
public class PriceController {

  final PriceControllerPort priceAdapter;

  @Operation(summary = "Get the price of a product by its id, brandId and its effective date")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found the price",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = PriceResponse.class)) }),
      @ApiResponse(responseCode = "204", description = "Price not found",
          content = @Content),
      @ApiResponse(responseCode = "400", description = "Invalid provided parameters",
          content = @Content),
      @ApiResponse(responseCode = "404", description = "Price not found",
          content = @Content) })
  @GetMapping("/prices")
  public ResponseEntity<PriceResponse> getPrice(
      @RequestParam("brand_id") @Parameter(description = "brand id of price to be searched") @NonNull Long brandId,
      @RequestParam("product_id") @Parameter(description = "product id of price to be searched") @NonNull Long productId,
      @RequestParam("application_date") @Parameter(description = "Effective date for the price") @NonNull @DateTimeFormat(iso =
          DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate) {

    return priceAdapter
        .retrievePriceForBrandProductAndDateApplication(brandId, productId, applicationDate)
        .map(price -> new ResponseEntity<>(price, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
  }

}
