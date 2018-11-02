package com.codebrig.omnisrc.observations

import com.codebrig.omnisrc.SourceLanguage

/**
 * @author github.com/BFergerson
 */
class OmniObservedLanguage extends ObservedLanguage {

    //todo: rename to omniEntities?
    public final Set<String> globalEntities
    public final Set<String> globalAttributes
    public final Set<String> globalRelations
    public final Set<String> globalRoles

    OmniObservedLanguage() {
        super(SourceLanguage.OmniSRC)
        this.globalEntities = new HashSet<>()
        this.globalAttributes = new HashSet<>()
        this.globalRelations = new HashSet<>()
        this.globalRoles = new HashSet<>()
    }

    void addEntityRole(String entity, String role) {
        roles.putIfAbsent(entity, new ObservedRoles())
        roles.get(entity).observe([role].iterator())
    }

    void observeGlobalEntity(String entity) {
        globalEntities.add(entity)
    }

    void observeGlobalAttribute(String attribute) {
        globalAttributes.add(attribute)
    }

    void observeGlobalRelation(String relation) {
        globalRelations.add(relation)
    }

    void observeGlobalRole(String role) {
        globalRoles.add(role)
    }

    @Override
    List<String> getObservedEntities(boolean naturalOrdering) {
        if (naturalOrdering) {
            def rtnEntities = globalEntities.toList()
            rtnEntities.sort(String.CASE_INSENSITIVE_ORDER)
            return rtnEntities
        } else {
            return globalEntities.toList()
        }
    }

    @Override
    List<String> getObservedAttributes(boolean naturalOrdering) {
        if (naturalOrdering) {
            def rtnAttributes = globalAttributes.toList()
            rtnAttributes.sort(String.CASE_INSENSITIVE_ORDER)
            return rtnAttributes
        } else {
            return globalAttributes.toList()
        }
    }

    @Override
    List<String> getObservedRoles(boolean naturalOrdering) {
        if (naturalOrdering) {
            def rtnRoles = globalRoles.toList()
            rtnRoles.sort(String.CASE_INSENSITIVE_ORDER)
            return rtnRoles
        } else {
            return globalRoles.toList()
        }
    }

    @Override
    List<String> getObservedRelations(boolean naturalOrdering) {
        if (naturalOrdering) {
            def rtnRelations = globalRelations.toList()
            rtnRelations.sort(String.CASE_INSENSITIVE_ORDER)
            return rtnRelations
        } else {
            return globalRelations.toList()
        }
    }

    @Override
    boolean observedEntity(String entity) {
        return globalEntities.contains(entity)
    }

    @Override
    boolean observedAttribute(String attribute) {
        return globalAttributes.contains(attribute)
    }

    @Override
    boolean observedRelation(String relation) {
        return globalRelations.contains(relation)
    }
}
