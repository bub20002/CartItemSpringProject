package com.jcg.examples.service;

import com.jcg.examples.model.Item;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    public ItemServiceImpl(){}

    @Override
    public Item findById(long id) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session sess = null;
        Item item = null;
        try {
            sess = sf.openSession();
            Transaction tx = null;
            try {
                tx = sess.beginTransaction();
                Query q = sess.createQuery("from Item where id = :id");
                q.setParameter("id", id);
                item = (Item)q.list().get(0);
                tx.commit();
            } catch(RuntimeException e2) {
                try {
                    if(tx != null) tx.rollback();
                } catch (Exception e3) {
                    throw new RuntimeException("Rollback error");
                }
                throw new RuntimeException("Error while making query");
            }
        } catch (RuntimeException e1) {
            throw new RuntimeException(e1.getMessage());
        } finally {
            if(sess != null) sess.close();
        }
        if(item == null) {
            throw new RuntimeException("Item not found");
        }
        return item;
    }

    @Override
    public List<Item> selectAll() {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session session = null;
        List<Item> items;
        try {
            session = sf.openSession();
            Query q = session.createQuery("from Item");
            items = q.list();
        } catch (RuntimeException e) {
            throw new RuntimeException("Error while transaction performing");
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return items;
    }

    @Override
    public void save(Item item){
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session sess = null;
        try {
            sess = sf.openSession();
            Transaction tx = null;
            try {
                tx = sess.beginTransaction();
                sess.save(item);
                tx.commit();
            } catch (RuntimeException e2) {
                try {
                    if (tx != null) tx.rollback();
                } catch (Exception e3) {
                    //logger.error("Exception in rollback", new RuntimeException("Rollback error"));
                    throw new RuntimeException("Rollback error");
                }
                //logger.error("Exception in transaction",
                //        new RuntimeException("Error while performing transaction"));
                throw new RuntimeException("Error while performing transaction");
            }

        } catch (RuntimeException e1) {
            //logger.error("Exception in openSession", new RuntimeException(e1.getMessage()));
            throw new RuntimeException(e1.getMessage());
        } finally {
            if (sess != null) sess.close();
        }
    }

    @Override
    public void update(Item item) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session sess = null;
        try {
            sess = sf.openSession();
            Transaction tx = null;
            try {
                tx = sess.beginTransaction();
                String hql = "update Item set name =:name, description=:description, " +
                        "price=:price where id=:id";
                sess.createQuery(hql)
                        .setParameter("id", item.getId())
                        .setParameter("name", item.getName())
                        .setParameter("description", item.getDescription())
                        .setParameter("price", item.getPrice())
                        .executeUpdate();
                tx.commit();
            } catch(RuntimeException e2) {
                try {
                    if(tx != null) tx.rollback();
                } catch (Exception e3) {
                    throw new RuntimeException("Rollback error");
                }
                throw new RuntimeException("Error while performing transaction");
            }
        } catch (RuntimeException e1) {
            throw new RuntimeException(e1.getMessage());
        } finally {
            if(sess != null) sess.close();
        }
    }

    @Override
    public void deleteById(long id) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();

        Session sess = null;
        try {
            sess = sf.openSession();
            Transaction tx = null;
            try {
                tx = sess.beginTransaction();
                Item item = sess.get(Item.class, id);
                sess.delete(item);
                tx.commit();
            } catch(RuntimeException e2) {
                try {
                    if(tx != null) tx.rollback();
                } catch (Exception e3) {
                    throw new RuntimeException("Rollback error");
                }
                throw new RuntimeException("Error while performing transaction");
            }

        } catch (RuntimeException e1) {
            throw new RuntimeException(e1.getMessage());
        } finally {
            if(sess != null) sess.close();
        }
    }
}
