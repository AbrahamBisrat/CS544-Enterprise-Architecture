package edu.miu.cs.cs544.exercise16_1.bank.dao;

import edu.miu.cs.cs544.exercise16_1.bank.domain.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Collection;
import java.util.List;

public class AccDAOImpl implements AccountDAO{
    private SessionFactory sf = HibernateUtils.getSessionFactory();

    @Override public void saveAccount(Account account) {
        sf.getCurrentSession().persist(account);
    }

    @Override public Account loadAccount(long id) {
        return (Account) sf.getCurrentSession().get(Account.class, id);
    }

    @Override public void updateAccount(Account account) {
        sf.getCurrentSession().saveOrUpdate(account);
    }

    public void delete(Account account) {
        sf.getCurrentSession().delete(account);
    }

    @Override public Collection<Account> getAccounts() {
        return loadAllData(Account.class, sf.getCurrentSession());
    }

    private static <T> List<T> loadAllData(Class<T> type, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        List<T> data = session.createQuery(criteria).getResultList();
        return data;
    }
}
