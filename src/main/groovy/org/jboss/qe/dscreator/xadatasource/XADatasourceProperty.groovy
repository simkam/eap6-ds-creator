package org.jboss.qe.dscreator.xadatasource

/**
 * @author Martin Simka
 */
class XADatasourceProperty {
    private String name
    private String value

    XADatasourceProperty(String name, String value) {
        this.name = name
        this.value = value
    }

    String getName() {
        return name
    }

    String getValue() {
        return value
    }

    @Override
    boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        XADatasourceProperty prop = (XADatasourceProperty) obj
       return this.name.equals(prop.getName()) && this.value.equals(prop.getValue())
    }
}
