package org.openmrs.module.etllite.api.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.api.db.hibernate.HibernateOpenmrsDataDAO;
import org.openmrs.module.etllite.api.dao.ErrorLogDao;
import org.openmrs.module.etllite.api.domain.ErrorLog;
import org.openmrs.module.etllite.api.util.DateUtil;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class ErrorLogDaoImpl extends HibernateOpenmrsDataDAO<ErrorLog> implements ErrorLogDao {

    private DbSessionFactory dbSessionFactory;

    public ErrorLogDaoImpl() {
        super(ErrorLog.class);
    }

    private DbSession getSession() {
        return dbSessionFactory.getCurrentSession();
    }

    @Override
    public ErrorLog findBySourceKeyAndRunDate(String database, String mapping, String sourceKey, String sourceValue, Date runOn) {
        Criteria crit = getSession().createCriteria(this.mappedClass);
        crit.add(Restrictions.eq("databaseName", database));
        crit.add(Restrictions.eq("mapping", mapping));
        crit.add(Restrictions.eq("sourceKey", sourceKey));
        crit.add(Restrictions.eq("sourceValue", sourceValue));
        crit.add(Restrictions.between("runOn", DateUtil.setTimeOfDay(runOn, 0, 0, 0), runOn));

        return (ErrorLog) crit.uniqueResult();
    }

    @Override
    public List<ErrorLog> findByMappingAndRunDate(String database, String mapping, Date runOn) {
        return findByMappingAndBetweenRunDates(database, mapping,
            DateUtil.setTimeOfDay(runOn, 0, 0, 0), runOn);
    }

    @Override
    public List<ErrorLog> findByMappingAndBetweenRunDates(String database, String mapping, Date startDate, Date endDate) {
        Criteria crit = getSession().createCriteria(this.mappedClass);
        crit.add(Restrictions.eq("databaseName", database));
        crit.add(Restrictions.eq("mapping", mapping));
        crit.add(Restrictions.between("runOn", startDate, endDate));

        return crit.list();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ErrorLog create(ErrorLog errorLog) {
        return saveOrUpdate(errorLog);
    }

    public void setDbSessionFactory(DbSessionFactory dbSessionFactory) {
        this.dbSessionFactory = dbSessionFactory;
    }
}
