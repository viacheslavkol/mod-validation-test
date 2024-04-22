package org.folio;

import static org.folio.Constants.INVALID_RECORD;
import static org.folio.Constants.VALID_RECORD;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.folio.poc.specifications.core.client.RecordSpecificationsClient;
import org.folio.poc.specifications.core.service.Validator;
import org.folio.poc.specifications.dto.MarcRecord;
import org.folio.poc.specifications.dto.RecordType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;


@EnableFeignClients("org.folio")
@ImportAutoConfiguration({FeignAutoConfiguration.class})
@SpringBootApplication
public class Main implements CommandLineRunner {

  private final RecordSpecificationsClient client;
  private final Validator validator;
  private final ObjectMapper mapper;

  public Main(RecordSpecificationsClient client) {
    this.client = client;
    validator = new Validator();
    mapper = new ObjectMapper();
  }

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    var specification = client.getRecordSpecification(RecordType.MARC);
    System.out.println("Specification from client: " + specification);

    var validRecord = mapper.readValue(VALID_RECORD, MarcRecord.class);
    System.out.println("Valid record: " + VALID_RECORD);
    var errorsForValid = validator.validate(validRecord, specification);
    System.out.println("Errors for valid record: " + errorsForValid);

    var invalidRecord = mapper.readValue(INVALID_RECORD, MarcRecord.class);
    System.out.println("Invalid record: " + VALID_RECORD);
    var errorsForInvalid = validator.validate(invalidRecord, specification);
    System.out.println("Errors for invalid record: " + errorsForInvalid);
  }
}