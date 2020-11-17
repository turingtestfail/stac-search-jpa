# stac-search-jpa
Spring Boot + Spring Data JPA implementation of STAC and STAC Query Extension
https://github.com/radiantearth/stac-spec
https://github.com/radiantearth/stac-api-spec/
https://github.com/radiantearth/stac-api-spec/tree/master/extensions/query

This project aims to give Java, Spring, and JPA developers a jumpstart on creating their own STAC implementations by providing the basic object relational mappings and JSON serialization/deserialization tools.  If you are interested in a high speed alternative that uses ElasticSearch take a look at [Staccato](https://github.com/planetlabs/staccato/tree/master/staccato-elasticsearch/src/main/java/com/planet/staccato/es)

Implemented Endpoints:
* STAC landing page (/)
* /conformance
* /collections
* /collections/{collectionId}
* /collections/{collectionId}/items
* /collections/{collectionId}/items/{featureId}
* /search
* /search?query=%7B"lte"%3A%7B"property"%3A"cloudcover"%2C"value"%3A10%7D%7D (this is http encoded cql-object following this standard https://github.com/opengeospatial/ogcapi-features/tree/master/extensions/cql )
* /filter?cql=  (CQL is not officially part of the STAC API standard yet, but I hear it is coming soon.  Note that this implementation is PostgreSQL specific and will have to be removed for other databases)

After you use JPA to create the tables you can load sample data from the [data.sql](https://github.com/turingtestfail/stac-search-jpa/blob/master/src/main/resources/data.sql)

To Be Done
1.  Add support for pagination via the STAC limit parameter.
2.  Add converter between STAC Extension Query and CQL.
3.  Create entity creation and editing end points.
    1.  React (or other JS Framework) front end to aid in entity creation.
    2.  GDAL and OSSIM integration to populate entities.
4.  Contribute back to GeoTools to bring cql support up to current WFS CQL state?  (DONE! https://github.com/geotools/geotools/tree/master/modules/unsupported/cql-json )

