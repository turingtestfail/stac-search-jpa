package com.jm.stacsearchjpa.model.repository;

import com.jm.stacsearchjpa.model.Feature;
import org.locationtech.jts.geom.Geometry;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class FeatureRepositoryCustomImpl implements  FeatureRepositoryCustom{
    private final EntityManager em;

    public FeatureRepositoryCustomImpl(EntityManager em){
        this.em=em;
    }

    @Override
    public List<Feature> stacSearch(Geometry geometry, String datetime, List<String> ids, List<String> collections) {
        String jql = "select f from Feature f where 1=1 ";
        if(geometry!=null){
            jql = jql + "AND intersects(f.geometry, :geometry) = true ";
        }
        if(datetime!=null){
            String datetimeSQL = rFC3339ToSQLFragment(datetime);
            jql = jql + "AND " + datetimeSQL + " ";
        }
        Query query = em.createQuery(jql);
        if(geometry!=null) {
            query.setParameter("geometry", geometry);
        }
        return query.getResultList();
    }

    private String rFC3339ToSQLFragment(String datetime) {
        String[]dateelements = datetime.split("/");
        if(dateelements.length==1){
            //no slash so looking for exact match
            return "f.properties.datetime="+dateelements[0];
        }else if(dateelements.length==1){
            if(!dateelements[0].contains("..")&&!dateelements[1].contains("..")){
                //range with two timestamps
                return "f.properties.datetime between "+dateelements[0]+" and "+dateelements[1] + " ";
            }else if(dateelements[0].contains("..")){
                return "f.properties.datetime <= " + dateelements[1] + " ";
            }else {
                return "f.properties.datetime >= " + dateelements[0] + " ";
            }
        }else{
            throw new IllegalArgumentException("datetime not parseable");
        }
    }
}
