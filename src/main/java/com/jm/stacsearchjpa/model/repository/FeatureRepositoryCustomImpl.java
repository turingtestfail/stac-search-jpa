package com.jm.stacsearchjpa.model.repository;

import com.jm.stacsearchjpa.model.Feature;
import org.geotools.data.jdbc.FilterToSQLException;
import org.geotools.data.postgis.PostgisFilterToSQL;
import org.geotools.filter.FilterFactoryImpl;
import org.geotools.filter.text.cql2.CQLException;
import org.geotools.filter.text.cqljson.CQLJsonCompiler;
import org.geotools.filter.text.ecql.ECQL;
import org.locationtech.jts.geom.Geometry;
import org.opengis.filter.Filter;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class FeatureRepositoryCustomImpl implements  FeatureRepositoryCustom{
    private final EntityManager em;
    private final PostgisFilterToSQL postgisFilterToSQL;

    public FeatureRepositoryCustomImpl(EntityManager em, PostgisFilterToSQL postgisFilterToSQL){
        this.em=em;
        this.postgisFilterToSQL = postgisFilterToSQL;
    }

    @Override
    public List<Feature> stacSearch(String queryJson,
                                    Geometry bbox, Geometry geometry, String datetime,
                                    List<String> ids, List<String> collections) throws FilterToSQLException,
            CQLException {
        if(queryJson!=null&&queryJson.length()>0){
            return cqlSearch(queryJson);
        }
        String jql = "select f from Feature f where 1=1 ";
        if(bbox!=null){
            jql = jql + "AND intersects(f.bbox, :bbox) = true ";
        }
        if(geometry!=null){
            jql = jql + "AND intersects(f.geometry, :geometry) = true ";
        }
        if(datetime!=null){
            String datetimeSQL = rFC3339ToSQLFragment(datetime);
            jql = jql + "AND " + datetimeSQL + " ";
        }
        if(ids!=null){
            jql = jql + "AND f.id IN :ids ";
        }
        if(collections!=null){
            jql = jql + "AND f.collection.id IN :collections ";
        }
        Query query = em.createQuery(jql);
        if(bbox!=null) {
            query.setParameter("bbox", bbox);
        }
        if(geometry!=null) {
            query.setParameter("geometry", geometry);
        }
        if(ids!=null){
            query.setParameter("ids",ids);
        }
        if(collections!=null){
            query.setParameter("collections",collections);
        }

        return query.getResultList();
    }

    @Override
    public List<Feature> cqlSearch(String cql) throws CQLException, FilterToSQLException {
        String fragment = cqlSearchFragment(cql);
        String sql = "select f from Feature f " + fragment;
        Query query = em.createQuery(sql);
        return query.getResultList();
    }


    public String cqlSearchFragment(String cql) throws CQLException, FilterToSQLException {
        Filter filter = null;
        if(isJSON(cql)){
            CQLJsonCompiler cqlJsonCompiler =
                    new CQLJsonCompiler(cql, new FilterFactoryImpl());
            cqlJsonCompiler.compileFilter();
            filter = cqlJsonCompiler.getFilter();
        }else {
            filter = ECQL.toFilter(cql);
        }
        String sqlFrag = postgisFilterToSQL.encodeToString(filter);
        return sqlFrag;
    }

    private boolean isJSON(String candidate){
        boolean json = false;
        if( (candidate.startsWith( "[" ) && candidate.endsWith( "]" ))
                || (candidate.startsWith( "{" ) && candidate.endsWith( "}" ))){
            return true;
        }
        return json;
    }

    private String rFC3339ToSQLFragment(String datetime) {
        String[]dateelements = datetime.split("/");
        if(dateelements.length==1){
            //no slash so looking for exact match
            return "f.properties.datetime='"+dateelements[0] + "' ";
        }else if(dateelements.length==2){
            if(!dateelements[0].contains("..")&&!dateelements[1].contains("..")){
                //range with two timestamps
                return "f.properties.datetime between '"+dateelements[0]+"' and '"+dateelements[1] + "' ";
            }else if(dateelements[0].contains("..")){
                return "f.properties.datetime <= '" + dateelements[1] + "' ";
            }else {
                return "f.properties.datetime >= '" + dateelements[0] + "' ";
            }
        }else{
            throw new IllegalArgumentException("datetime not parseable");
        }
    }
}
