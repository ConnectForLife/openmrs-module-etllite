package org.openmrs.module.etllite.api.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.api.db.hibernate.HibernateOpenmrsDataDAO;
import org.openmrs.module.etllite.api.dao.ETLLogDao;
import org.openmrs.module.etllite.api.domain.ETLLog;

import java.util.Date;
import java.util.List;

public class ETLLogDaoImpl extends HibernateOpenmrsDataDAO<ETLLog> implements ETLLogDao {

    private static final String RUN_ON = "runOn";

    private DbSessionFactory dbSessionFactory;

    public ETLLogDaoImpl() {
        super(ETLLog.class);
    }

    private DbSession getSession() {
        return dbSessionFactory.getCurrentSession();
    }

    @Override
    public ETLLog findById(Integer jobId) {
        return getById(jobId);
    }

    @Override
    public ETLLog update(ETLLog etlLog) {
        return saveOrUpdate(etlLog);
    }

    @Override
    public ETLLog create(ETLLog etlLog) {
        return saveOrUpdate(etlLog);
    }

    @Override
    public List<ETLLog> retrieveAll() {
        return getAll(false);
    }

    @Override
    public void deleteAll() {
        getSession().createQuery("delete from etl.Log").executeUpdate();
    }

    @Override
    public Date executeQuery(String database, String mapping) {
        Criteria crit = getSession().createCriteria(this.mappedClass);
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.max(RUN_ON));
        crit.setProjection(projectionList);
        crit.add(Restrictions.eq("databaseName", database));
        crit.add(Restrictions.eq("mapping", mapping));

        return (Date) crit.uniqueResult();
    }

    public void setDbSessionFactory(DbSessionFactory dbSessionFactory) {
        this.dbSessionFactory = dbSessionFactory;
    }

}
