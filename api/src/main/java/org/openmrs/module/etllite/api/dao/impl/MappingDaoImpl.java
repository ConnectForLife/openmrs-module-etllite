package org.openmrs.module.etllite.api.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.api.db.hibernate.HibernateOpenmrsMetadataDAO;
import org.openmrs.module.etllite.api.dao.MappingDao;
import org.openmrs.module.etllite.api.domain.Mapping;

import java.util.List;

public class MappingDaoImpl extends HibernateOpenmrsMetadataDAO<Mapping> implements MappingDao {

    private DbSessionFactory dbSessionFactory;

    public MappingDaoImpl() {
        super(Mapping.class);
    }

    @Override
    public Mapping findByNameAndSource(String name, String source) {
        Criteria crit = getSession().createCriteria(this.mappedClass);
        crit.add(Restrictions.eq("name", name));
        crit.add(Restrictions.eq("source", source));
        return (Mapping) crit.uniqueResult();
    }

    @Override
    public List<Mapping> findBySource(String source) {
        Criteria crit = getSession().createCriteria(this.mappedClass);
        crit.add(Restrictions.eq("source", source));
        return crit.list();
    }

    @Override
    public Mapping create(Mapping mapping) {
        return saveOrUpdate(mapping);
    }

    @Override
    public Mapping findById(Integer id) {
        return getById(id);
    }

    @Override
    public Mapping update(Mapping mapping) {
        return saveOrUpdate(mapping);
    }

    @Override
    public List<Mapping> retrieveAll() {
        return getAll(false);
    }

    @Override
    public void deleteAll() {
        getSession().createQuery("delete from etl.Mapping").executeUpdate();
    }

    public void setDbSessionFactory(DbSessionFactory dbSessionFactory) {
        this.dbSessionFactory = dbSessionFactory;
    }

    private DbSession getSession() {
        return dbSessionFactory.getCurrentSession();
    }
}
