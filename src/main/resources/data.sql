INSERT INTO public.catalog (id, description, stac_version, title) VALUES ('naip', 'Catalog of NAIP Imagery', '1.0.0', 'NAIP Imagery');
INSERT INTO public.link (href, rel, title, type) VALUES ('http://data.example.org/', 'self', 'this document', 'application/json');
INSERT INTO public.link (href, rel, title, type) VALUES ('http://data.example.org/api', 'service-desc', 'tthe API definition', 'application/vnd.oai.openapi+json;version=3.0');
INSERT INTO public.link (href, rel, title, type) VALUES ('http://www.geoserver.example/stac/naip/child/catalog.json', 'child', 'NAIP Child Catalog', 'application/geo+json');
INSERT INTO public.catalog_links (catalog_id, links_href, links_rel) VALUES ('naip', 'http://data.example.org/', 'self');
INSERT INTO public.catalog_links (catalog_id, links_href, links_rel) VALUES ('naip', 'http://data.example.org/api', 'service-desc');
INSERT INTO public.collection(
            id, description, spatial, keywords, license, stac_extensions,
            stac_version, title, citation, max_ts, min_ts)
    VALUES ('Sentinel-2', 'Sentinel-2 is a wide-swath, high-resolution, multi-spectral\nimaging mission...\n', ST_SetSRID(ST_GeomFromText('POLYGON((-180 -90, 180 -90, 180 90, -180 90, -180 -90))'),4326),
    '{copernicus,esa,eu,msi,radiance,sentinel}', 'proprietary', '{}',
            '1.0.0', 'Sentinel-2 MSI: MultiSpectral Instrument, Level-1C','{"Copernicus Sentinel data [Year]"}','2020-06-22 19:10:25', '2016-06-22 19:10:25');


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
