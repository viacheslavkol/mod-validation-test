package org.folio;

public final class Constants {

  private Constants() {}

  public static final String VALID_RECORD = """
      {
          "type": "MARC",
          "fields": [
              {
                  "tag": "100",
                  "subfields": [
                      "a",
                      "b"
                  ]
              },
              {
                  "tag": "200",
                  "subfields": [
                      "c",
                      "d"
                  ]
              }
          ]
      }
      """;
  public static final String INVALID_RECORD = """
      {
          "type": "MARC",
          "fields": [
              {
                  "tag": "100",
                  "subfields": [
                      "c",
                      "d"
                  ]
              },
              {
                  "tag": "200",
                  "subfields": [
                      "a",
                      "b"
                  ]
              }
          ]
      }
      """;
}
