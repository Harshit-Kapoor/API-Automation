package com.Graphql.pojos;

import lombok.Getter;
import lombok.Setter;

/**
 *  This is the main pojo class for complete query and json variable
 */

//@Getter
//@Setter
public class GraphqlQuery {

    private String query;
    private Object variables;


    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Object getVariables() {
        return variables;
    }

    public void setVariables(Object variables) {
        this.variables = variables;
    }

}
