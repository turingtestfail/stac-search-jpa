INSERT INTO public.catalog (id, description, stac_version, title) VALUES ('naip', 'Catalog of NAIP Imagery', '1.0.0', 'NAIP Imagery');
INSERT INTO public.link (href, rel, title, type) VALUES ('http://data.example.org/', 'self', 'this document', 'application/json');
INSERT INTO public.link (href, rel, title, type) VALUES ('http://data.example.org/api', 'service-desc', 'tthe API definition', 'application/vnd.oai.openapi+json;version=3.0');
--INSERT INTO public.link (href, rel, title, type) VALUES ('http://www.geoserver.example/stac/naip/child/catalog.json', 'child', 'NAIP Child Catalog', 'application/geo+json');
INSERT INTO public.catalog_links (catalog_id, links_href, links_rel) VALUES ('naip', 'http://data.example.org/', 'self');
INSERT INTO public.catalog_links (catalog_id, links_href, links_rel) VALUES ('naip', 'http://data.example.org/api', 'service-desc');
INSERT INTO public.collection (id, description, spatial, max_interval, min_interval, keywords, license, stac_extensions, stac_version, citation, max_ts, min_ts, title, constellation, gsd, instruments, max_nadir, min_nadir, platform, max_elevation, min_elevation) VALUES ('Sentinel-2', 'Sentinel-2 is a wide-swath, high-resolution, multi-spectral\nimaging mission...\n', '0103000020E6100000010000000500000000000000008066C000000000008056C0000000000080664000000000008056C00000000000806640000000000080564000000000008066C0000000000080564000000000008066C000000000008056C0', '2019-07-10 13:44:56', '2015-06-23 00:00:00', '{copernicus,esa,eu,msi,radiance,sentinel}', 'proprietary', '{}', '1.0.0', '{"Copernicus Sentinel data [Year]"}', '2020-06-22 19:10:25', '2016-06-22 19:10:25', 'Sentinel-2 MSI: MultiSpectral Instrument, Level-1C', '{sentinel-2}', NULL, '{msi}', 100, 0, '{sentinel-2a,sentinel-2b}', 89.9, 6.78);
INSERT INTO public.provider (name, roles, url) VALUES ('ESA', '{producer,licensor}', 'https://sentinel.esa.int/web/sentinel/user-guides/sentinel-2-msi');
INSERT INTO public.collection_providers (collection_id, providers_name) VALUES ('Sentinel-2', 'ESA');
INSERT INTO public.collections(
            coll_id)
    VALUES (1);


INSERT INTO public.collections_collections(
            collections_coll_id, collections_id)
    VALUES (1,'Sentinel-2');

INSERT INTO public.link(
            href, rel, title, type)
    VALUES ('http://www.geoserver.example/stac/naip/child/catalog.json', 'child', 'NAIP Child Catalog','application/geo+json');

INSERT INTO public.collections_links(
            links_href, links_rel, collections_coll_id)
    VALUES ('http://www.geoserver.example/stac/naip/child/catalog.json', 'child', 1);

INSERT INTO public.band (name, center_wavelength, common_name) VALUES ('B1', 4.43900000000000006, 'coastal');
INSERT INTO public.band (name, center_wavelength, common_name) VALUES ('B2', 4.96600000000000019, 'blue');
INSERT INTO public.band (name, center_wavelength, common_name) VALUES ('B3', 5.59999999999999964, 'green');
INSERT INTO public.band (name, center_wavelength, common_name) VALUES ('B4', 6.64499999999999957, 'red');
INSERT INTO public.band (name, center_wavelength, common_name) VALUES ('B5', NULL, '7.039');

INSERT INTO public.collection_bands (collection_id, bands_name) VALUES ('Sentinel-2', 'B1');
INSERT INTO public.collection_bands (collection_id, bands_name) VALUES ('Sentinel-2', 'B2');
INSERT INTO public.collection_bands (collection_id, bands_name) VALUES ('Sentinel-2', 'B3');
INSERT INTO public.collection_bands (collection_id, bands_name) VALUES ('Sentinel-2', 'B4');
INSERT INTO public.collection_bands (collection_id, bands_name) VALUES ('Sentinel-2', 'B5');

INSERT INTO public.conforms_to (conformsto) VALUES ('{http://www.opengis.net/spec/ogcapi-features-1/1.0/conf/core,http://www.opengis.net/spec/ogcapi-features-1/1.0/conf/oas30,http://www.opengis.net/spec/ogcapi-features-1/1.0/conf/html,http://www.opengis.net/spec/ogcapi-features-1/1.0/conf/geojson}');