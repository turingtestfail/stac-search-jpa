package com.jm.stacsearchjpa.model;

import java.io.Serializable;
import java.util.Objects;

public class LinkId implements Serializable {
    private String rel;
    private String href;

    public LinkId(){

    }
    public LinkId(String rel, String href){
        this.rel=rel;
        this.href=href;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkId linkId = (LinkId) o;
        return Objects.equals(rel, linkId.rel) &&
                Objects.equals(href, linkId.href);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rel, href);
    }
}
